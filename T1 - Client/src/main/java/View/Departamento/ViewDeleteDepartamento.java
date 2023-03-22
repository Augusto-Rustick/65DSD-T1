package View.Departamento;

import Socket.Client;
import View.Transportador.ViewDeleteTransportador;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewDeleteDepartamento extends JFrame {

    private final JTextField nomeDeptField;
    private JSONObject myjson;

    public ViewDeleteDepartamento(Client client) {

        JLabel cpfLabel = new JLabel("Departamento :");
        nomeDeptField = new JTextField(20);
        nomeDeptField.setFont(nomeDeptField.getFont().deriveFont(16f));
        JLabel returnLabel = new JLabel("Resultado:");

        JButton registerButton = new JButton("Delete");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            try {
                String txt = "departamento;GET;" + nomeDeptField.getText() + ";";
                String response = client.write(txt);
                myjson = new JSONObject(response);
            } catch (JSONException je) {
                nomeDeptField.setText("Não encontrado!");
                myjson = new JSONObject("{'nome':'','produto':'','quantidadeEstoque':''}");
            } catch (IOException ex) {
                Logger.getLogger(ViewDeleteTransportador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    String txt = "departamento;DELETE;" + myjson.get("id").toString();
                    showMessageDialog(this, client.write(txt));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

        getContentPane().setName("Remover departamentos");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

}
