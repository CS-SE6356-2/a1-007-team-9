package resources;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GameWindow {

	protected JFrame frmBlackjack;
	protected JLabel outputLabel;
	protected JLabel handLabel;
	protected JLabel player1Info; //label for the player game stats like score and revealed card
	protected JLabel player2Info;
	protected JLabel player3Info;
	protected JLabel player4Info;
	protected JLabel dealerHandLabel; //dealer's hand info
	protected JLabel roundLabel;
	protected JLabel playerHandSumLabel;
	
	
	//game methods
	public void setOutput(String output) {
		this.outputLabel.setText(output);
	}
	public void setPlayer1Info(String info) {
		this.player1Info.setText("<html>" + info + "</html>");
	}
	public void setPlayer2Info(String info) {
		this.player2Info.setText("<html>" + info + "</html>");
	}
	public void setPlayer3Info(String info) {
		this.player3Info.setText("<html>" + info + "</html>");
	}
	public void setPlayer4Info(String info) {
		this.player4Info.setText("<html>" + info + "</html>");
	}
	public void setDealerHand(String hand) {
		this.dealerHandLabel.setText("<html><div style='text-align: center;'>"+ hand +"</div></html>");
	}
	public void setPlayerHand(String hand) {
		this.handLabel.setText("<html>" + hand + "</html>");
	}
	public void setRoundLabel(String round) {
		this.roundLabel.setText(round);
	}
	public void setHandSumLabel(String sum) {
		this.playerHandSumLabel.setText(sum);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frmBlackjack.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBlackjack = new JFrame();
		frmBlackjack.setResizable(false);
		frmBlackjack.setTitle("BlackJack");
		frmBlackjack.setBounds(100, 100, 720, 530);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnHit = new JButton("HIT");
		btnHit.setBounds(60, 369, 75, 29);
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.out.println("hit");
	             Player p = Game_Main.getCurrentPlayer();
	             Game_Main.hit(p, Game_Main.deck);
			}
		});
		frmBlackjack.getContentPane().setLayout(null);
		frmBlackjack.getContentPane().add(btnHit);
		
		JButton btnStay = new JButton("STAY");
		btnStay.setBounds(215, 369, 76, 29);
		frmBlackjack.getContentPane().add(btnStay);
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Stay");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.stand(p, Game_Main.deck);
			}
		});
		
		
		JButton btnSurrender = new JButton("SURRENDER");
		btnSurrender.setBounds(388, 369, 118, 29);
		frmBlackjack.getContentPane().add(btnSurrender);
		btnSurrender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("surrender");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.surrender(p, Game_Main.deck);
			}
		});
		
		JButton btnDouble = new JButton("DOUBLE");
		btnDouble.setBounds(585, 369, 94, 29);
		frmBlackjack.getContentPane().add(btnDouble);
		btnDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Double");
                Player p = Game_Main.getCurrentPlayer();
                Game_Main.doublebet(p, Game_Main.deck);
			}
		});
		
		outputLabel = new JLabel("Title");
		outputLabel.setBounds(60, 6, 174, 36);
		frmBlackjack.getContentPane().add(outputLabel);
		
		handLabel = new JLabel("Hand");
		handLabel.setBounds(190, 423, 286, 42);
		frmBlackjack.getContentPane().add(handLabel);
		
		JLabel lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setBounds(60, 121, 61, 16);
		frmBlackjack.getContentPane().add(lblPlayer1);
		
		JLabel lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setBounds(215, 121, 61, 16);
		frmBlackjack.getContentPane().add(lblPlayer2);
		
		JLabel lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setBounds(415, 121, 61, 16);
		frmBlackjack.getContentPane().add(lblPlayer3);
		
		JLabel lblPlayer = new JLabel("Player 4");
		lblPlayer.setBounds(598, 121, 61, 16);
		frmBlackjack.getContentPane().add(lblPlayer);
		
		player1Info = new JLabel("");
		player1Info.setBounds(30, 150, 130, 190);
		frmBlackjack.getContentPane().add(player1Info);
		
		player2Info = new JLabel("");
		player2Info.setBounds(190, 150, 130, 190);
		frmBlackjack.getContentPane().add(player2Info);
		
		player3Info = new JLabel("");
		player3Info.setBounds(375, 150, 130, 190);
		frmBlackjack.getContentPane().add(player3Info);
		
		player4Info = new JLabel("");
		player4Info.setBounds(560, 150, 130, 190);
		frmBlackjack.getContentPane().add(player4Info);
		
		JLabel lblDealer = new JLabel("Dealer");
		lblDealer.setBounds(302, 54, 100, 16);
		frmBlackjack.getContentPane().add(lblDealer);
		
		dealerHandLabel = new JLabel("");
		dealerHandLabel.setBounds(295, 73, 107, 36);
		frmBlackjack.getContentPane().add(dealerHandLabel);
		
		JButton btnPassScreen = new JButton("Pass Screen");
		btnPassScreen.setBounds(6, 443, 117, 29);
		frmBlackjack.getContentPane().add(btnPassScreen);
		
		JLabel lblPassTheScreen = new JLabel("Pass the screen to the next player");
		lblPassTheScreen.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblPassTheScreen.setBounds(6, 423, 220, 16);
		frmBlackjack.getContentPane().add(lblPassTheScreen);
		
		JLabel lblYourHand = new JLabel("Your Hand");
		lblYourHand.setBounds(190, 407, 85, 16);
		frmBlackjack.getContentPane().add(lblYourHand);
		
		JLabel lblRoundTitle = new JLabel("Round: ");
		lblRoundTitle.setBounds(560, 16, 48, 16);
		frmBlackjack.getContentPane().add(lblRoundTitle);
		
		roundLabel = new JLabel("0");
		roundLabel.setBounds(620, 16, 61, 16);
		frmBlackjack.getContentPane().add(roundLabel);
		
		JLabel lblHandSum = new JLabel("Hand Sum:");
		lblHandSum.setBounds(520, 448, 68, 16);
		frmBlackjack.getContentPane().add(lblHandSum);
		
		playerHandSumLabel = new JLabel("0");
		playerHandSumLabel.setBounds(598, 448, 61, 16);
		frmBlackjack.getContentPane().add(playerHandSumLabel);
		
		
	}
}
