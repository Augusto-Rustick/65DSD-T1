package View.Transportador;

import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewDeleteTransportador extends JFrame {

    private final JTextField cpfField;
    private JSONObject myjson;

    public ViewDeleteTransportador(Client client) {

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        cpfField.setFont(cpfField.getFont().deriveFont(16f));
        JLabel returnLabel = new JLabel("Resultado:");

        JButton registerButton = new JButton("Delete");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            try {
                String txt = "transportador;GET;" + cpfField.getText() + ";";
                String response = client.write(txt);
                myjson = new JSONObject(response);
            } catch (JSONException je) {
                cpfField.setText("Não encontrado!");
                myjson = new JSONObject("{'nome':'','endereco':'','telefone':'','carregamento':''}");
            } catch (IOException ex) {
                Logger.getLogger(ViewDeleteTransportador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(myjson.has("id")){
                        String txt = "transportador;DELETE;" + myjson.get("id").toString();
                        showMessageDialog(this, client.write(txt));
                    }else{
                        showMessageDialog(this, "Não foi possível encontrar um transportador com esse cpf para deletar");
                    }

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
        formPanel.add(cpfField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("Remover tranportadores");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
