package View;

import View.Transportador.ViewConsultaTransportador;
import View.Transportador.ViewDeleteTransportador;
import View.Transportador.ViewRegistroTransportador;
import View.Transportador.ViewUpdateTransportador;
import Components.CustomJMenuItem;
import Socket.Client;
import View.Compra.ViewCompra;
import View.Departamento.ViewConsultaDepartamento;
import View.Departamento.ViewDeleteDepartamento;
import View.Departamento.ViewRegistroDepartamento;
import View.Departamento.ViewUpdateDepartamento;
import View.Funcionario.ViewConsultaFuncionario;
import View.Funcionario.ViewDeleteFuncionario;
import View.Funcionario.ViewRegistroFuncionarios;
import View.Funcionario.ViewUpdateFuncionarios;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class App extends JFrame {

    JMenu transportadorOptions = new JMenu("Transportador");
    JMenu funcionarioOptions = new JMenu("Funcionario");
    JMenu departamentoOptions = new JMenu("Departamento");
    JMenu comprarOptions = new JMenu("Comprar");
    Client client;

    private void handleTransportadorOptions(JMenuBar menuBar, JPanel panel) throws IOException {

        transportadorOptions = new JMenu("Transportador");

        transportadorOptions.add(new CustomJMenuItem(this, panel, "New", new ViewRegistroTransportador(client).getContentPane()));
        transportadorOptions.add(new CustomJMenuItem(this, panel, "See one", new ViewConsultaTransportador(client).getContentPane()));
        transportadorOptions.add(new CustomJMenuItem(this, panel, "Update", new ViewUpdateTransportador(client).getContentPane()));
        transportadorOptions.add(new CustomJMenuItem(this, panel, "Delete", new ViewDeleteTransportador(client).getContentPane()));
        menuBar.add(transportadorOptions);

    }

    private void handleFuncionarioOptions(JMenuBar menuBar, JPanel panel) throws IOException {

        funcionarioOptions = new JMenu("Funcionario");

        funcionarioOptions.add(new CustomJMenuItem(this, panel, "New", new ViewRegistroFuncionarios(client).getContentPane()));
        funcionarioOptions.add(new CustomJMenuItem(this, panel, "See one", new ViewConsultaFuncionario(client).getContentPane()));
        funcionarioOptions.add(new CustomJMenuItem(this, panel, "Update", new ViewUpdateFuncionarios(client).getContentPane()));
        funcionarioOptions.add(new CustomJMenuItem(this, panel, "Delete", new ViewDeleteFuncionario(client).getContentPane()));
        menuBar.add(funcionarioOptions);

    }

    private void handleDepartamentoOptions(JMenuBar menuBar, JPanel panel) {

        departamentoOptions = new JMenu("Departamento");

        departamentoOptions.add(new CustomJMenuItem(this, panel, "New", new ViewRegistroDepartamento(client).getContentPane()));
        departamentoOptions.add(new CustomJMenuItem(this, panel, "See one", new ViewConsultaDepartamento(client).getContentPane()));
        departamentoOptions.add(new CustomJMenuItem(this, panel, "Update", new ViewUpdateDepartamento(client).getContentPane()));
        departamentoOptions.add(new CustomJMenuItem(this, panel, "Delete", new ViewDeleteDepartamento(client).getContentPane()));
        menuBar.add(departamentoOptions);

    }

    private void handleBuyOptions(JMenuBar menuBar, JPanel panel) throws IOException {

        comprarOptions = new JMenu("Comprar");

        comprarOptions.add(new CustomJMenuItem(this, panel, "Produto", new ViewCompra(client).getContentPane()));
        menuBar.add(comprarOptions);

    }

    public void addConnectionButton(JMenuBar menuBar, JPanel contentPane) {
        JButton button = new JButton("Connect");
        button.addActionListener(e -> {
            try {
                client = new Client("localhost", 80, 9999);
                handleTransportadorOptions(menuBar, contentPane);
                handleFuncionarioOptions(menuBar, contentPane);
                handleDepartamentoOptions(menuBar, contentPane);
                handleBuyOptions(menuBar, contentPane);
                revalidate();
                repaint();
                button.setVisible(false);
                contentPane.remove(button);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setText("Connect");
        contentPane.add(button);
    }

    public App() {

        setTitle("Connection");
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));

        JPanel contentPane = new JPanel(new GridBagLayout());
        JMenuBar menuBar = new JMenuBar();

        addConnectionButton(menuBar, contentPane);

        setJMenuBar(menuBar);

        getContentPane().setName("connecting");
        getContentPane().add(contentPane, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

}
