package MenuInterface;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WindowsMenu extends JFrame {
    private JPanel panelCenter = new JPanel();
    private JPanel panel;
    private Side_Menu sm;


    public WindowsMenu(String companyName) {
        this.setVisible(true);
        this.setTitle("Page d'accueil");
        this.setSize(676, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        sm = new Side_Menu(companyName, this);
        //the general panel
        panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

        // the left panel contains the menu
        panel.add(BorderLayout.WEST, sm);


        panel.add(BorderLayout.CENTER, optionCenteredPanel());

        if(!companyName.equals("Mairie"))
            sm.getIndicator().setEnabled(false);
        else if (sm.getDeconnexion().getActionCommand().equals("Deconnexion"))
            this.dispose();

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
