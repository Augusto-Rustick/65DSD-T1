package View.Estoque;

import Components.Departamento;
import Components.Pessoa;
import Components.PessoaComboBox;
import Components.PessoaTipo;
import Socket.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewCarregarEstoque extends JFrame {


    private final JComboBox<Departamento> productCb;
    private final JTextField qntField;
    private final PessoaComboBox funcionarioComboBox;
    private final JButton registerButton;

    public ViewCarregarEstoque(Client client) throws IOException {

        JLabel funcionarioCbLabel = new JLabel("Transportador:");
        funcionarioComboBox = new PessoaComboBox(client, PessoaTipo.TRANSPORTADOR);
        funcionarioComboBox.setFont(funcionarioComboBox.getFont().deriveFont(16f));
        JLabel productCbLabel = new JLabel("Produto:");
        productCb = new JComboBox<Departamento>();
        productCb.setFont(productCb.getFont().deriveFont(16f));
        JLabel qntLabel = new JLabel("Quantidade: ");
        qntField = new JTextField(20);
        qntField.setFont(qntField.getFont().deriveFont(16f));
        registerButton = new JButton("Entregar");
        registerButton.setFont(registerButton.getFont().deriveFont(16f));

        adicionarEventoAoComboBoxFuncionario(client);
        adiconarEventoProcessarReq(client);

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

        getContentPane().setName("Adicionar estoque");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarEventoAoComboBoxFuncionario(Client client) {
        prencherProdutos(client, funcionarioComboBox, productCb);
    }

    static void prencherProdutos(Client client, PessoaComboBox funcionarioComboBox, JComboBox<Departamento> productCb) {
        funcionarioComboBox.addActionListener(e -> {
            String response = null;
            try {
                response = "{ data: " + client.write("Departamento;LIST") + "}";
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JSONObject json = new JSONObject(response);
            ObjectMapper objectMapper = new ObjectMapper();
            Departamento[] list;
            try {
                list = objectMapper.readValue(json.get("data").toString(), Departamento[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            ArrayList<Departamento> deps = new ArrayList<>(List.of(list));
            productCb.removeAllItems();
            for (Departamento dep : deps) {
                if (funcionarioComboBox.getDepId() == dep.getId())
                    productCb.addItem(dep);
            }
        });
    }

    private void adiconarEventoProcessarReq(Client client) {
        registerButton.addActionListener(e -> {
            Departamento dep = (Departamento) productCb.getSelectedItem();
            Pessoa t = (Pessoa) funcionarioComboBox.getSelectedItem();
            if (dep == null) {
                showMessageDialog(this, "O transportador selecionado não está em nenhum departamento");
            }
            assert t != null;
            assert dep != null;
            int transaction = dep.getQuantidadeEstoque() + Integer.parseInt(qntField.getText());
            int transporterLoad = t.getCarregamento() - Integer.parseInt(qntField.getText());
            if (transporterLoad >= 0) {
                String updateEmployee = "transportador;UPDATE;" + t.getCpf() + ";" + t.getNome() + ";" + t.getEndereco()
                        + ";" + t.getTelefone() + ";" + transporterLoad + ";" + t.getDepartamento() + ";" + t.getId() + ";";
                String updateDepartment = "departamento;UPDATE;" + dep.getNome() + ";" + dep.getProduto() + ";" + transaction + ";" + dep.getId();
                try {
                    client.write(updateEmployee);
                    t.setCarregamento(transporterLoad);
                    client.write(updateDepartment);
                    dep.setQuantidadeEstoque(transaction);
                    showMessageDialog(this, "O departamento " + dep.getNome() + " agora tem " + transaction + " em estoque!");
                } catch (IOException ex) {
                    showMessageDialog(this, ex.getMessage());
                }
            } else {
                showMessageDialog(this, "O transportador não possui estoque o suficiente!");
            }
        });
    }
}
