package resources;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BustedWindow {

	private JFrame frmBusted;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player testPlayer = new Player(1);
					BustedWindow window = new BustedWindow(testPlayer);
					window.frmBusted.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BustedWindow(Player BustedPlayer) {
		initialize(BustedPlayer);
	}
	
	public void setVisible(boolean set) {
		frmBusted.setVisible(set);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Player player) {
		frmBusted = new JFrame();
		frmBusted.setTitle("Busted");
		frmBusted.setBounds(100, 100, 450, 300);
		frmBusted.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnClose = new JButton("Close");
		frmBusted.getContentPane().add(btnClose, BorderLayout.SOUTH);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBusted.dispose();
			}
		});
		
		
		String text = "Player " + player.playernumer + " has busted";
		JLabel lblBusted = new JLabel(text, SwingConstants.CENTER);
		frmBusted.getContentPane().add(lblBusted, BorderLayout.CENTER);
	}

}
