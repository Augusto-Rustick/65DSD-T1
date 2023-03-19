package View.Departamento;

import Socket.Client;

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
            String txt = "departamento;GET;"+ nomeDeptField.getText()+";";
            try {
                returnField.setText(client.write(txt));
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

        getContentPane().setName("consultadeps");
        add(formPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

}
