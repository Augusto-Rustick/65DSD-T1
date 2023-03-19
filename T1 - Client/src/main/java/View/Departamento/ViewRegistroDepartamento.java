package View.Departamento;

import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewRegistroDepartamento extends JFrame {

    private final JTextField nomeField;
    private final JTextField produtoField;
    private final JTextField qntProdutoFiled;

    public ViewRegistroDepartamento(Client client) {

        JLabel cpfLabel = new JLabel("Nome departamento :");
        nomeField = new JTextField(20);
        nomeField.setFont(nomeField.getFont().deriveFont(16f));
        JLabel nameLabel = new JLabel("Produto :");
        produtoField = new JTextField(20);
        produtoField.setFont(produtoField.getFont().deriveFont(16f));
        JLabel addressLabel = new JLabel("Quantidade :");
        qntProdutoFiled = new JTextField(20);
        qntProdutoFiled.setFont(qntProdutoFiled.getFont().deriveFont(16f));

        JButton registerButton = new JButton("Register");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e->{
            String txt = "departamento;INSERT;"+nomeField.getText() + ";" + produtoField.getText() + ";" + qntProdutoFiled.getText() + ";";
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
        formPanel.add(nomeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(produtoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(qntProdutoFiled, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("registradeps");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
