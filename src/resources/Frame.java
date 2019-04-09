package resources;

import javax.swing.JFrame;

public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;

    public static int width = 800, height = 600;

    public Frame() {
        super("Blackjack V0");

        // You can set the content pane of the frame to your custom class.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        add(new DrawPane());
        setFocusable(true);
        requestFocus();
    }

}