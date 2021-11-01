package IhmMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static client.Client.getSend;
import static client.Client.map;

public class Gestion extends JPanel implements MouseListener {
    private final static Logger log = LoggerFactory.getLogger(Gestion.class.getName());
    Graphics g;
    public Gestion() {
        setPreferredSize(new Dimension(750, 750));
        this.addMouseListener(this);
    }


    public void paint(Graphics g) {
        this.g = g;
        URL imgURL = Thread.currentThread().getContextClassLoader().getResource("planbureau.jpg");
        BufferedImage map;
        try {
            map = ImageIO.read(imgURL);
            g.drawImage(map, 0, 0, 750, 750, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawPosition1(g);
        drawPosition2(g);
        drawPosition4(g);
        drawPosition3(g);
    }

    private void drawEquipment(String x, String y, Graphics g) {
        URL imgURL;
        BufferedImage currentEquipment;

        try {
            String id_room = WindowsMapping.getId_room();

            map.get("requestGetLocalisation").put("id_room", id_room);
            map.get("requestGetLocalisation").put("positionX", x);
            map.get("requestGetLocalisation").put("positionY", y);
            String requestGetLocalisation = getSend("requestGetLocalisation");
            String[] answersLocalisation = requestGetLocalisation.split("@");
            for (String b : answersLocalisation) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }

            String id_localisation = answersLocalisation[0];

            map.get("requestVerifyStatus").put("id_localisation", id_localisation);
            String requestVerifyStatus = getSend("requestVerifyStatus");
            String[] answers = requestVerifyStatus.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }

                log.info(b);
            }
            if (answers[0].contains("t")) {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
                currentEquipment = ImageIO.read(imgURL);
                g.drawImage(currentEquipment, Integer.valueOf(x), Integer.valueOf(y), 50, 50, null);
            } else {
                InitialDraw(x, y, g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void InitialDraw(String x, String y,Graphics g) {

        String id_room = WindowsMapping.getId_room();
        log.info(id_room + "id_room");


        map.get("requestGetEquip").put("id_room", id_room);
        map.get("requestGetEquip").put("positionX", x);
        map.get("requestGetEquip").put("positionY", y);
        String requestGetEquip = getSend("requestGetEquip");
        String[] answersEquipment = requestGetEquip.split("@");
        for (String b : answersEquipment) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }

        String name_equipment = answersEquipment[0];
        log.info(name_equipment + "name_equipment");

        map.get("requestGetStatusEquipment").put("name_equipment", name_equipment);
        String requestGetStatusEquipment = getSend("requestGetStatusEquipment");
        String[] answersStatus = requestGetStatusEquipment.split("@");
        for (String b : answersStatus) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }

        String status = answersStatus[0];
        log.info(status + "status");

        activeOrInactive(name_equipment, status, x, y, g);
    }

    private void activeOrInactive(String name_equipment, String status, String x, String y, Graphics g) {

        URL mapUrl = null;

        if (name_equipment.contains("fenêtre")) {
            if (status.equals("Inactif")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");
            } else {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetreActif.jpg");
            }

        } else if (name_equipment.contains("capteur")) {
            if (status.equals("Inactif")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("capteur.jpg");
            } else {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("capteurActif.jpg");
            }

        } else if (name_equipment.contains("prise")) {
            if (status.equals("Inactif")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("prise.jpg");
            } else {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("priseActif.jpg");
            }

        } else if (name_equipment.contains("écran")) {
            if (status.equals("Inactif")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("écran.jpg");
            } else {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("écranActif.jpg");
            }
        }
        try {
            Image currentEquipment = ImageIO.read(mapUrl);
            g.drawImage(currentEquipment, Integer.valueOf(x), Integer.valueOf(y), 50, 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawPosition1(Graphics g) {
        drawEquipment("195", "460", g);
    }

    public void drawPosition2(Graphics g) {
        drawEquipment("533", "201", g);
    }

    public void drawPosition3(Graphics g) {
        drawEquipment("550", "549", g);
    }

    public void drawPosition4(Graphics g) {
        drawEquipment("99", "377", g);
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        String id_room = WindowsMapping.getId_room();

        if (e.getX() >= 195 & e.getX() <= 245 & e.getY() >= 460 & e.getY() <= 510) {
            if ((e.getButton() == MouseEvent.BUTTON3) && verifyStatus(getLocalisation(id_room, "195", "460")).contains("f")) {
                log.info("change status");
                changeStatus("195", "460");
            } else {
                placeOrDelete("195", "460");
            }
        }
        if (e.getX() >= 533 & e.getX() <= 583 & e.getY() >= 201 & e.getY() <= 251) {
            if ((e.getButton() == MouseEvent.BUTTON3) && verifyStatus(getLocalisation(id_room, "533", "201")).contains("f")) {
                System.out.println("change status");
                changeStatus("533", "201");
            } else {
                placeOrDelete("533", "201");
            }
        }
        if (e.getX() >= 550 & e.getX() <= 600 & e.getY() >= 549 & e.getY() <= 599) {
            if ((e.getButton() == MouseEvent.BUTTON3) && verifyStatus(getLocalisation(id_room, "550", "549")).contains("f")) {
                System.out.println("change status");
                changeStatus("550", "549");
            } else {
                placeOrDelete("550", "549");
            }
        }
        if (e.getX() >= 99 & e.getX() <= 149 & e.getY() >= 377 & e.getY() <= 427) {
            if ((e.getButton() == MouseEvent.BUTTON3) && verifyStatus(getLocalisation(id_room, "99", "377")).contains("f")) {
                System.out.println("change status");
                changeStatus("625", "220");
            } else {
                placeOrDelete("99", "377");
            }
        }
    }

    private String getLocalisation(String id_room, String x, String y) {
        map.get("requestGetLocalisation").put("id_room", id_room);
        map.get("requestGetLocalisation").put("positionX", x);
        map.get("requestGetLocalisation").put("positionY", y);
        String requestGetLocalisation = getSend("requestGetLocalisation");
        String[] answersLocalisation = requestGetLocalisation.split("@");
        for (String b : answersLocalisation) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }

        String id_localisation = answersLocalisation[0];
        return id_localisation;
    }

    private String verifyStatus(String id_localisation) {
        map.get("requestVerifyStatus").put("id_localisation", id_localisation);
        String requestVerifyStatus = getSend("requestVerifyStatus");
        String[] answers = requestVerifyStatus.split("@");
        for (String b : answers) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }
        return answers[0];
    }

    private void changeStatus(String x, String y) {

        String id_room = WindowsMapping.getId_room();
        log.info(id_room + "id_room");


        map.get("requestGetEquip").put("id_room", id_room);
        map.get("requestGetEquip").put("positionX", x);
        map.get("requestGetEquip").put("positionY", y);
        String requestGetEquip = getSend("requestGetEquip");
        String[] answersEquipment = requestGetEquip.split("@");
        for (String b : answersEquipment) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }

        String name_equipment = answersEquipment[0];
        log.info(name_equipment + "name_equipment");

        map.get("requestGetStatusEquipment").put("name_equipment", name_equipment);
        String requestGetStatusEquipment = getSend("requestGetStatusEquipment");
        String[] answersStatus = requestGetStatusEquipment.split("@");
        for (String b : answersStatus) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }

        String status = answersStatus[0];
        log.info(status + "status");

        if (status.equals("Actif")) {
            status = "Inactif";

        } else {
            status = "Actif";
        }


        int response = JOptionPane.showConfirmDialog(null, "Voulez vous changer le status de l'équipement");
        if (response == JOptionPane.YES_OPTION) {


            map.get("requestUpdateStatusEquipment").put("status", status);
            map.get("requestUpdateStatusEquipment").put("name_equipment", name_equipment);
            String requestUpdateStatusEquipment = getSend("requestUpdateStatusEquipment");
            String[] answersUpdatees = requestUpdateStatusEquipment.split("@");
            for (String b : answersUpdatees) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }

            activeOrInactive(name_equipment, status, x, y, g);

        }

    }

    private void placeOrDelete(String x, String y) {


        JPanel panel = new JPanel();
        JLabel label = new JLabel("Quel équipement voulez vous placer? ");
        panel.add(label);

        String id_room = WindowsMapping.getId_room();


        String id_localisation = getLocalisation(id_room, x, y);
        String status = verifyStatus(id_localisation);

        log.info("Is empty ? : " + status);


        if (status.contains("t")) {
            place(id_localisation, x, y);


        } else {
            delete(id_localisation, x, y);
        }

    }

    private void delete(String id_localisation, String x, String y) {

        map.get("requestGetNameEquipment").put("id_localisation", id_localisation);
        String requestGetNameEquipment = getSend("requestGetNameEquipment");
        String[] answersUpdate = requestGetNameEquipment.split("@");
        for (String b : answersUpdate) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }
        String name_equipment = answersUpdate[0];

        int responseScreen = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer l'équipement " + name_equipment);
        if (responseScreen == JOptionPane.YES_OPTION) {

            String availablity = "true";


            map.get("requestUpdateEquipment").put("id_localisation", "NULL");
            map.get("requestUpdateEquipment").put("availablity", availablity);
            map.get("requestUpdateEquipment").put("name_equipment", name_equipment);
            map.get("requestUpdateEquipment").put("status", "NULL");
            String requestUpdateEquipment = getSend("requestUpdateEquipment");
            answersUpdate = requestUpdateEquipment.split("@");
            for (String b : answersUpdate) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }

            map.get("requestUpdateStatus").put("id_localisation", id_localisation);
            map.get("requestUpdateStatus").put("empty", "true");
            String requestUpdateStatus = getSend("requestUpdateStatus");
            String[] answersUpdates = requestUpdateStatus.split("@");
            for (String b : answersUpdates) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }

            try {
                URL imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
                Image currentEquipment = ImageIO.read(imgURL);
                int xx = Integer.valueOf(x);
                int yy = Integer.valueOf(y);
                getGraphics().clearRect(xx, yy, 50, 50);
                getGraphics().drawImage(currentEquipment, xx, yy, 50, 50, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void place(String id_localisation, String x, String y) {
        map.get("requestGetEquipment");
        String responses = getSend("requestGetEquipment");
        String[] ListEquipment = responses.split("@");
        for (String b : ListEquipment) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
            log.info(b);
        }

        String name_equipment =
                (String) JOptionPane.showInputDialog(null,
                        "Veuillez indiquer votre équipement",
                        "Choix d'équipements",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        ListEquipment,
                        ListEquipment[0]);

        log.info((String) name_equipment);
        URL mapUrl = null;

        if (name_equipment.contains("fenêtre")) {
            mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetreActif.jpg");
        } else if (name_equipment.contains("capteur")) {
            mapUrl = Thread.currentThread().getContextClassLoader().getResource("capteurActif.jpg");
        } else if (name_equipment.contains("prise")) {
            mapUrl = Thread.currentThread().getContextClassLoader().getResource("priseActif.jpg");
        } else if (name_equipment.contains("écran")) {
            mapUrl = Thread.currentThread().getContextClassLoader().getResource("écranActif.jpg");
        }


        if (name_equipment.contains("fenêtre") | name_equipment.contains("capteur") | name_equipment.contains("prise") | name_equipment.contains("écran")) {

            String availablity = "false";


            map.get("requestUpdateEquipment").put("id_localisation", id_localisation);
            map.get("requestUpdateEquipment").put("availablity", availablity);
            map.get("requestUpdateEquipment").put("name_equipment", name_equipment);
            map.get("requestUpdateEquipment").put("status", "Actif");
            String requestUpdateEquipment = getSend("requestUpdateEquipment");
            String[] answersUpdate = requestUpdateEquipment.split("@");
            for (String b : answersUpdate) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }

            map.get("requestUpdateStatus").put("id_localisation", id_localisation);
            map.get("requestUpdateStatus").put("empty", "false");
            String requestUpdateStatus = getSend("requestUpdateStatus");
            String[] answersUpdates = requestUpdateStatus.split("@");
            for (String b : answersUpdates) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }


            try {
                Image currentEquipment = ImageIO.read(mapUrl);
                int xx = Integer.valueOf(x);
                int yy = Integer.valueOf(y);
                getGraphics().drawImage(currentEquipment, xx, yy, 50, 50, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
