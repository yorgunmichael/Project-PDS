package IhmMapping;

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
    public Gestion() {
        setPreferredSize(new Dimension(750, 750));
        this.addMouseListener(this);
    }


    public void paint(Graphics g) {
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
                System.out.println(b);
            }
            if (answers[0].contains("t")) {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
            } else {
                map.get("requestEquipment").put("id_localisation", id_localisation);
                String requestEquipment = getSend("requestEquipment");
                String[] answersrequestEquipment = requestEquipment.split("@");
                for (String b : answersrequestEquipment) {
                    if (b.contains("@")) {
                        b.replace("@", "");
                    }
                }
                String name_equipment = answersrequestEquipment[0];

                imgURL = Thread.currentThread().getContextClassLoader().getResource("écranActif.jpg");

                if (name_equipment.contains("fenêtre")) {
                    imgURL = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");
                } else if (name_equipment.contains("capteur")) {
                    imgURL = Thread.currentThread().getContextClassLoader().getResource("capteur.jpg");
                } else if (name_equipment.contains("prise")) {
                    imgURL = Thread.currentThread().getContextClassLoader().getResource("prise.jpg");
                } else if (name_equipment.contains("écran")) {
                    imgURL = Thread.currentThread().getContextClassLoader().getResource("écranActif.jpg");
                }

            }
            currentEquipment = ImageIO.read(imgURL);
            g.drawImage(currentEquipment, Integer.valueOf(x), Integer.valueOf(y), 50, 50, null);


        } catch (Exception e) {
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

        if (e.getX() >= 195 & e.getX() <= 245 & e.getY() >= 460 & e.getY() <= 510) {
            placeOrDelete("195", "460");
        }
        if (e.getX() >= 533 & e.getX() <= 583 & e.getY() >= 201 & e.getY() <= 251) {
            placeOrDelete("533", "201");
        }
        if (e.getX() >= 550 & e.getX() <= 600 & e.getY() >= 549 & e.getY() <= 599) {
            placeOrDelete("550", "549");
        }
        if (e.getX() >= 99 & e.getX() <= 149 & e.getY() >= 377 & e.getY() <= 427) {
            placeOrDelete("99", "377");
        }
    }

    private void placeOrDelete(String x, String y) {
        JOptionPane d = new JOptionPane();
        map.get("requestGetEquipment");
        String responses = getSend("requestGetEquipment");
        String[] ListEquipment = responses.split("@");
        for (String b : ListEquipment) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
            System.out.println(b);
        }

        BufferedImage currentEquipment;
        URL mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Quel équipement voulez vous placer?");
        panel.add(label);

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
        }

        System.out.println("Is empty ? : " + answers[0]);


        if (answers[0].contains("t")) {

            String name_equipment =
                    (String) JOptionPane.showInputDialog(null,
                            "Veuillez indiquer votre équipement",
                            "Choix d'équipements",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            ListEquipment,
                            ListEquipment[0]);
            System.out.println((String) name_equipment);


            if (name_equipment.contains("fenêtre")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");
            } else if (name_equipment.contains("capteur")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("capteur.jpg");
            } else if (name_equipment.contains("prise")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("prise.jpg");
            } else if (name_equipment.contains("écran")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("écranActif.jpg");
            }


            if (name_equipment.contains("fenêtre") | name_equipment.contains("capteur") | name_equipment.contains("prise") | name_equipment.contains("écran")) {

                String availablity = "false";

                map.get("requestUpdateEquipment").put("id_localisation", id_localisation);
                map.get("requestUpdateEquipment").put("availablity", availablity);
                map.get("requestUpdateEquipment").put("name_equipment", name_equipment);
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
                    currentEquipment = ImageIO.read(mapUrl);
                    int xx = Integer.valueOf(x);
                    int yy = Integer.valueOf(y);
                    getGraphics().drawImage(currentEquipment, xx, yy, 50, 50, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        } else {
            int responseScreen = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer l'équipement?");
            if (responseScreen == JOptionPane.YES_OPTION) {

                String availablity = "true";


                map.get("requestGetNameEquipment").put("id_localisation", id_localisation);
                String requestGetNameEquipment = getSend("requestGetNameEquipment");
                String[] answersUpdate = requestGetNameEquipment.split("@");
                for (String b : answersUpdate) {
                    if (b.contains("@")) {
                        b.replace("@", "");
                    }
                }
                String name_equipment = answersUpdate[0];

                map.get("requestUpdateEquipment").put("id_localisation", id_localisation);
                map.get("requestUpdateEquipment").put("availablity", availablity);
                map.get("requestUpdateEquipment").put("name_equipment", name_equipment);
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
                    currentEquipment = ImageIO.read(imgURL);
                    int xx = Integer.valueOf(x);
                    int yy = Integer.valueOf(y);
                    getGraphics().clearRect(xx, yy, 50, 50);
                    getGraphics().drawImage(currentEquipment, xx, yy, 50, 50, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
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
