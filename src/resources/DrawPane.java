package resources;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

// Create a component that you can actually draw on.
public class DrawPane extends JPanel {
    private static final long serialVersionUID = 1L;
    public int width = 800, height = 600;

    public Listening listening;

    public DrawPane() {
        //listeners and other things in need of instantiation go here
        listening = new Listening();
        addMouseListener(listening);
        addMouseMotionListener(listening);
        addMouseWheelListener(listening);
        addKeyListener(listening);

    }

    public void paintComponent(Graphics g) {
        tick();

        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void tick() {
        
    }
}
