package MenuInterface;

import IhmMapping.WindowsMapping;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Side_Menu extends JPanel {

        private JButton home;
        private  JButton card;
        private  JButton mapping;
        private  JButton indicator;
        private  JButton location;
        private  JButton deconnexion;
        private  JLabel companyLabel;
        private  JSeparator separator;

        private String company_name;


        public Side_Menu(String company_name, JFrame frame) {
            this.company_name = company_name;
            this.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.setBackground(new Color(68, 114, 196));
            this.setPreferredSize(new Dimension(222, 500));
            this.setLayout(new BorderLayout());
            add(panelNorth(), BorderLayout.NORTH);
            add(panelSouth(frame), BorderLayout.SOUTH);

        }

        /************ using two panel to implement the side_menu ones top and other down ***********/

        private JPanel panelNorth() {
            JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panelNorth.setPreferredSize(new Dimension(10, 300));
            panelNorth.setBackground(new Color(68, 114, 196));



            companyLabel  = new JLabel();
            companyLabel.setBackground(Color.BLUE);
            companyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            companyLabel.setPreferredSize(new Dimension(200, 100));
            companyLabel.setForeground(Color.BLACK);
            companyLabel.setFont(new Font("Serif", Font.BOLD, 20));
            companyLabel.setText("Teck-work");
            panelNorth.add(companyLabel);


            separator  = new JSeparator();
            separator.setOpaque(true);

            separator.setBounds(new Rectangle(10, 10, 10, 10));
            separator.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            separator.setBackground(Color.BLACK);
            separator.setAlignmentX(20.0f);
            separator.setAlignmentY(Component.TOP_ALIGNMENT);
            separator.setPreferredSize(new Dimension(215, 3));
            separator.setForeground(Color.BLACK);

            panelNorth.add(separator);

            home = new JButton("Accueil");
            home.setPreferredSize(new Dimension(170, 23));
            home.setVerticalAlignment(SwingConstants.TOP);
            home.setVisible(true);

            home.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            home.setForeground(SystemColor.desktop);
            home.setBackground(SystemColor.inactiveCaption);
            home.setAlignmentX(Component.CENTER_ALIGNMENT);
            home.setAlignmentX(10.0f);
            home.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                }
            });
            home.setVerticalAlignment(SwingConstants.TOP);
            home.setAlignmentY(10.0f);
            home.setAlignmentX(10.0f);
            panelNorth.add(home);

            card = new JButton("Carte");
            card.setPreferredSize(new Dimension(170, 23));
            card.setVerticalAlignment(SwingConstants.TOP);
            card.setVisible(true);

            card.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            card.setForeground(SystemColor.desktop);
            card.setBackground(SystemColor.inactiveCaption);
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.setAlignmentX(10.0f);
            card.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //new Card(company_name);

                }
            });
            card.setVerticalAlignment(SwingConstants.TOP);
            card.setAlignmentY(10.0f);
            card.setAlignmentX(10.0f);
            panelNorth.add(card);

            mapping  = new JButton("Mapping");
            mapping.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            mapping.setForeground(SystemColor.activeCaptionText);
            mapping.setBackground(SystemColor.inactiveCaption);
            mapping.setPreferredSize(new Dimension(170, 23));
            mapping.setVerticalAlignment(SwingConstants.TOP);
            mapping.setAlignmentY(10.0f);
            mapping.setAlignmentX(10.0f);


            mapping.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ((JButton) e.getSource() == mapping)
                        new WindowsMapping(company_name);

                }

            });

            panelNorth.add(mapping);

            indicator = new JButton("Indicateur");
            indicator.setPreferredSize(new Dimension(170, 23));
            indicator.setVerticalAlignment(SwingConstants.TOP);


            indicator.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            indicator.setForeground(SystemColor.desktop);
            indicator.setBackground(SystemColor.inactiveCaption);
            indicator.setAlignmentX(Component.CENTER_ALIGNMENT);
            indicator.setAlignmentX(10.0f);
            indicator.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if ((JButton) e.getSource() == indicator) {}
                      //  new Indicator(company_name);
                }

            });
            panelNorth.add(indicator);

            location  = new JButton("Location");
            location.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            location.setForeground(SystemColor.activeCaptionText);
            location.setBackground(SystemColor.inactiveCaption);
            location.setPreferredSize(new Dimension(170, 23));
            location.setVerticalAlignment(SwingConstants.TOP);
            location.setAlignmentY(10.0f);
            location.setAlignmentX(10.0f);


            location.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ((JButton) e.getSource() == location) {}
                       // new ExecLoc(company_name);

                }

            });

            panelNorth.add(location);

            return panelNorth;
        }

        /************ down panel which contains only the deconnection button ****************/

        private JPanel panelSouth(JFrame frame) {
            JPanel panelSouth = new JPanel(new FlowLayout());
            panelSouth.setBackground(new Color(68, 114, 196));

            setBackground(new Color(68, 114, 196));

            deconnexion = new JButton("Déconnexion");
            deconnexion.setPreferredSize(new Dimension(170, 23));
            deconnexion.setVerticalAlignment(SwingConstants.TOP);
            deconnexion.setVisible(true);

            deconnexion.setFont(new Font("Sylfaen", Font.PLAIN, 14));
            deconnexion.setForeground(SystemColor.desktop);
            deconnexion.setBackground(SystemColor.inactiveCaption);
            deconnexion.setAlignmentX(Component.CENTER_ALIGNMENT);
            deconnexion.setAlignmentX(10.0f);
            deconnexion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new Home();
                }
            });
            panelSouth.add(deconnexion);
            return panelSouth;
        }

        public JButton getIndicator() {
            return indicator;
        }

        public JButton getDeconnexion() {
            return deconnexion;
        }
}
