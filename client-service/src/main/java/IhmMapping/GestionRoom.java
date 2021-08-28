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

public class GestionRoom extends JPanel implements MouseListener {

    public GestionRoom() {
        setPreferredSize(new Dimension(750, 750));
        this.addMouseListener(this);
    }


    public void paint(Graphics g) {
        URL imgURL = Thread.currentThread().getContextClassLoader().getResource("salle de conférence.jpg");
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
        drawEquipment("111", "430", g);
    }

    public void drawPosition2(Graphics g) {
        drawEquipment("625", "220", g);
    }

    public void drawPosition3(Graphics g) {
        drawEquipment("236", "189", g);
    }

    public void drawPosition4(Graphics g) {
        drawEquipment("557", "560", g);
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {


        if (e.getX() >= 625 & e.getX() <= 675 & e.getY() >= 220 & e.getY() <= 270) {
            if ((e.getButton() == MouseEvent.BUTTON3)) {
                changeStatus("625", "220");
            } else {
                placeOrDelete("625", "220");
            }

        } else if (e.getX() >= 236 & e.getX() <= 286 & e.getY() >= 189 & e.getY() <= 239) {
            placeOrDelete("236", "189");
        } else if (e.getX() >= 557 & e.getX() <= 607 & e.getY() >= 560 & e.getY() <= 610) {
            placeOrDelete("557", "560");
        } else if (e.getX() >= 111 & e.getX() <= 161 & e.getY() >= 430 & e.getY() <= 480) {
            placeOrDelete("111", "430");
        }

    }

    private void changeStatus(String x, String y) {

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

        map.get("requestGetStatusEquipment").put("name_equipment", name_equipment);
        String requestGetStatusEquipment = getSend("requestGetStatusEquipment");
        String[] answersStatus = requestGetStatusEquipment.split("@");
        for (String b : answersStatus) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
        }

        String status = answersStatus[0];

        if (status.equals("Actif")) {
            status = "Inactif";

        } else {
            status = "Actif";
        }
        URL mapUrl;
      int response  = JOptionPane.showConfirmDialog(null, "Voulez vous changer le status de l'équipement");
        if(response == JOptionPane.YES_OPTION) {

            map.get("requestUpdateStatusEquipment").put("status", status);
            map.get("requestUpdateStatusEquipment").put("name_equipment", name_equipment);
            String requestUpdateStatusEquipment = getSend("requestUpdateStatusEquipment");
            String[] answersUpdatees = requestUpdateStatusEquipment.split("@");
            for (String b : answersUpdatees) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
            }

            if (name_equipment.contains("fenêtre")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");
            } else if (name_equipment.contains("capteur")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("capteur.jpg");
            } else if (name_equipment.contains("prise")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("prise.jpg");
            } else if (name_equipment.contains("écran")) {
                mapUrl = Thread.currentThread().getContextClassLoader().getResource("écran.jpg");
            }

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
