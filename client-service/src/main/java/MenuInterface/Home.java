package MenuInterface;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static client.Client.getSend;
import static client.Client.map;

public class Home {
    private String[] element = {"mairie", "service", "pouvooirs", "publics", "location"};
    private JComboBox comboboxCompany;
    private WindowsMenu newWindow;
    private JFrame myFrame = new JFrame();



    public Home() {

        myFrame.setVisible(true);
        myFrame.setTitle("page d'accueil");
        myFrame.setSize(700, 400);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);


        JPanel contentPane = (JPanel) myFrame.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));

        String company_id = "1";
        map.get("requestCompany").put("company_id", company_id);

        String responses = getSend("requestCompany");
        String[] company = responses.split("@");
        for (String b : company) {
            if (b.contains("@")) {
                b.replace("@", "");
            }
            System.out.println(b);
        }

        comboboxCompany = new JComboBox(company);

        comboboxCompany.setPreferredSize(new Dimension(70, 70));

        contentPane.add(topOptionPanel());
        contentPane.add(comboboxCompany);
        contentPane.add(southOptionPanel());
        contentPane.revalidate();

    }

    private JPanel southOptionPanel() {
        JPanel southPanel = new JPanel(new FlowLayout());
        JButton ValidateButon = new JButton("Valider");
        ValidateButon.setPreferredSize(new Dimension(100, 70));
        ;
        ValidateButon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                newWindow = new WindowsMenu((String) comboboxCompany.getSelectedItem());
                myFrame.dispose();


            }
        });
        southPanel.add(ValidateButon);

        JButton cancelButon = new JButton("Annuler");
        cancelButon.setPreferredSize(new Dimension(100, 70));
        cancelButon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Vous avez annul� la session");
                myFrame.dispose();
            }
        });
        southPanel.add(cancelButon);
        southPanel.setBackground(Color.white);


        return southPanel;
    }

    private JPanel topOptionPanel() {
        JPanel topPanel = new JPanel();
        JLabel topMessage = new JLabel("Veuillez choisir une option");
        topMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        topMessage.setAlignmentY(Component.CENTER_ALIGNMENT);
        topMessage.setFont(new Font("Sylfaen", Font.CENTER_BASELINE, 20));
        topPanel.setBackground(Color.white);
        topPanel.add(topMessage);
        return topPanel;
    }



}
