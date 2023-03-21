package View.Cliente;

import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewRegistroClientes extends JFrame {

    private final JTextField cpfField;
    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextField phoneField;
    private final JTextField emailField;

    public ViewRegistroClientes(Client client) {

        super("Register Clients");

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        cpfField.setFont(cpfField.getFont().deriveFont(16f));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setFont(nameField.getFont().deriveFont(16f));
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setFont(addressField.getFont().deriveFont(16f));
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phoneField.setFont(phoneField.getFont().deriveFont(16f));
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setFont(emailField.getFont().deriveFont(16f));

        JButton registerButton = new JButton("Register");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            String txt = "cliente;INSERT;" + cpfField.getText() + ";" + nameField.getText() + ";" + addressField.getText() + ";" + phoneField.getText() + ";" + emailField.getText() + ";";
            try {
                showMessageDialog(this, client.write(txt));
            } catch (IOException ex) {
                showMessageDialog(this, ex.getMessage()); ;
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
        formPanel.add(cpfField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("registraclientes");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
