package resources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIF {
    private JPanel Home;
    private JButton welcomeToBlackJackButton;
    private JPanel bgpanel;
    private JButton hitButton;
    private JButton stayButton;
    private JButton SurrenderButton;
    private JButton DoubleButton;

    public GUIF() {
//        welcomeToBlackJackButton.addActionListener(new ActionListener() { //action listener for buttons to work
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null,"Hello Players");
//
//            }
//        });
        bgpanel = new JPanel();

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hit");
            }
        });
        stayButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                System.out.println("Stay");
            }
        });
        SurrenderButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                System.out.println("Bet");

            }
        });
        DoubleButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                System.out.println("Double");
            }
        });
    }

    public static void createGui(){
        GUIF gui = new GUIF();
        JFrame frame = new JFrame("BlackJack");
        frame.setContentPane(gui.bgpanel);
        frame.add(gui.hitButton);
        frame.add(gui.stayButton);
        frame.add(gui.SurrenderButton);
        frame.add(gui.DoubleButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

        public void main(String[] args) {
        GUIF myWindow = new GUIF();
        myWindow.createGui();
    }
}
