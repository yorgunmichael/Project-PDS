package MenuInterface;

import IhmMapping.WindowsMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class WindowsMenu extends JFrame {
    private JPanel panelCenter = new JPanel();
    private JPanel panel;
    private Side_Menu sm;
    private JButton home;
    private  JButton card;
    private  JButton mapping;
    private  JButton indicator;
    private  JButton location;
    private  JButton logout;
    private String company_name;


    public WindowsMenu(String companyName) {
        this.setVisible(true);
        this.setTitle("Page d'accueil");
        this.setSize(676, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.company_name = companyName;
        sm = new Side_Menu(companyName, this);
        //the general panel
        panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());
        sm.initPanel();

        initHome();
        initCard();
        initMapping();
        initIndicator();
        initLocation();
        initLogOut();
















        sm.add(sm.getPanelNorth(), BorderLayout.NORTH);
        sm.add(sm.getPanelSouth(), BorderLayout.SOUTH);
        // the left panel contains the menu
        panel.add(BorderLayout.WEST, sm);


        panel.add(BorderLayout.CENTER, optionCenteredPanel());

        if(!companyName.equals("Mairie"))
            indicator.setEnabled(false);
        else if (logout.getActionCommand().equals("LogOut"))
            this.dispose();

    }

    private void initLogOut() {
        logout = new JButton("Déconnexion");
        logout.setPreferredSize(new Dimension(170, 23));
        logout.setVerticalAlignment(SwingConstants.TOP);
        logout.setVisible(true);

        logout.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        logout.setForeground(SystemColor.desktop);
        logout.setBackground(SystemColor.inactiveCaption);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(10.0f);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Home();
            }
        });
        sm.getPanelSouth().add(logout);
    }

    private void initLocation() {
        location = new JButton("Location");
        location.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        location.setForeground(SystemColor.activeCaptionText);
        location.setBackground(SystemColor.inactiveCaption);
        location.setPreferredSize(new Dimension(170, 23));
        location.setVerticalAlignment(SwingConstants.TOP);
        location.setAlignmentY(10.0f);
        location.setAlignmentX(10.0f);


        location.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((JButton) e.getSource() == location) {
                }
                // new ExecLoc(company_name);

            }

        });

        sm.getPanelNorth().add(location);
    }

    private void initIndicator() {
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
                if ((JButton) e.getSource() == indicator) {
                }
                // new Indicator(company_name);
            }

        });
        sm.getPanelNorth().add(indicator);
    }

    private void initMapping() {
        mapping = new JButton("Mapping");
        mapping.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        mapping.setForeground(SystemColor.activeCaptionText);
        mapping.setBackground(SystemColor.inactiveCaption);
        mapping.setPreferredSize(new Dimension(170, 23));
        mapping.setVerticalAlignment(SwingConstants.TOP);
        mapping.setAlignmentY(10.0f);
        mapping.setAlignmentX(10.0f);


        mapping.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((JButton) e.getSource() == mapping) {
                    new WindowsMapping(company_name);
                    dispose();

                }

            }

        });

        sm.getPanelNorth().add(mapping);
    }

    private void initCard() {
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
        sm.getPanelNorth().add(card);
    }

    private void initHome() {
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
        sm.getPanelNorth().add(home);
    }

    public String getCompany_name() {
        return company_name;
    }

    private JPanel optionCenteredPanel() {

        panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(centerTopPanel(), BorderLayout.NORTH);

        // adding image icon here
        URL url =  Thread.currentThread().getContextClassLoader().getResource("city.jpg");
        ImageIcon image = new ImageIcon(url);
        JLabel labImge = new JLabel();
        labImge.setIcon(image);
        panelCenter.add(labImge, BorderLayout.CENTER);


        panelCenter.setBackground(SystemColor.inactiveCaptionBorder);
        panelCenter.add(centerDownPanel(), BorderLayout.SOUTH);
        return panelCenter;
    }

    private JPanel centerTopPanel() {
        JPanel top = new JPanel();
        top.setSize(new Dimension(50, 50));
        top.setForeground(SystemColor.inactiveCaption);
        top.setPreferredSize(new Dimension(10, 50));

        JLabel label = new JLabel("Bienvenue sur TeckWork! Votre application de location");
        label.setFont(new Font("Serif", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(400, 30));
        top.add(label);


        top.setBackground(Color.WHITE);
        return top;
    }

    private JPanel centerDownPanel() {
        JPanel down = new JPanel();
        down.setPreferredSize(new Dimension(10, 50));

        JLabel footer = new JLabel("� Copyright TeckWork 2020-2021");
        footer.setPreferredSize(new Dimension(200, 20));
        footer.setFont(new Font("Serif", Font.PLAIN, 12));

        down.add(footer);
        return down;

    }


    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    public static void main(String[] args) {
        new WindowsMenu(null);
    }
}
