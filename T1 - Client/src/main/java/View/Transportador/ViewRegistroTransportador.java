package View.Transportador;

import Socket.Client;
import Components.DepartamentoComboBox;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewRegistroTransportador extends JFrame {

    private final JTextField cpfField;
    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextField phoneField;
    private final JTextField carregamentoField;
    private final DepartamentoComboBox departmentCb;


    public ViewRegistroTransportador(Client client) throws IOException {

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
        JLabel carregamentoLabel = new JLabel("Carregamento:");
        carregamentoField = new JTextField(20);
        carregamentoField.setFont(carregamentoField.getFont().deriveFont(16f));
        JLabel departmentCbLabel = new JLabel("Departamento:");
        departmentCb = new DepartamentoComboBox(client);
        departmentCb.setFont(departmentCb.getFont().deriveFont(16f));


        JButton registerButton = new JButton("Register");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            String txt = "transportador;INSERT;" + cpfField.getText().replace(";", "") + ";"
                    + nameField.getText().replace(";", "") + ";"
                    + addressField.getText().replace(";", "") + ";"
                    + phoneField.getText().replace(";", "") + ";"
                    + carregamentoField.getText().replace(";", "") + ";"
                    + departmentCb.getId() + ";";
            System.out.println(txt);
            try {
                showMessageDialog(this, client.write(txt));
            } catch (IOException ex) {
                showMessageDialog(this, ex.getMessage());
                ;
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
        formPanel.add(carregamentoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(carregamentoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(departmentCbLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(departmentCb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("Registrar tranportadores");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
