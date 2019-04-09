package resources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIF {
    private JPanel Home;
    private JButton welcomeToBlackJackButton;

    public GUIF() {
        welcomeToBlackJackButton.addActionListener(new ActionListener() { //action listener for buttons to work
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello Players");

            }
        });
    }
        public static void main(String[] args) {
        JFrame frame = new JFrame("GUIF");
        frame.setContentPane(new GUIF().welcomeToBlackJackButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
