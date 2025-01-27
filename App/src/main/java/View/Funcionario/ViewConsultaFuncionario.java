package View.Funcionario;

import Socket.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewConsultaFuncionario extends JFrame {

    private final JTextField cpfField;
    private final JTextArea returnField;

    public ViewConsultaFuncionario(Client client) {

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        cpfField.setFont(cpfField.getFont().deriveFont(16f));
        JLabel returnLabel = new JLabel("Resultado:");
        returnField = new JTextArea(5, 20);
        returnField.setLineWrap(true);
        returnField.setWrapStyleWord(true);
        returnField.setFont(returnField.getFont().deriveFont(16f));
        JScrollPane scrollPane = new JScrollPane(returnField);

        JButton registerButton = new JButton("Search");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e -> {
            String txt;
            if (!cpfField.getText().equals("")) {
                txt = "funcionario;GET;" + cpfField.getText() + ";";
            } else {
                txt = "funcionario;LIST";
            }
            try {
                String response = client.write(txt);
                if (cpfField.getText().equals("")) {
                    this.renderList(response);
                } else {
                    this.renderGet(response);
                }
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
        formPanel.add(returnLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(scrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("Consultar funcionários");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void renderList(String response) {
        if (response.equalsIgnoreCase("[]")) {
            returnField.setText("List: \n 0");
        } else {
            JSONArray jsonArray = new JSONArray(response.toString());
            String texto = "List: \nQuantidade:" + jsonArray.length() + " \n \n";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                texto += "Funcionario " + (i + 1) + ": \n"
                        + jsonObject.get("cpf").toString()
                        + ", " + jsonObject.get("nome").toString()
                        + ", " + jsonObject.get("endereco").toString() + "\n \n";
            }
            returnField.setText(texto);
        }
    }

    private void renderGet(String response) {
        JSONObject myjson = new JSONObject(response);
        if (myjson.has("id")) {
            String texto = "GET:" + " \n \n"
                    + myjson.get("cpf").toString() + "\n"
                    + myjson.get("nome").toString() + "\n"
                    + myjson.get("endereco").toString();
            returnField.setText(texto);
        } else {
            returnField.setText("Nenhum registro encontrado com esse cpf");
        }
    }

}
