package View.Departamento;

import Socket.Client;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewUpdateDepartamento extends JFrame {

    private final JTextField idField;
    private final JTextField nomeField;
    private final JTextField produtoField;
    private final JTextField qntProdutoFiled;

    public ViewUpdateDepartamento(Client client) {

        idField = new JTextField(20);
        idField.setVisible(false);
        JLabel cpfLabel = new JLabel("Nome departamento :");
        nomeField = new JTextField(20);
        nomeField.setFont(nomeField.getFont().deriveFont(16f));
        JLabel nameLabel = new JLabel("Produto :");
        produtoField = new JTextField(20);
        produtoField.setFont(produtoField.getFont().deriveFont(16f));
        JLabel addressLabel = new JLabel("Quantidade :");
        qntProdutoFiled = new JTextField(20);
        qntProdutoFiled.setFont(qntProdutoFiled.getFont().deriveFont(16f));

        JButton findButton = new JButton("Search");
        findButton.setFont(findButton.getFont().deriveFont(16f));
        JButton registerButton = new JButton("Register");
        registerButton.setEnabled(false);

        findButton.addActionListener(e->{
            String txt = "departamento;GET;"+nomeField.getText()+";";
            try {
                String response = client.write(txt);
                JSONObject myjson;
                try{
                    myjson = new JSONObject(response);
                }catch(JSONException je){
                    nomeField.setText("Não encontrado!");
                    myjson = new JSONObject("{'produto':'','quantidade':''}");
                }

                idField.setText(myjson.get("id").toString());
                produtoField.setText(myjson.get("produto").toString());
                qntProdutoFiled.setText(myjson.get("quantidadeEstoque").toString());
                registerButton.setEnabled(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        registerButton.addActionListener(e->{
            String txt = "departamento;UPDATE;"+nomeField.getText() + ";" + produtoField.getText() + ";" + qntProdutoFiled.getText() + ";"+idField.getText();
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
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(findButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        getContentPane().setName("Atualizar departamentos");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
