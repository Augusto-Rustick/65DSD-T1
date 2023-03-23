package View.Estoque;

import Components.Departamento;
import Components.Pessoa;
import Components.PessoaComboBox;
import Components.PessoaTipo;
import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ViewVenderEstoque extends JFrame {


    private final JComboBox<Departamento> productCb;
    private final JTextField qntField;
    private final PessoaComboBox funcionarioComboBox;
    private final JButton registerButton;

    public ViewVenderEstoque(Client client) throws IOException {

        JLabel funcionarioCbLabel = new JLabel("Funcionário:");
        funcionarioComboBox = new PessoaComboBox(client, PessoaTipo.FUNCIONARIO);
        funcionarioComboBox.setFont(funcionarioComboBox.getFont().deriveFont(16f));
        JLabel productCbLabel = new JLabel("Produto:");
        productCb = new JComboBox<Departamento>();
        productCb.setFont(productCb.getFont().deriveFont(16f));
        JLabel qntLabel = new JLabel("Quantidade: ");
        qntField = new JTextField(20);
        qntField.setFont(qntField.getFont().deriveFont(16f));
        registerButton = new JButton("Retirar");
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

        getContentPane().setName("Vender estoque");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarEventoAoComboBoxFuncionario(Client client) {
        ViewCarregarEstoque.prencherProdutos(client, funcionarioComboBox, productCb);
    }

    private void adiconarEventoProcessarReq(Client client) {
        registerButton.addActionListener(e -> {
            Departamento dep = (Departamento) productCb.getSelectedItem();
            Pessoa f = (Pessoa) funcionarioComboBox.getSelectedItem();
            if (dep == null) {
                showMessageDialog(this, "O funcionário selecionado não está em nenhum departamento");
            }
            assert f != null;
            assert dep != null;
            int transaction = dep.getQuantidadeEstoque() - Integer.parseInt(qntField.getText());
            int employeeStock = f.getQuantidadeVendas() + Integer.parseInt(qntField.getText());
            if (transaction >= 0) {
                String updateEmployee = "funcionario;UPDATE;" + f.getCpf() + ";" + f.getNome() + ";" + f.getEndereco()
                        + ";" + f.getCtps() + ";" + employeeStock + ";" + f.getDepartamento() + ";" + f.getId() + ";";
                String updateDepartment = "departamento;UPDATE;" + dep.getNome() + ";" + dep.getProduto() + ";" + transaction + ";" + dep.getId();
                try {
                    client.write(updateEmployee);
                    f.setQuantidadeVendas(employeeStock);
                    client.write(updateDepartment);
                    dep.setQuantidadeEstoque(transaction);
                    showMessageDialog(this, "O funcionário " + f.getNome() + " fez " + employeeStock + " vendas!");
                } catch (IOException ex) {
                    showMessageDialog(this, ex.getMessage());
                }
            } else {
                showMessageDialog(this, "O departamento não possui estoque!");
            }
        });
    }
}
