package View.Estoque;

import Components.FuncionarioComboBox;
import Components.ProdutoComboBox;
import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewSubtrair extends JFrame {


    private final ProdutoComboBox productCb;
    private final JTextField qntField;
    private final FuncionarioComboBox funcionarioComboBox;


    public ViewSubtrair(Client client) throws IOException {

        super("Register Clients");

        JLabel funcionarioCbLabel = new JLabel("Funcionário:");
        funcionarioComboBox = new FuncionarioComboBox(client);
        funcionarioComboBox.setFont(funcionarioComboBox.getFont().deriveFont(16f));
        JLabel productCbLabel = new JLabel("Produto:");
        productCb = new ProdutoComboBox(client, 2);
        productCb.setFont(productCb.getFont().deriveFont(16f));
        JLabel qntLabel = new JLabel("Quantidade: ");
        qntField = new JTextField(20);
        qntField.setFont(qntField.getFont().deriveFont(16f));


        JButton registerButton = new JButton("Comprar");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            String txt = "";
//                    "entity;BUY;" + cpfField.getText().replace(";", "") + ";"
//                    + funcionarioComboBox.getId() + ";"
//                    + productCb.getId() + ";"
//                    + qntField.getText().replace(";", "") + ";";
            System.out.println(txt);
            try {
                showMessageDialog(this, client.write(txt));
            } catch (IOException ex) {
                showMessageDialog(this, ex.getMessage());
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(funcionarioCbLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(funcionarioComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(productCbLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(productCb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(qntLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(qntField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("compraprodutos");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        ViewSubtrair v = new ViewSubtrair(new Client("localhost", 80, 9999));
        v.setVisible(true);
    }
}
