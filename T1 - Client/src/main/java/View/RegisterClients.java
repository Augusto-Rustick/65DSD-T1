package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterClients extends JFrame {

    private JTextField cpfField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton registerButton;

    public RegisterClients() {
        super("My Form");

        // Create the labels and text fields for the form fields
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        cpfField.setFont(cpfField.getFont().deriveFont(16f)); // Increase the font size
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setFont(nameField.getFont().deriveFont(16f)); // Increase the font size
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setFont(addressField.getFont().deriveFont(16f)); // Increase the font size
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phoneField.setFont(phoneField.getFont().deriveFont(16f)); // Increase the font size
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setFont(emailField.getFont().deriveFont(16f)); // Increase the font size

        // Create the register button
        registerButton = new JButton("Register");
        registerButton.setFont(registerButton.getFont().deriveFont(16f)); // Increase the font size

        // Create a panel to hold the form fields and the register button using a GridBagLayout
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

        // Add the form panel to the JFrame
        getContentPane().setName("registraclientes");
        add(formPanel);

        // Set JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
    }

    public static void main(String[] args) {
        RegisterClients form = new RegisterClients();
        form.setVisible(true);
    }
}
