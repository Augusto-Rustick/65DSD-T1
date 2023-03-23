package View.Departamento;

import Socket.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewConsultaDepartamento extends JFrame {

    private final JTextField nomeDeptField;
    private final JTextArea returnField;

    public ViewConsultaDepartamento(Client client) {

        JLabel cpfLabel = new JLabel("Departamento :");
        nomeDeptField = new JTextField(20);
        nomeDeptField.setFont(nomeDeptField.getFont().deriveFont(16f));
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
            if (!nomeDeptField.getText().equals("")) {
                txt = "departamento;GET;" + nomeDeptField.getText() + ";";
            } else {
                txt = "departamento;LIST";
            }
            try {
                String response = client.write(txt);
                if (nomeDeptField.getText().equals("")) {
                    this.renderList(response, client);
                } else {
                    this.renderGet(response, client);
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
        formPanel.add(nomeDeptField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(returnLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(scrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("Conuslta de Departamentos");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void renderList(String response, Client client) {
        if (response.equalsIgnoreCase("[]")) {
            returnField.setText("List: \n 0");
        } else {
            JSONArray jsonArray = new JSONArray(response.toString());
            String texto = "List: \nQuantidade:" + jsonArray.length() + " \n \n";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                texto += "Departamento " + (i + 1) + ": \n"
                        + jsonObject.get("nome").toString()
                        + ", " + jsonObject.get("produto").toString()
                        + ", " + jsonObject.get("quantidadeEstoque").toString()
                        + this.getAllPessoaOfDepartamento(jsonObject.get("id").toString(), client) + "\n \n";
            }
            returnField.setText(texto);
        }
    }

    private void renderGet(String response, Client client) {
        JSONObject myjson = new JSONObject(response);
        if (myjson.has("id")) {
            String texto = "GET:" + " \n \n";
            returnField.setText(response);
        } else {
            returnField.setText("Nenhum registro encontrado com esse nome");
        }
    }

    private String getAllPessoaOfDepartamento(String departamentoId, Client client) {
        try {
            String requesting = "pessoa;List;" + departamentoId;
            String response = client.write(requesting);

            JSONArray jsonArrayPessoa = new JSONArray(response.toString());

            String texto = "\nPessoas:" + jsonArrayPessoa.length() + " \nPessoas:\n";
            for (int i = 0; i < jsonArrayPessoa.length(); i++) {
                JSONObject jsonObject = jsonArrayPessoa.getJSONObject(i);
                texto += "Pessoa " + (i + 1) + ": \n"
                        + jsonObject.get("cpf").toString()
                        + ", " + jsonObject.get("nome").toString() + "\n";
            }

            return texto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
