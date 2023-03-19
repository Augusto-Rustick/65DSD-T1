package View.Funcionario;

import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewDeleteFuncionario extends JFrame {

    private final JTextField cpfField;

    public ViewDeleteFuncionario(Client client) {

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        cpfField.setFont(cpfField.getFont().deriveFont(16f));
        JLabel returnLabel = new JLabel("Resultado:");

        JButton registerButton = new JButton("Delete");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            String txt = "Funcionario;DELETE;"+cpfField.getText()+";";
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
        formPanel.add(cpfField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("deletefuncs");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

}
