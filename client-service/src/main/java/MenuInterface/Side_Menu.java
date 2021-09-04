package MenuInterface;

import IhmMapping.WindowsMapping;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Side_Menu extends JPanel {


    private JLabel companyLabel;
    private JSeparator separator;

    private String company_name;

    private JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    private JPanel panelSouth = new JPanel(new FlowLayout());


    public Side_Menu(String company_name, JFrame frame) {
        this.company_name = company_name;
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(68, 114, 196));
        this.setPreferredSize(new Dimension(222, 500));
        this.setLayout(new BorderLayout());

    }

    public JPanel getPanelNorth() {
        return panelNorth;
    }

    public void setPanelNorth(JPanel panelNorth) {
        this.panelNorth = panelNorth;
    }

    public JPanel getPanelSouth() {
        return panelSouth;
    }

    public void setPanelSouth(JPanel panelSouth) {
        this.panelSouth = panelSouth;
    }

    /************ using two panel to implement the side_menu ones top and other down ***********/
    public void initPanel() {
        panelNorth();
        panelSouth();
    }

    private void panelNorth() {

        panelNorth.setPreferredSize(new Dimension(10, 300));
        panelNorth.setBackground(new Color(68, 114, 196));


        companyLabel = new JLabel();
        companyLabel.setBackground(Color.BLUE);
        companyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        companyLabel.setPreferredSize(new Dimension(200, 100));
        companyLabel.setForeground(Color.BLACK);
        companyLabel.setFont(new Font("Serif", Font.BOLD, 20));
        companyLabel.setText("Teck-work");
        panelNorth.add(companyLabel);


        separator = new JSeparator();
        separator.setOpaque(true);

        separator.setBounds(new Rectangle(10, 10, 10, 10));
        separator.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        separator.setBackground(Color.BLACK);
        separator.setAlignmentX(20.0f);
        separator.setAlignmentY(Component.TOP_ALIGNMENT);
        separator.setPreferredSize(new Dimension(215, 3));
        separator.setForeground(Color.BLACK);

        panelNorth.add(separator);


    }

    /************ down panel which contains only the deconnection button ****************/

    private void panelSouth() {

        panelSouth.setBackground(new Color(68, 114, 196));

        setBackground(new Color(68, 114, 196));


    }


}
