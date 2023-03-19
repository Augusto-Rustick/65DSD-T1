package View.Funcionario;

import Socket.Client;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewUpdateFuncionarios extends JFrame {

    private final JTextField cpfField;
    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextField ctpsField;

    public ViewUpdateFuncionarios(Client client) {

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        cpfField.setFont(cpfField.getFont().deriveFont(16f));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setFont(nameField.getFont().deriveFont(16f));
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setFont(addressField.getFont().deriveFont(16f));
        JLabel ctpsLabel = new JLabel("CTPS:");
        ctpsField = new JTextField(20);
        ctpsField.setFont(addressField.getFont().deriveFont(16f));

        JButton findButton = new JButton("Search");
        findButton.setFont(findButton.getFont().deriveFont(16f));
        JButton registerButton = new JButton("Register");
        registerButton.setEnabled(false);

        findButton.addActionListener(e->{
            String txt = "Departmanto;GET;"+cpfField.getText();
            try {
                String response = client.write(txt);
                JSONObject myjson;
                try{
                    myjson = new JSONObject(response);
                }catch(JSONException je){
                    cpfField.setText("Não encontrado!");
                    myjson = new JSONObject("{'nome':'','endereco':'','ctps':''}");
                }

                nameField.setText(myjson.get("nome").toString());
                addressField.setText(myjson.get("endereco").toString());
                ctpsField.setText(myjson.get("ctps").toString());
                registerButton.setEnabled(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e->{
            String txt = cpfField.getText() + ";" + nameField.getText() + ";" + addressField.getText() + ";" +  this.ctpsField.getText();
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
        formPanel.add(ctpsLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ctpsField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(findButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("updatefuncs");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
