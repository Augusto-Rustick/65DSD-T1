package View;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuyProducts extends JFrame {

    private JComboBox<Supplier> supplierComboBox;
    private JComboBox<Product> productComboBox;
    private JTextField stockField;
    private JButton buyButton;

    private Supplier[] suppliers = {
            new Supplier("Supplier 1", new Product[]{new Product("Product A", 10), new Product("Product B", 20)}),
            new Supplier("Supplier 2", new Product[]{new Product("Product C", 30), new Product("Product D", 40)})
    };

    public BuyProducts() {
        super("My Form");

        // Create the supplier and product combo boxes
        supplierComboBox = new JComboBox<>(suppliers);
        supplierComboBox.addActionListener(e -> {
            Supplier selectedSupplier = (Supplier) supplierComboBox.getSelectedItem();
            productComboBox.removeAllItems();
            for (Product p : selectedSupplier.getProducts()) {
                productComboBox.addItem(p);
            }
        });
        productComboBox = new JComboBox<>();

        // Create the stock field (read-only)
        stockField = new JTextField(10);
        stockField.setEditable(false);

        // Create the buy button
        buyButton = new JButton("Buy");

        // Set font sizes for the form fields and button
        Font font = new Font("Arial", Font.PLAIN, 18);
        supplierComboBox.setFont(font);
        productComboBox.setFont(font);
        stockField.setFont(font);
        buyButton.setFont(font);

        // Create a panel to hold the form fields using a GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(new JLabel("Supplier:"), gbc);
        gbc.gridx = 1;
        formPanel.add(supplierComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Product:"), gbc);
        gbc.gridx = 1;
        formPanel.add(productComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1;
        formPanel.add(stockField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buyButton, gbc);

        // Add the form panel to the JFrame
        getContentPane().setName("comprarprodutos");
        add(formPanel);

        // Set JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
    }

    private class Supplier {
        private String name;
        private Product[] products;

        public Supplier(String name, Product[] products) {
            this.name = name;
            this.products = products;
        }

        public String toString() {
            return name;
        }

        public Product[] getProducts() {
            return products;
        }
    }

    private class Product {
        private String name;
        private int stock;

        public Product(String name, int stock) {
            this.name = name;
            this.stock = stock;
        }

        public String toString() {
            return name;
        }

        public int getStock() {
            return stock;
        }
    }

    public static void main(String[] args) {
        BuyProducts form = new BuyProducts();
        form.setVisible(true);
    }
}


