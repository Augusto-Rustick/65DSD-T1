package View.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TabButton extends JButton {


    private final Color enabledColor = new Color(50, 150, 255);
    private final Color disabledColor = new Color(136, 136, 136, 15);
    private final Color foreColor = new Color(255, 255, 255);


    public TabButton(String text, Dimension dimension, Color bg) {
        setText(text);
        setPreferredSize(dimension);
        setBackground(bg);
        setForeground(foreColor);
    }

    public TabButton(String text) {
        setText(text);
        setPreferredSize(new Dimension(100, 30));
        setBackground(enabledColor);
        setForeground(foreColor);
    }

    public TabButton(JFrame root, JPanel father, String text, Container child) {
        setText(text);
        setPreferredSize(new Dimension(100, 30));
        setBackground(enabledColor);
        setForeground(foreColor);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!root.getName().equals(child.getName())) {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.fill = GridBagConstraints.CENTER;
                    for (Component c : father.getComponents()
                    ) {
                        father.remove(c);
                    }
                    father.add(child, gbc);
                    father.revalidate();
                    father.repaint();
                }
                root.revalidate();
                root.repaint();
                root.setName(child.getName());
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            setBackground(enabledColor);
        } else {
            setBackground(disabledColor);
            setForeground(foreColor);
        }
    }


}
