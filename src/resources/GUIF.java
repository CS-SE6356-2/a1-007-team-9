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
        bgpanel = new JPanel();

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hit");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.hit(p,Game_Main.deck);
            }
        });
        stayButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                System.out.println("Stay");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.stand(p,Game_Main.deck);
            }
        });
        SurrenderButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                System.out.println("surrender");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.surrender(p,Game_Main.deck);
            }
        });
        DoubleButton.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                System.out.println("Double");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.doublebet(p,Game_Main.deck);
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

        public void main(String[] args) {
        GUIF myWindow = new GUIF();
        myWindow.createGui();
    }
}
