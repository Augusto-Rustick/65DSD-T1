package View;

import View.Components.CustomJMenuItem;

import javax.swing.*;
import java.awt.*;

public class ConnectionFrame extends JFrame {

    private void handleClientOptions(JMenuBar menuBar, JPanel panel) {

        JMenu clientOptions = new JMenu("Client");

        clientOptions.add(new CustomJMenuItem(this, panel, "New", new RegisterClients().getContentPane()));
        clientOptions.add(new CustomJMenuItem(this, panel, "See one", new BuyProducts().getContentPane()));
        clientOptions.add(new CustomJMenuItem(this, panel, "See all", new BuyProducts().getContentPane()));
        clientOptions.add(new CustomJMenuItem(this, panel, "Update", new BuyProducts().getContentPane()));
        clientOptions.add(new CustomJMenuItem(this, panel, "Delete", new BuyProducts().getContentPane()));

        menuBar.add(clientOptions);

    }

    public ConnectionFrame() {

        setTitle("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));

        JPanel contentPane = new JPanel(new GridBagLayout());
        JMenuBar menuBar = new JMenuBar();

        handleClientOptions(menuBar, contentPane);
        //..

        setJMenuBar(menuBar);

        getContentPane().setName("connecting");
        getContentPane().add(contentPane, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        new ConnectionFrame();
    }
}
