package IhmMapping;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.Socket;

import static client.Client.getSend;
import static client.Client.map;

public class WindowsMapping extends JFrame {
    JPanel panel = new JPanel();
    Gestion panelBureau;
    GestionRoom panelRoom;

    public static JComboBox<String> listBuiling;
    private JComboBox<String> listRoom;
    private JComboBox<String> listFloor;
    private Socket socket;
    private String company_name;
    public static String id_room;

    public static String getId_room() {
        return id_room;
    }

    public WindowsMapping(String company_name) {
        Frame frame = this;

        CardLayout cardLayout = new CardLayout();
        JPanel panels = new JPanel();
        panels.setLayout(cardLayout);

        panelBureau = new Gestion();
        panelRoom = new GestionRoom();
        panels.add(panelBureau, "panelBureau");
        panels.add(panelRoom, "panelRoom");

        this.company_name = company_name;
        // listB = new JComboBox<>();
        listFloor = new JComboBox<>();
        listRoom = new JComboBox<>();

        this.setTitle("Bienvenue à l'affichage");
        this.setSize(900, 800);
        this.setResizable(false);
        getContentPane().setBackground(Color.white);
        panel.setBackground(Color.BLUE);
        panel.setPreferredSize(new Dimension(150, 150));

        JLabel j = new JLabel("Choix de l'entreprise");
        j.setFont(new Font("TimesRoman", Font.BOLD, 14));

        map.get("requestgetListBuilding").put("company_name", company_name);
        String responses = getSend("requestgetListBuilding");
        String[] building = responses.split("@");
        for (String b : building) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
            //System.out.println(b);
        }
        listBuiling = new JComboBox(building);

        listBuiling.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) listBuiling.getSelectedItem();
                // System.out.println(str);
                map.get("request_id_building").put("name_building", str);
                String response = getSend("request_id_building");
                listFloor.removeAllItems();
                String[] idbuilding = response.split("@");
                for (String b : idbuilding) {
                    if (b.contains("@")) {
                        b.replace("@", "");
                    }
                    // System.out.println(b);
                }
                map.get("requestFloor").put("company_name", company_name);
                map.get("requestFloor").put("building_name", str);
                response = getSend("requestFloor");
                String[] floor = response.split("@");
                for (String b : floor) {
                    if (b.contains("@")) {
                        b.replace("@", "");
                    }
                    //  System.out.println(b);
                }
                for (String elem : floor) {
                    listFloor.addItem(elem);
                }
            }
        });

        listBuiling.setBounds(1000, 1000, 20000, 1000);
        listBuiling.setSize(200, 200);
        panel.add(j);
        panel.add(listBuiling);

        JLabel j3 = new JLabel("          Etage            ");
        j.setFont(new Font("TimesRoman", Font.BOLD, 14));
        listFloor.setBounds(1000, 1000, 20000, 1000);
        listFloor.setSize(200, 200);
        panel.add(j3);
        panel.add(listFloor);
        listFloor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("Je suis l'étage");
                String floorvalue = (String) e.getItem();
                String buildingvalue = (String) listBuiling.getSelectedItem();

                map.get("requestRoom").put("company_name", company_name);
                map.get("requestRoom").put("floor_name", floorvalue);
                map.get("requestRoom").put("building_name", buildingvalue);

                String response = getSend("requestRoom");
                System.out.println(map.get("requestRoom") );

                String[] room = response.split("@");
                for (String b : room) {
                    if (b.contains("@")) {
                        b.replace("@", "");
                    }
                    //  System.out.println(b);
                }
                listRoom.removeAllItems();

                for (String elem : room) {
                    listRoom.addItem(elem);
                }

            }
        });

        JLabel j1 = new JLabel("Choix de la salle");
        j.setFont(new Font("TimesRoman", Font.BOLD, 14));

        listRoom.setBounds(1000, 1000, 20000, 1000);
        listRoom.setSize(200, 200);
        panel.add(j1);
        panel.add(listRoom);
        listRoom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String roomValue = (String) e.getItem();
                System.out.println(e.getItem());
                String name_room = (String) listRoom.getSelectedItem();

                map.get("requestGetIdRoom").put("name_room", name_room);
                String responseGetIdRoom = getSend("requestGetIdRoom");
                String[] answersIdRoom = responseGetIdRoom.split("@");
                for (String b : answersIdRoom) {
                    if (b.contains("@")) {
                        b.replace("@", "");
                    }
                    System.out.println(b);
                }
                id_room = answersIdRoom[0];


                getContentPane().add(panels);
                if (roomValue.contains("Bureau") | roomValue.contains("Petite salle")) {
                    panelBureau.revalidate();
                    frame.repaint();
                    cardLayout.show(panels, "panelBureau");

                }
                if (roomValue.contains("Salle de conférence") | roomValue.contains("Salle ouverte")) {
                    panelRoom.revalidate();
                    cardLayout.show(panels, "panelRoom");
                    frame.repaint();


                }

            }
        });

        getContentPane().add(panel, BorderLayout.WEST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
