package View;

import javax.swing.*;
import java.awt.*;

public class ProductRegistrationFrame extends JFrame {

    public ProductRegistrationFrame() {
        setTitle("Product Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a form layout
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add fields for product name, stock quantity, and price
        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Stock Quantity:");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(priceLabel);
        panel.add(priceField);

        // Create a button for registering the product
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            // TODO: Register the product using the input fields
            JOptionPane.showMessageDialog(this, "Product registered successfully!");
        });

        // Add the button to the panel
        panel.add(new JPanel());
        panel.add(registerButton);

        // Add the panel to the center of the frame
        getContentPane().add(panel, BorderLayout.CENTER);

        // Pack and show the frame
        pack();
    }

    public static void main(String[] args) {
        new ProductRegistrationFrame();
    }
}
