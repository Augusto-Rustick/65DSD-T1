package View.Departamento;

import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewDeleteDepartamento extends JFrame {

    private final JTextField nomeDeptField;

    public ViewDeleteDepartamento(Client client) {

        JLabel cpfLabel = new JLabel("Departamento :");
        nomeDeptField = new JTextField(20);
        nomeDeptField.setFont(nomeDeptField.getFont().deriveFont(16f));
        JLabel returnLabel = new JLabel("Resultado:");

        JButton registerButton = new JButton("Delete");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            String txt = "Departamento;DELETE;"+ nomeDeptField.getText()+";";
            try {
                showMessageDialog(this, client.write(txt));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(cpfLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomeDeptField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("deletedeps");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

}
