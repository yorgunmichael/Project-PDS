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
        drawPositions(g);
        drawPrisePosition(g);
        drawSensorPosition(g);
        drawWindowsPosition(g);


    }


    public void drawPositions(Graphics g) {
        URL imgURL;
        BufferedImage currentEquipment;

        try {
            String id_room = WindowsMapping.getId_room();
            map.get("requestScreenIsEmpty").put("id_room", id_room);
            String responseScreenIsEmpty = getSend("requestScreenIsEmpty");
            String[] answers = responseScreenIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }
            if (!answers[0].contains("t")) {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
            } else {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("écran.jpg");
            }
            currentEquipment = ImageIO.read(imgURL);
            g.drawImage(currentEquipment, 111, 430, 50, 50, null);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawPrisePosition(Graphics g) {
        URL imgURL;
        BufferedImage currentEquipment;

        try {
            String id_room = WindowsMapping.getId_room();
            map.get("requestPriseIsEmpty").put("id_room", id_room);
            String responsePriseIsEmpty = getSend("requestPriseIsEmpty");
            String[] answers = responsePriseIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }
            if (!answers[0].contains("t")) {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
            } else {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("prise.jpg");
            }
            currentEquipment = ImageIO.read(imgURL);
            g.drawImage(currentEquipment, 557, 560, 50, 50, null);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawWindowsPosition(Graphics g) {
        URL imgURL;
        BufferedImage currentEquipment;

        try {
            String id_room = WindowsMapping.getId_room();
            map.get("requestWindowsIsEmpty").put("id_room", id_room);
            String requestWindowsIsEmpty = getSend("requestWindowsIsEmpty");
            String[] answers = requestWindowsIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }
            if (!answers[0].contains("t")) {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
            } else {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");
            }
            currentEquipment = ImageIO.read(imgURL);
            g.drawImage(currentEquipment, 625, 220, 50, 50, null);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSensorPosition(Graphics g) {
        URL imgURL;
        BufferedImage currentEquipment;

        try {
            String id_room = WindowsMapping.getId_room();
            map.get("requestSensorIsEmpty").put("id_room", id_room);
            String requestSensorIsEmpty = getSend("requestSensorIsEmpty");
            String[] answers = requestSensorIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }
            if (!answers[0].contains("t")) {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
            } else {
                imgURL = Thread.currentThread().getContextClassLoader().getResource("capteur.jpg");
            }
            currentEquipment = ImageIO.read(imgURL);
            g.drawImage(currentEquipment, 236, 189, 50, 50, null);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

        BufferedImage currentEquipment;
        URL mapUrl = Thread.currentThread().getContextClassLoader().getResource("fenetre.jpg");
        if (e.getX() >= 625 & e.getX() <= 675 & e.getY() >= 220 & e.getY() <= 270) {

            String id_room = WindowsMapping.getId_room();

            map.get("requestWindowsIsEmpty").put("id_room", id_room);
            String requestWindowsIsEmpty = getSend("requestWindowsIsEmpty");
            String[] answers = requestWindowsIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }

            if (!answers[0].contains("t")) {
                int responsePrise = JOptionPane.showConfirmDialog(null, " Voulez vous placer une fenetre?");
                if (responsePrise == JOptionPane.YES_OPTION) {


                    String valueChoose = "1";
                    map.get("requestUpdateWindows").put("value", valueChoose);
                    map.get("requestUpdateWindows").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdateWindows");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        currentEquipment = ImageIO.read(mapUrl);
                        getGraphics().drawImage(currentEquipment, 625, 220, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                int responseScreen = JOptionPane.showConfirmDialog(null, " Voulez vous supprimer une fenetre?");
                if (responseScreen == JOptionPane.YES_OPTION) {


                    String valueChoose = "0";
                    map.get("requestUpdateWindows").put("value", valueChoose);
                    map.get("requestUpdateWindows").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdateWindows");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        URL imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
                        currentEquipment = ImageIO.read(imgURL);
                        getGraphics().clearRect(625, 220, 50, 50);
                        getGraphics().drawImage(currentEquipment, 625, 220, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

        }

        URL mapUrl2 = Thread.currentThread().getContextClassLoader().getResource("capteur.jpg");
        if (e.getX() >= 236 & e.getX() <= 286 & e.getY() >= 189 & e.getY() <= 239) {

            String id_room = WindowsMapping.getId_room();

            map.get("requestSensorIsEmpty").put("id_room", id_room);
            String requestSensorIsEmpty = getSend("requestSensorIsEmpty");
            String[] answers = requestSensorIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }

            if (!answers[0].contains("t")) {
                int responseSensor = JOptionPane.showConfirmDialog(null, " Voulez vous placer un capteur?");
                if (responseSensor == JOptionPane.YES_OPTION) {


                    String valueChoose = "1";
                    map.get("requestUpdateSensor").put("value", valueChoose);
                    map.get("requestUpdateSensor").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdateSensor");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        currentEquipment = ImageIO.read(mapUrl2);
                        getGraphics().drawImage(currentEquipment, 236, 189, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                int responseSensor = JOptionPane.showConfirmDialog(null, " Voulez vous supprimer un capteur?");
                if (responseSensor == JOptionPane.YES_OPTION) {


                    String valueChoose = "0";
                    map.get("requestUpdateSensor").put("value", valueChoose);
                    map.get("requestUpdateSensor").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdateSensor");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        URL imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
                        currentEquipment = ImageIO.read(imgURL);
                        getGraphics().clearRect(236, 189, 50, 50);
                        getGraphics().drawImage(currentEquipment, 236, 189, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

        }

        URL mapUrl1 = Thread.currentThread().getContextClassLoader().getResource("prise.jpg");
        if (e.getX() >= 557 & e.getX() <= 607 & e.getY() >= 560 & e.getY() <= 610) {

            String id_room = WindowsMapping.getId_room();

            map.get("requestPriseIsEmpty").put("id_room", id_room);
            String responsePriseIsEmpty = getSend("requestPriseIsEmpty");
            String[] answers = responsePriseIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }

            if (!answers[0].contains("t")) {
                int responsePrise = JOptionPane.showConfirmDialog(null, " Voulez vous placer une prise?");
                if (responsePrise == JOptionPane.YES_OPTION) {


                    String valueChoose = "1";
                    map.get("requestUpdatePrise").put("value", valueChoose);
                    map.get("requestUpdatePrise").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdatePrise");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        currentEquipment = ImageIO.read(mapUrl1);
                        getGraphics().drawImage(currentEquipment, 557, 560, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                int responseScreen = JOptionPane.showConfirmDialog(null, " Voulez vous supprimer une prise?");
                if (responseScreen == JOptionPane.YES_OPTION) {


                    String valueChoose = "0";
                    map.get("requestUpdatePrise").put("value", valueChoose);
                    map.get("requestUpdatePrise").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdatePrise");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        URL imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
                        currentEquipment = ImageIO.read(imgURL);
                        getGraphics().clearRect(557, 560, 50, 50);
                        getGraphics().drawImage(currentEquipment, 557, 560, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }

        URL mapUrl3 = Thread.currentThread().getContextClassLoader().getResource("écran.jpg");
        if (e.getX() >= 111 & e.getX() <= 161 & e.getY() >= 430 & e.getY() <= 480) {

            String id_room = WindowsMapping.getId_room();

            map.get("requestScreenIsEmpty").put("id_room", id_room);
            String responseScreenIsEmpty = getSend("requestScreenIsEmpty");
            String[] answers = responseScreenIsEmpty.split("@");
            for (String b : answers) {
                if (b.contains("@")) {
                    b.replace("@", "");
                }
                System.out.println(b);
            }

            if (!answers[0].contains("t")) {
                int responseScreen = JOptionPane.showConfirmDialog(null, " Voulez vous placer un écran?");
                if (responseScreen == JOptionPane.YES_OPTION) {


                    String valueChoose = "1";
                    map.get("requestUpdate").put("value", valueChoose);
                    map.get("requestUpdate").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdate");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        currentEquipment = ImageIO.read(mapUrl3);
                        getGraphics().drawImage(currentEquipment, 111, 430, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                int responseScreen = JOptionPane.showConfirmDialog(null, " Voulez vous supprimer un écran?");
                if (responseScreen == JOptionPane.YES_OPTION) {


                    String valueChoose = "0";
                    map.get("requestUpdate").put("value", valueChoose);
                    map.get("requestUpdate").put("id_room", id_room);
                    String responseUpdate = getSend("requestUpdate");
                    answers = responseUpdate.split("@");
                    for (String b : answers) {
                        if (b.contains("@")) {
                            b.replace("@", "");
                        }
                        System.out.println(b);
                    }

                    try {
                        URL imgURL = Thread.currentThread().getContextClassLoader().getResource("localisation.png");
                        currentEquipment = ImageIO.read(imgURL);
                        getGraphics().clearRect(111, 430, 50, 50);
                        getGraphics().drawImage(currentEquipment, 111, 430, 50, 50, null);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
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
