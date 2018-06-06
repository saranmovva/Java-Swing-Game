package edu.ben.groupproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	/**
	 * @author Ryan Yanes
	 * 
	 *         These are all class variables that are used to display Player
	 *         info and options.
	 */
	MirskyTrail game = new MirskyTrail();
	private JPanel gridPane;
	private JPanel player1Pane;
	private JPanel player2Pane;
	private JPanel gameOptions;
	private JLabel p1OptionsLbl = new JLabel("Player 1");
	private JLabel p2OptionsLbl = new JLabel("Player 2");
	private static JButton[][] btns = new JButton[21][39];
	private static JButton p1SuppBtn = new JButton("Buy Supplies");
	private static JButton p2SuppBtn = new JButton("Buy Supplies");
	private static JButton p1MoveBtn = new JButton("Move");
	private static JButton p2MoveBtn = new JButton("Move");
	private static JButton p1SecurityBtn = new JButton("Buy Security");
	private static JButton p2SecurityBtn = new JButton("Buy Security");
	private static JButton p1CrewBtn = new JButton("Hire Crew");
	private static JButton p2CrewBtn = new JButton("Hire Crew");
	private static JButton p1RefillBtn = new JButton("Refill");
	private static JButton p2RefillBtn = new JButton("Refill");
	private static JButton p1StealBtn = new JButton("Steal Supplies");
	private static JButton p2StealBtn = new JButton("Steal Supplies");
	private static JButton p1EndTurnBtn = new JButton("End Turn");
	private static JButton p2EndTurnBtn = new JButton("End Turn");
	private static JButton restartBtn = new JButton("Restart");
	private static JButton playGameBtn = new JButton("Play");
	private static JButton endGameBtn = new JButton("Quit");
	private JTextField p1Funds = new JTextField("0");
	private JTextField p2Funds = new JTextField("0");
	private JTextField p1Supplies = new JTextField("0");
	private JTextField p2Supplies = new JTextField("0");
	private JTextField p1Crew = new JTextField("0");
	private JTextField p2Crew = new JTextField("0");
	private JTextField p1Security = new JTextField("0");
	private JTextField p2Security = new JTextField("0");
	private final JLabel lblFundsP1 = new JLabel("Funds:");
	private final JLabel lblFundsP2 = new JLabel("Funds:");
	private final JLabel lblSuppliesP1 = new JLabel("Supplies: $1");
	private final JLabel lblSuppliesP2 = new JLabel("Supplies: $1");
	private final JLabel lblCrewP1 = new JLabel("Crew: $4");
	private final JLabel lblCrewP2 = new JLabel("Crew: $4");
	private final JLabel lblSecurityP1 = new JLabel("Security: $2");
	private final JLabel lblSecurityP2 = new JLabel("Security: $2");
	private static int player1NumOfTrns = 0;
	private static int player2NumOfTrns = 0;
	private static int timesPlayed = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		// JFrame frame = new JFrame();
		setVisible(true);
		setSize(1200, 720);
		getContentPane().setLayout(new BorderLayout());
		gridPane = new JPanel();
		gridPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gridPane.setLayout(new GridLayout(21, 39, 0, 0));
		gridPane.setBounds(0, 0, 360, 360);
		setTitle("The Mirsky Trail");
		/* I create all the JButtons * */
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 39; j++) {
				btns[i][j] = new JButton(" ");
				// Fixes three dots in button problem
				btns[i][j].setMargin(new Insets(0, 0, 0, 0));
				gridPane.add(btns[i][j]);
			}
		}

		getContentPane().add(gridPane, BorderLayout.CENTER);
		updateData();

		/** Player 1 actionListeners */
		player1Pane = new JPanel();
		player1Pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Box p1Options = Box.createVerticalBox();
		p1SuppBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase Supplies");
				if (game.getPlayer1().getMoney() >= 1) {
					String temp = JOptionPane.showInputDialog(frame,
							"How many supplies do you want to buy");
					// Prevents an error from happening when you click the
					// cancel button.
					if (temp == null || "".equals(temp.trim())) {
						return;
					}
					int quantity = Integer.parseInt(temp);
					boolean temp2 = game.purchase(1, "Supplies", quantity);
					GameLog.fileWrite("Player One buys " + quantity
							+ " Supplies");
					if (temp2) {
						JOptionPane.showMessageDialog(frame,
								"Purchase Successful");
						GameLog.fileWrite("Player One: Purchase Successful");
					} else {
						JOptionPane.showMessageDialog(frame,
								"Purchase Unsuccessful Not enough funds");
						GameLog.fileWrite("Player One: Purchase Unsuccessful Not Enough Funds");
					}
					game.getPlayer1().setBuySupplies(false);
					p1MoveBtn.setEnabled(false);
					p1StealBtn.setEnabled(false);
					p1RefillBtn.setEnabled(false);
					updateData();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Not enough funds to buy supplies.");
					GameLog.fileWrite("Player One: Not enough funds to buy supplies.");
					p1SuppBtn.setEnabled(false);
				}
			}

		});
		p1SuppBtn.setEnabled(false);
		p1SuppBtn.setSize(200, 100);
		p1CrewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase Crew");
				if (game.getPlayer1().getMoney() >= 4) {
					String temp = JOptionPane.showInputDialog(frame,
							"How many crew members do you want to buy");
					// Prevents an error from happening when you click the
					// cancel button.
					if (temp == null || "".equals(temp.trim())) {
						return;
					}
					int quantity = Integer.parseInt(temp);
					boolean temp2 = game.purchase(1, "Crew", quantity);
					GameLog.fileWrite("Player One Hires " + quantity + " Crew");
					if (temp2) {
						JOptionPane.showMessageDialog(frame,
								"Purchase Successful");
						GameLog.fileWrite("Player One: Purchase Successful");
					} else {
						JOptionPane.showMessageDialog(frame,
								"Purchase Unsuccessful Not enough funds");
						GameLog.fileWrite("Player One: Purchase Unsuccessful Not Enough Funds");
					}
					game.getPlayer1().setBuySupplies(false);
					p1MoveBtn.setEnabled(false);
					p1StealBtn.setEnabled(false);
					p1RefillBtn.setEnabled(false);
					updateData();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Not enough funds to buy Crew");
					GameLog.fileWrite("Player One: Not Enough Funds To Buy Crew");
					p1CrewBtn.setEnabled(false);
				}
			}

		});
		p1CrewBtn.setEnabled(false);
		p1CrewBtn.setSize(200, 100);
		p1SecurityBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase Security");

				if (game.getPlayer1().getMoney() >= 2) {
					String temp = JOptionPane.showInputDialog(frame,
							"How many body guards do you want to buy");
					// Prevents an error from happening when you click the
					// cancel button.
					if (temp == null || "".equals(temp.trim())) {
						return;
					}
					int quantity = Integer.parseInt(temp);
					boolean temp2 = game.purchase(1, "Guards", quantity);
					GameLog.fileWrite("Player One Buys " + quantity
							+ " Security");
					if (temp2) {
						JOptionPane.showMessageDialog(frame,
								"Purchase Successful");
						GameLog.fileWrite("Player One: Purchase Successful");
					} else {
						JOptionPane.showMessageDialog(frame,
								"Purchase Unsuccessful Not enough funds");
						GameLog.fileWrite("Player One: Purchase Unsuccessful Not Enough Funds");
					}
					game.getPlayer1().setBuySupplies(false);
					p1MoveBtn.setEnabled(false);
					p1StealBtn.setEnabled(false);
					p1RefillBtn.setEnabled(false);
					updateData();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Not enough funds to buy Security");
					GameLog.fileWrite("Player One: Not Enough Funds To Buy Security.");
					p1SecurityBtn.setEnabled(false);
				}
			}

		});

		p1RefillBtn.setEnabled(false);
		p2RefillBtn.setEnabled(false);
		p1RefillBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Gets current supplies before the refill
				int before = game.getPlayer1().getSupplies();
				// Refill in controller then uses refill class
				game.refillPlayer(game.getPlayer1());
				game.getPlayer1().setSupplies(game.getPlayer1().getSupplies());
				// Updates supplies
				updateData();
				JOptionPane.showMessageDialog(null, "You got "
						+ (game.getPlayer1().getSupplies() - before)
						+ " supplies!");
				GameLog.fileWrite("Player One Gained "
						+ (game.getPlayer1().getSupplies() - before)
						+ " Supplies");
				GameLog.fileWrite("Player One Ends Their Turn");
				game.getPlayer2().setTurn(true);
				game.getPlayer1().setTurn(false);
				// Ask Mirsky whether they can refill still on the same move
				// they go back to start.
				// game.getPlayer1().setBuySupplies(false);

				game.getPlayer1().setBuySupplies(false);
				p1SuppBtn.setEnabled(false);
				p1CrewBtn.setEnabled(false);
				p1SecurityBtn.setEnabled(false);
				player1EndTrn();
			}

		});
		p2RefillBtn.setEnabled(false);
		p2RefillBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Gets current supplies before the refill
				int before = game.getPlayer2().getSupplies();
				// Refill in controller then uses refill class
				game.refillPlayer(game.getPlayer2());
				game.getPlayer2().setSupplies(game.getPlayer2().getSupplies());
				// Updates supplies
				updateData();
				JOptionPane.showMessageDialog(null, "You got "
						+ (game.getPlayer2().getSupplies() - before)
						+ " supplies!");
				GameLog.fileWrite("Player Two Gained "
						+ (game.getPlayer2().getSupplies() - before)
						+ " Supplies");
				GameLog.fileWrite("Player Two Ends Their Turn");
				game.getPlayer2().setTurn(false);
				game.getPlayer1().setTurn(true);
				// Ask Mirsky whether they can refill still on the same move
				// they go back to start.
				// game.getPlayer2().setBuySupplies(false);

				game.getPlayer2().setBuySupplies(false);
				p2SuppBtn.setEnabled(false);
				p2CrewBtn.setEnabled(false);
				p2SecurityBtn.setEnabled(false);
				player2EndTrn();
			}

		});
		p1SecurityBtn.setEnabled(false);
		p1SecurityBtn.setSize(40, 20);
		p1MoveBtn.setEnabled(false);
		p1MoveBtn.setSize(40, 20);
		p1StealBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Corey
				// Rolls two die and puts them into the steal method in the
				// controller
				// The steal method then determines whether or not the player
				// was successful in stealing

				// If the players guards are <=0 then they should not be allowed
				// to steal
				if (game.getPlayer1().getGuards() <= 0) {
					JOptionPane.showMessageDialog(null,
							"Not enough security to steal supplies!");
					GameLog.fileWrite("Player One: Not Enough Security To Steal Supplies");
					return;
				}
				int p1Multiplier = game.roll() * game.getPlayer1().getGuards();
				int p2Multiplier = game.roll() * game.getPlayer2().getGuards();
				// If the rolls are tied, it cancels the steal and you have to
				// hit the steal button again
				JOptionPane.showMessageDialog(null,
						"Player One's multiplier is " + p1Multiplier + "\n"
								+ "Player Two's multiplier is " + p2Multiplier);
				int prevSupplies = game.getPlayer1().getSupplies();
				if (game.steal(p1Multiplier, p2Multiplier, 1)) {
					JOptionPane.showMessageDialog(null, "Steal Successful"
							+ "\n" + "Amount of Supplies Stolen: "
							+ (game.getPlayer1().getSupplies() - prevSupplies));
					GameLog.fileWrite("Steal Successful, Player One Stole "
							+ (game.getPlayer1().getSupplies() - prevSupplies)
							+ " Supplies");

				} else if (p1Multiplier == p2Multiplier) {
					JOptionPane.showMessageDialog(null,
							"Rolls tied, please try again");
					return;
				} else {

					JOptionPane.showMessageDialog(null,
							"Steal Unsuccessful. One Secturity Guard Lost");
					GameLog.fileWrite("Player One: Steal Unsuccessful. One Security Guard Lost");
				}
				GameLog.fileWrite("Player One Ends Their Turn");
				updateData();
				player1EndTrn();
				p1SuppBtn.setEnabled(false);
				p1CrewBtn.setEnabled(false);
				p1SecurityBtn.setEnabled(false);
				game.getPlayer1().setBuySupplies(false);
				game.getPlayer2().setTurn(true);
				game.getPlayer1().setTurn(false);
			}
		});
		p1StealBtn.setEnabled(false);
		p1StealBtn.setSize(40, 20);
		p1Options.add(p1OptionsLbl);
		p1Options.add(p1SuppBtn);
		p1Options.add(p1CrewBtn);
		p1Options.add(p1SecurityBtn);
		p1Options.add(p1MoveBtn);
		p1Options.add(p1RefillBtn);
		p1Options.add(p1StealBtn);
		p1EndTurnBtn.setEnabled(false);
		p1EndTurnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// @ Ryan Yanes
				// Changes the turn from player 1 to player2.
				game.getPlayer1().setTurn(false);
				game.getPlayer2().setTurn(true);

				if (p1RefillBtn.isEnabled() && !p1SuppBtn.isEnabled()
						&& !p1CrewBtn.isEnabled() && !p1SecurityBtn.isEnabled()
						&& !p1MoveBtn.isEnabled() && !p1StealBtn.isEnabled()) {

				} else {
					game.getPlayer1().setBuySupplies(false);
				}
				GameLog.fileWrite("Player One Ends Their Turn");
				player1EndTrn();

			}
		});
		p1Options.add(p1EndTurnBtn);

		p1Options.add(lblFundsP1);
		p1Funds.setEditable(false);
		p1Options.add(p1Funds);

		p1Options.add(lblSuppliesP1);
		p1Supplies.setEditable(false);
		p1Options.add(p1Supplies);

		p1Options.add(lblCrewP1);
		p1Crew.setEditable(false);
		p1Options.add(p1Crew);

		p1Options.add(lblSecurityP1);
		p1Security.setEditable(false);
		p1Options.add(p1Security);

		player1Pane.add(p1Options);
		getContentPane().add(player1Pane, BorderLayout.WEST);

		/** Creating buttons for Player 2 */
		player2Pane = new JPanel();
		player2Pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Box p2Options = Box.createVerticalBox();
		p2Options.add(p2OptionsLbl);
		p2SuppBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase Supplies");

				if (game.getPlayer2().getMoney() >= 1) {
					String temp = JOptionPane.showInputDialog(frame,
							"How many supplies do you want to buy");
					// Prevents an error from happening when you click the
					// cancel button.
					if (temp == null || "".equals(temp.trim())) {
						return;
					}
					int quantity = Integer.parseInt(temp);
					boolean temp2 = game.purchase(2, "Supplies", quantity);
					GameLog.fileWrite("Player Two Buys " + quantity
							+ " Supplies");
					GameLog.fileWrite("Player Two buys " + quantity
							+ " Supplies");
					if (temp2) {
						JOptionPane.showMessageDialog(frame,
								"Purchase Successful");
						GameLog.fileWrite("Player Two: Purchase Successful");
					} else {
						JOptionPane.showMessageDialog(frame,
								"Purchase Unsuccessful Not enough funds");
						GameLog.fileWrite("Player Two: Purchase Unsuccessful Not Enough Funds");
					}
					game.getPlayer2().setBuySupplies(false);
					p2MoveBtn.setEnabled(false);
					p2StealBtn.setEnabled(false);
					p2RefillBtn.setEnabled(false);
					updateData();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Not enough funds to buy Supplies.");
					GameLog.fileWrite("Player Two: Not Enough Funds To Buy Supplies.");
					p2SuppBtn.setEnabled(false);
				}
			}

		});
		p2SuppBtn.setEnabled(false);
		p2Options.add(p2SuppBtn);
		p2CrewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase Crew");
				if (game.getPlayer2().getMoney() >= 4) {
					String temp = JOptionPane.showInputDialog(frame,
							"How many crew members do you want to buy");
					// Prevents an error from happening when you click the
					// cancel button.
					if (temp == null || "".equals(temp.trim())) {
						return;
					}
					int quantity = Integer.parseInt(temp);
					boolean temp2 = game.purchase(2, "Crew", quantity);
					GameLog.fileWrite("Player Two Hires " + quantity + " Crew");
					if (temp2) {
						JOptionPane.showMessageDialog(frame,
								"Purchase Successful");
						GameLog.fileWrite("Player Two: Purchase Successful");
					} else {
						JOptionPane.showMessageDialog(frame,
								"Purchase Unsuccessful Not enough funds");
						GameLog.fileWrite("Player Two: Purchase Unsuccessful Not Enough Funds");
					}
					game.getPlayer2().setBuySupplies(false);
					p2MoveBtn.setEnabled(false);
					p2StealBtn.setEnabled(false);
					p2RefillBtn.setEnabled(false);
					updateData();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Not enough funds to buy Crew.");
					GameLog.fileWrite("Player Two: Not Enough Funds To Buy Crew.");
					p2CrewBtn.setEnabled(false);
				}
			}

		});
		p2CrewBtn.setEnabled(false);
		p2Options.add(p2CrewBtn);
		p2SecurityBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Purchase Security");

				if (game.getPlayer2().getMoney() >= 2) {
					String temp = JOptionPane.showInputDialog(frame,
							"How many body guards do you want to buy");
					// Prevents an error from happening when you click the
					// cancel button.
					if (temp == null || "".equals(temp.trim())) {
						return;
					}
					int quantity = Integer.parseInt(temp);
					boolean temp2 = game.purchase(2, "Guards", quantity);
					GameLog.fileWrite("Player Two buys " + quantity
							+ " Security");
					if (temp2) {
						JOptionPane.showMessageDialog(frame,
								"Purchase Successful");
						GameLog.fileWrite("Player Two: Purchase Successful");
					} else {
						JOptionPane.showMessageDialog(frame,
								"Purchase Unsuccessful Not enough funds");
						GameLog.fileWrite("Player Two: Purchase Unsuccessful Not Enough Funds");
					}
					game.getPlayer2().setBuySupplies(false);
					p2MoveBtn.setEnabled(false);
					p2StealBtn.setEnabled(false);
					p2RefillBtn.setEnabled(false);
					updateData();
				} else {
					JOptionPane.showMessageDialog(frame,
							"Not enough funds to buy Security.");
					GameLog.fileWrite("Player Two: Not Enough Funds To Buy Security.");
					p2SecurityBtn.setEnabled(false);
				}
			}

		});
		p2SecurityBtn.setEnabled(false);
		p2Options.add(p2SecurityBtn);
		p2MoveBtn.setEnabled(false);
		p2Options.add(p2MoveBtn);
		p2MoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2MoveBtn.setEnabled(false);
				p2StealBtn.setEnabled(false);
				JFrame frame = new JFrame("Roll");
				JOptionPane.showMessageDialog(frame, "You Just rolled a: "
						+ game.roll());
				GameLog.fileWrite("Player Two Rolled a " + game.getDieFaceVal());
				p2SuppBtn.setEnabled(false);
				p2CrewBtn.setEnabled(false);
				p2SecurityBtn.setEnabled(false);
				enableBoard(true);
				game.getPlayer2().setBuySupplies(false);
			}
		});
		p1MoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1MoveBtn.setEnabled(false);
				p1StealBtn.setEnabled(false);
				JFrame frame = new JFrame("Roll");
				JOptionPane.showMessageDialog(frame, "You Just rolled a: "
						+ game.roll());
				GameLog.fileWrite("Player One Rolled a " + game.getDieFaceVal());
				p1SuppBtn.setEnabled(false);
				p1CrewBtn.setEnabled(false);
				p1SecurityBtn.setEnabled(false);
				enableBoard(true);
				game.getPlayer1().setBuySupplies(false);
			}
		});

		p2Options.add(p2RefillBtn);
		p2StealBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Corey
				// Rolls two die and puts them into the steal method in the
				// controller
				// The steal method then determines whether or not the player
				// was successful in stealing

				// If the players guards are <=0 then they should not be allowed
				// to steal
				if (game.getPlayer2().getGuards() <= 0) {
					JOptionPane.showMessageDialog(null,
							"Not enough security to steal supplies!");
					return;
				}
				int p1Count = game.roll() * game.getPlayer1().getGuards();
				int p2Count = game.roll() * game.getPlayer2().getGuards();
				// If the rolls are tied, it cancels the steal and you have to
				// hit the steal button again
				JOptionPane.showMessageDialog(null,
						"Player One's multiplier is " + p1Count + "\n"
								+ "Player Two's multiplier is " + p2Count);
				if (p1Count == p2Count) {
					JOptionPane.showMessageDialog(null,
							"Rolls tied, please try again");
					return;

				}
				int prevSupplies = game.getPlayer2().getSupplies();
				if (game.steal(p1Count, p2Count, 2)) {
					JOptionPane.showMessageDialog(null, "Steal Successful"
							+ "\n" + "Amount of Supplies Stolen: "
							+ (game.getPlayer2().getSupplies() - prevSupplies));
					GameLog.fileWrite("Steal Successful, Player Two Stole "
							+ (game.getPlayer2().getSupplies() - prevSupplies)
							+ " Supplies");
				} else {
					JOptionPane.showMessageDialog(null,
							"Steal Unsuccessful. One Secturity Guard Lost");
					GameLog.fileWrite("Player Two: Steal Unsuccessful. One Security Guard Lost");
				}
				GameLog.fileWrite("Player Two Ends Their Turn");
				game.getPlayer2().setBuySupplies(false);
				updateData();
				player2EndTrn();
				game.getPlayer2().setTurn(false);
				game.getPlayer1().setTurn(true);
				p2SuppBtn.setEnabled(false);
				p2CrewBtn.setEnabled(false);
				p2SecurityBtn.setEnabled(false);
				p2MoveBtn.setEnabled(false);
				p2RefillBtn.setEnabled(false);
				p2StealBtn.setEnabled(false);
			}
		});
		p2StealBtn.setEnabled(false);
		p2Options.add(p2StealBtn);
		p2EndTurnBtn.setEnabled(false);
		p2EndTurnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// @ Ryan Yanes
				// Changes the turn from player 2 to player 1.
				game.getPlayer2().setTurn(false);
				game.getPlayer1().setTurn(true);
				if (p2RefillBtn.isEnabled() && !p2SuppBtn.isEnabled()
						&& !p2CrewBtn.isEnabled() && !p2SecurityBtn.isEnabled()
						&& !p2MoveBtn.isEnabled() && !p2StealBtn.isEnabled()) {

				} else {
					game.getPlayer2().setBuySupplies(false);
				}
				GameLog.fileWrite("Player Two Ends Their Turn");
				player2EndTrn();

			}
		});
		p2Options.add(p2EndTurnBtn);

		p2Options.add(lblFundsP2);
		p2Funds.setEditable(false);
		p2Options.add(p2Funds);

		p2Options.add(lblSuppliesP2);
		p2Supplies.setEditable(false);
		p2Options.add(p2Supplies);

		p2Options.add(lblCrewP2);
		p2Crew.setEditable(false);
		p2Options.add(p2Crew);

		p2Options.add(lblSecurityP2);
		p2Security.setEditable(false);
		p2Options.add(p2Security);

		player2Pane.add(p2Options);
		getContentPane().add(player2Pane, BorderLayout.EAST);

		/** Adding game options */

		gameOptions = new JPanel();
		gameOptions.setLayout(new GridLayout(0, 3));
		playGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (timesPlayed == 0) {
					createActionListeners();
					timesPlayed++;
				}
				int player1 = (int) (Math.random() * 10) + 1;
				int player2 = (int) (Math.random() * 10) + 1;
				if (player1 > player2) {
					game.getPlayer1().setTurn(true);
					game.getPlayer2().setTurn(false);
					p1CrewBtn.setEnabled(true);
					p1SecurityBtn.setEnabled(true);
					p1SuppBtn.setEnabled(true);
					p1MoveBtn.setEnabled(false);

					p1StealBtn.setEnabled(false);
					p1EndTurnBtn.setEnabled(true);
					p2SuppBtn.setEnabled(false);
					p2CrewBtn.setEnabled(false);
					p2SecurityBtn.setEnabled(false);
					p2MoveBtn.setEnabled(false);

					p2StealBtn.setEnabled(false);
					p2EndTurnBtn.setEnabled(false);
					playGameBtn.setEnabled(false);

					JOptionPane
							.showMessageDialog(null, "Player One Goes First");
					GameLog.fileWrite("Player One Goes First");
				} else {
					game.getPlayer1().setTurn(false);
					game.getPlayer2().setTurn(true);
					p1SuppBtn.setEnabled(false);
					p1CrewBtn.setEnabled(false);
					p1SecurityBtn.setEnabled(false);
					p1MoveBtn.setEnabled(false);

					p1StealBtn.setEnabled(false);
					p1EndTurnBtn.setEnabled(false);
					p2SuppBtn.setEnabled(true);
					p2CrewBtn.setEnabled(true);
					p2SecurityBtn.setEnabled(true);
					p2MoveBtn.setEnabled(false);
					p2StealBtn.setEnabled(false);
					p2EndTurnBtn.setEnabled(true);
					playGameBtn.setEnabled(false);
					JOptionPane
							.showMessageDialog(null, "Player Two Goes First");
					GameLog.fileWrite("Player Two Goes First");

				}

			}

		});
		gameOptions.add(playGameBtn);
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.restart();
				updateBoard();
				updateData();
				player1NumOfTrns = 0;
				player2NumOfTrns = 0;
				int player1 = (int) (Math.random() * 10) + 1;
				int player2 = (int) (Math.random() * 10) + 1;
				if (player1 > player2) {
					game.getPlayer1().setTurn(true);
					game.getPlayer2().setTurn(false);
					p1CrewBtn.setEnabled(true);
					p1SecurityBtn.setEnabled(true);
					p1SuppBtn.setEnabled(true);
					p1MoveBtn.setEnabled(false);

					p1StealBtn.setEnabled(false);
					p1EndTurnBtn.setEnabled(true);
					p2SuppBtn.setEnabled(false);
					p2CrewBtn.setEnabled(false);
					p2SecurityBtn.setEnabled(false);
					p2MoveBtn.setEnabled(false);

					p2StealBtn.setEnabled(false);
					p2EndTurnBtn.setEnabled(false);
					playGameBtn.setEnabled(false);

					JOptionPane
							.showMessageDialog(null, "Player One Goes First");
					GameLog.fileWrite("Player One Goes First");
				} else {
					game.getPlayer1().setTurn(false);
					game.getPlayer2().setTurn(true);
					p1SuppBtn.setEnabled(false);
					p1CrewBtn.setEnabled(false);
					p1SecurityBtn.setEnabled(false);
					p1MoveBtn.setEnabled(false);

					p1StealBtn.setEnabled(false);
					p1EndTurnBtn.setEnabled(false);
					p2SuppBtn.setEnabled(true);
					p2CrewBtn.setEnabled(true);
					p2SecurityBtn.setEnabled(true);
					p2MoveBtn.setEnabled(false);
					p2StealBtn.setEnabled(false);
					p2EndTurnBtn.setEnabled(true);
					playGameBtn.setEnabled(false);
					JOptionPane
							.showMessageDialog(null, "Player Two Goes First");
					GameLog.fileWrite("Player Two Goes First");

				}

			}
		});

		gameOptions.add(restartBtn);

		endGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Exits the game after button press and confirmation on the
				// message
				int response = JOptionPane
						.showConfirmDialog(null,
								"Are You Sure You Want to Quit?", "Exit Game",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				GameLog.fileWrite("Game Over");
				if (response == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});
		gameOptions.add(endGameBtn);

		getContentPane().add(gameOptions, BorderLayout.SOUTH);

		// @Jeff- update player position
		updatePlayerPosition();
		enableBoard(false);
		updateBoard();

	}

	private void enableBoard(boolean enable) {
		if (enable) {
			for (int i = 0; i < MirskyTrail.HEIGHT_OF_BOARD; i++) {
				for (int j = 0; j < MirskyTrail.WIDTH_OF_BOARD; j++) {
					btns[i][j].setEnabled(true);
				}
			}
		} else {
			for (int i = 0; i < MirskyTrail.HEIGHT_OF_BOARD; i++) {
				for (int j = 0; j < MirskyTrail.WIDTH_OF_BOARD; j++) {
					btns[i][j].setEnabled(false);
				}
			}
		}
	}

	/**
	 * Creates action listeners for each tile button. Each button coordinates
	 * with its own tile.
	 * 
	 * @author KING_Savaman
	 */
	private void createActionListeners() {
		for (int i = 0; i < MirskyTrail.HEIGHT_OF_BOARD; i++) {
			for (int j = 0; j < MirskyTrail.WIDTH_OF_BOARD; j++) {
				btns[i][j].setActionCommand(Integer.toString(i) + "."
						+ Integer.toString(j));
				btns[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean isValid;
						int confirmationMessage;
						int confirmationButtons;
						String[] indexes = e.getActionCommand().split("\\.");
						int x = Integer.parseInt(indexes[0]);
						int y = Integer.parseInt(indexes[1]);
						Purchase currentPlayer = game.getCurrentPlayer();
						Purchase nextPlayer = game.nextPlayer();
						if (game.gameBoard().getTile(x, y).getType()
								.equals(TileType.SUPPLYSTATION)) {
							JOptionPane
									.showMessageDialog(null,
											"You are not allowed to move onto a Supply Station.");
						} else if (!currentPlayer.equals(null)) {
							isValid = game
									.validateMovement(x, y, currentPlayer);
							if (isValid) {
								confirmationButtons = JOptionPane.YES_NO_OPTION;
								confirmationMessage = JOptionPane
										.showConfirmDialog(
												null,
												"This move will use:\n"
														+ game.printPathCost(currentPlayer),
												"Confirmation",
												confirmationButtons);
								if (confirmationMessage == JOptionPane.YES_OPTION) {

									if (currentPlayer.getName() == "Player1") {
										GameLog.fileWrite("Player One Uses: \n"
												+ game.printPathCost(currentPlayer));
										GameLog.fileWrite("Player One Ends Their Turn");
									}
									if (currentPlayer.getName() == "Player2") {
										GameLog.fileWrite("Player Two Uses: \n"
												+ game.printPathCost(currentPlayer));
										GameLog.fileWrite("Player Two Ends Their Turn");
									}
									// Play audio file
									game.trainSoundEffects();
									game.move(x, y, currentPlayer);
									// Enables P1Refill button
									if (currentPlayer.getName().equals(
											"Player1")) {
										game.enableP1Refill();
									} else {
										game.enableP2Refill();
									}

									if (game.isRefillLocation(currentPlayer)) {
										if (currentPlayer.isBuySupplies()) {
											if (currentPlayer.getName().equals(
													"Player1")) {
												p1RefillBtn.setEnabled(false);
												player1EndTrn();
											} else {
												p2RefillBtn.setEnabled(false);
												player2EndTrn();
											}
											currentPlayer.setTurn(false);
											nextPlayer.setTurn(true);
											updateBoard();

										} else {
											if (currentPlayer.getName().equals(
													"Player1")) {
												p1RefillBtn.setEnabled(true);
											} else {
												p2RefillBtn.setEnabled(true);
											}
											updateBoard();
										}
									} else {
										if (currentPlayer.getName().equals(
												"Player1")) {
											player1EndTrn();
										} else {
											player2EndTrn();
										}
										currentPlayer.setTurn(false);
										nextPlayer.setTurn(true);
										updateBoard();
									}
									updateData();
									updatePlayerPosition();
									// @Corey
									// Checks to see if the player has reached
									// the goal
									// and displays a winning message and exits
									// the game
									if (game.gameBoard()
											.getTile(currentPlayer.getRow(),
													currentPlayer.getCol())
											.getType().equals(TileType.GOAL)) {
										if (currentPlayer.getName().equals(
												"Player1")) {
											JOptionPane.showMessageDialog(null,
													"Player 1 has won!");
											GameLog.fileWrite("Player One Has Won!");
											p1SuppBtn.setEnabled(false);
											p1CrewBtn.setEnabled(false);
											p1SecurityBtn.setEnabled(false);
											p1MoveBtn.setEnabled(false);
											p1StealBtn.setEnabled(false);
											p1EndTurnBtn.setEnabled(false);
											p2SuppBtn.setEnabled(false);
											p2CrewBtn.setEnabled(false);
											p2SecurityBtn.setEnabled(false);
											p2MoveBtn.setEnabled(false);
											p2StealBtn.setEnabled(false);
											p2EndTurnBtn.setEnabled(false);
										} else {
											JOptionPane.showMessageDialog(null,
													"Player 2 has won!");
											GameLog.fileWrite("Player Two Has Won!");
											p1SuppBtn.setEnabled(false);
											p1CrewBtn.setEnabled(false);
											p1SecurityBtn.setEnabled(false);
											p1MoveBtn.setEnabled(false);
											p1StealBtn.setEnabled(false);
											p1EndTurnBtn.setEnabled(false);
											p2SuppBtn.setEnabled(false);
											p2CrewBtn.setEnabled(false);
											p2SecurityBtn.setEnabled(false);
											p2MoveBtn.setEnabled(false);
											p2StealBtn.setEnabled(false);
											p2EndTurnBtn.setEnabled(false);
										}

									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Move was canceled.\nIt is still your turn.\nYour Roll: "
													+ game.getDieFaceVal());
								}

							} else {
								if (currentPlayer.isInPath(x, y)
										|| currentPlayer.isHeadPosition(
												currentPlayer.getRow(),
												currentPlayer.getCol())) {
									confirmationButtons = JOptionPane.CLOSED_OPTION;
									JOptionPane
											.showConfirmDialog(
													null,
													"You cannot move here.\nThis move requires:\n"
															+ game.printPathCost(currentPlayer),
													"Try Again",
													confirmationButtons);
								} else {
									confirmationButtons = JOptionPane.CLOSED_OPTION;
									JOptionPane
											.showConfirmDialog(
													null,
													"You can only create new tracks from the head track!",
													"Invalid Move",
													confirmationButtons);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Game has lost track of who's turn it is.");
						}

					}
				});
			}
		}
	}

	/**
	 * Updates the resources of each player
	 */
	private void updateData() {
		p1Funds.setText(Integer.toString(game.getPlayer1().getMoney()));
		game.getPlayer1().setMoney(game.getPlayer1().getMoney());
		p2Funds.setText(Integer.toString(game.getPlayer2().getMoney()));
		game.getPlayer2().setMoney(game.getPlayer2().getMoney());
		p1Supplies.setText(Integer.toString(game.getPlayer1().getSupplies()));
		game.getPlayer1().setSupplies(game.getPlayer1().getSupplies());
		p2Supplies.setText(Integer.toString(game.getPlayer2().getSupplies()));
		game.getPlayer2().setSupplies(game.getPlayer2().getSupplies());
		p1Crew.setText(Integer.toString(game.getPlayer1().getCrew()));
		game.getPlayer1().setCrew(game.getPlayer1().getCrew());
		p2Crew.setText(Integer.toString(game.getPlayer2().getCrew()));
		game.getPlayer2().setCrew(game.getPlayer2().getCrew());
		p1Security.setText(Integer.toString(game.getPlayer1().getGuards()));
		game.getPlayer1().setGuards(game.getPlayer1().getGuards());
		p2Security.setText(Integer.toString(game.getPlayer2().getGuards()));
		game.getPlayer2().setGuards(game.getPlayer2().getGuards());

	}

	/**
	 * Updates the game board
	 */
	private void updateBoard() {
		for (int i = 0; i < MirskyTrail.HEIGHT_OF_BOARD; i++) {
			for (int j = 0; j < MirskyTrail.WIDTH_OF_BOARD; j++) {
				if (game.getTile(i, j).getType() == TileType.P1START) {
					btns[i][j].setBackground(Color.RED);
				} else if (game.getTile(i, j).getType() == TileType.P2START) {
					btns[i][j].setBackground(Color.RED);
				} else if (game.getTile(i, j).isTrack()) {
					if (game.getPlayer1().getRow() == i
							&& game.getPlayer1().getCol() == j) {
						btns[i][j].setBackground(Color.WHITE);
						btns[i][j].setForeground(Color.BLACK);
					} else if (game.getPlayer2().getRow() == i
							&& game.getPlayer2().getCol() == j) {
						btns[i][j].setBackground(Color.YELLOW);
						btns[i][j].setForeground(Color.BLACK);
					} else {
						if (game.getPlayer1().isInPath(i, j)) {
							btns[i][j].setBackground(Color.BLACK);
						} else {
							btns[i][j].setBackground(Color.DARK_GRAY);
						}

						btns[i][j].setText("");
					}
				} else if (game.getTile(i, j).getType() == TileType.WATER) {
					btns[i][j].setBackground(Color.BLUE);
					btns[i][j].setText("");
				} else if (game.getTile(i, j).getType() == TileType.FOREST) {
					btns[i][j].setBackground(Color.GREEN);
					btns[i][j].setText("");
				} else if (game.getTile(i, j).getType() == TileType.SUPPLYSTATION) {
					btns[i][j].setBackground(Color.MAGENTA);
					btns[i][j].setText("");
				} else if (game.getTile(i, j).getType() == TileType.MOUNTAIN) {
					btns[i][j].setBackground(Color.GRAY);
					btns[i][j].setText("");
				} else if (game.getTile(i, j).getType() == TileType.GOAL) {
					btns[i][j].setBackground(Color.RED);
					btns[i][j].setText("");
				} else if (game.getTile(i, j).getType() == TileType.PLAIN) {
					btns[i][j].setBackground(Color.LIGHT_GRAY);
					btns[i][j].setText("");
				}
			}
		}
	}

	public static JButton getP1EndTurnBtn() {
		return p1EndTurnBtn;
	}

	public static void setP1EndTurnBtn(JButton p1EndTurnBtn) {
		GUI.p1EndTurnBtn = p1EndTurnBtn;
	}

	public static JButton getP2EndTurnBtn() {
		return p2EndTurnBtn;
	}

	public static void setP2EndTurnBtn(JButton p2EndTurnBtn) {
		GUI.p2EndTurnBtn = p2EndTurnBtn;
	}

	/**
	 * @Jeff- Marks the button on the board with a "1" for player 1 and a "2"
	 *        for player 2. Also enables the refill button if the player is
	 *        adjacent to a supply station.
	 * 
	 */
	public void updatePlayerPosition() {
		btns[game.getPlayer1().getRow()][game.getPlayer1().getCol()]
				.setText("1");
		btns[game.getPlayer2().getRow()][game.getPlayer2().getCol()]
				.setText("2");

		// The refill buttons are enabled based on if the player is next to a
		// supply station.

		// p1RefillBtn.setEnabled(game.isRefillLocation1());
		// p2RefillBtn.setEnabled(game.isRefillLocation2());

	}

	public void setPlains(JButton[][] array, int rowStart, int rowEnd,
			int columnStart, int columnEnd) {
		for (int i = rowStart; i < rowEnd; i++) {
			for (int j = columnStart; j < columnEnd; j++) {
				array[i][j].setBackground(Color.LIGHT_GRAY);
				array[i][j].setActionCommand(TileType.PLAIN.name());
			}
		}
	}

	/**
	 * @author Ryan Yanes
	 * 
	 *         This method is used to end Player1's turn and enable Player2's
	 *         turn and the options available based on position on board. Also
	 *         is used to determine the good fortune/natural disaster for player
	 *         2
	 */

	public void player1EndTrn() {
		Random ran = new Random();
		int chance = ran.nextInt(10) + 1;
		enableBoard(false);
		p1CrewBtn.setEnabled(false);
		p1SecurityBtn.setEnabled(false);
		p1SuppBtn.setEnabled(false);
		p1EndTurnBtn.setEnabled(false);
		p1MoveBtn.setEnabled(false);
		p1RefillBtn.setEnabled(false);
		p1StealBtn.setEnabled(false);
		if (player2NumOfTrns < 1) {
			p2CrewBtn.setEnabled(true);
			p2SecurityBtn.setEnabled(true);
			p2SuppBtn.setEnabled(true);
			p2StealBtn.setEnabled(false);
			p2MoveBtn.setEnabled(false);
		} else {
			if (game.getPlayer2().isBuySupplies()) {
				p2CrewBtn.setEnabled(true);
				p2SecurityBtn.setEnabled(true);
				p2SuppBtn.setEnabled(true);
			} else {
				p2CrewBtn.setEnabled(false);
				p2SecurityBtn.setEnabled(false);
				p2SuppBtn.setEnabled(false);
			}
			p2StealBtn.setEnabled(true);
			p2MoveBtn.setEnabled(true);
		}
		p2EndTurnBtn.setEnabled(true);
		p2RefillBtn.setEnabled(game.isRefillLocation(game.getPlayer2()));
		player1NumOfTrns++;
		// This is where the random events happen for player 2. If the 10%
		// requirement is met, it generates one of 12 events and displays the
		// proper message
		if (player1NumOfTrns > 1) {
			if (chance == 1) {
				int event = game.randomEvents(2);
				if (event == 1) {
					JOptionPane
							.showMessageDialog(null,
									"You picked up some muscle along the way. Security increased 10%");
					GameLog.fileWrite("Player Two: You picked up some muscle along the way. Security increased 10%");
				} else if (event == 2) {
					JOptionPane
							.showMessageDialog(null,
									"Some guards were caught sleeping on the job. Security decreased 10%");
					GameLog.fileWrite("Player Two: Some guards were caught sleeping on the job. Security decreased 10%");
				} else if (event == 3) {
					JOptionPane
							.showMessageDialog(null,
									"You hire some workers willing to help the cause. Crew increased 20%");
					GameLog.fileWrite("Player Two: You hire some workers willing to help the cause. Crew increased 20%");
				} else if (event == 4) {
					JOptionPane
							.showMessageDialog(null,
									"Some of your crew forgot to put in comments and javadocs. Crew decreased 20%");
					GameLog.fileWrite("Player Two: Some of your crew forgot to put in comments and javadocs. Crew decreased 20%");
				} else if (event == 5) {
					JOptionPane
							.showMessageDialog(null,
									"You reached the end of a rainbow and found supplies. Supplies increased 30%");
					GameLog.fileWrite("Player Two: You reached the end of a rainbow and found supplies. Supplies increased 30%");
				} else if (event == 6) {
					JOptionPane
							.showMessageDialog(null,
									"You forgot to forward that chain letter. Supplies decreased 30%");
					GameLog.fileWrite("Player Two: You forgot to forward that chain letter. Supplies decreased 30%");
				} else if (event == 7) {
					JOptionPane
							.showMessageDialog(null,
									"Santa came early this year. Supplies increased 40%");
					GameLog.fileWrite("Player Two: Santa came early this year. Supplies increased 40%");
				} else if (event == 8) {
					JOptionPane
							.showMessageDialog(null,
									"Obligitory Dysentary reference. Crew decreased 40%");
					GameLog.fileWrite("Player Two: Obligitory Dysentary reference. Crew decreased 40%");
				} else if (event == 9) {
					JOptionPane.showMessageDialog(null,
							"You won the lottery. Supplies increased 50%");
					GameLog.fileWrite("Player Two: You won the lottery. Supplies increased 50%");
				} else if (event == 10) {
					JOptionPane
							.showMessageDialog(null,
									"You left most of your supplies behind. Supplies decreased 50%");
					GameLog.fileWrite("Player Two: You left most of your supplies behind. Supplies decreased 50%");
				} else if (event == 11) {
					JOptionPane
							.showMessageDialog(null,
									"The game must really want you to win. Supplies doubled");
					GameLog.fileWrite("Player Two: The game must really want you to win. Supplies doubled");
				} else if (event == 12) {
					JOptionPane
							.showMessageDialog(null,
									"Who's bad side did you get on to deserve this? Supplies decreased 75%");
					GameLog.fileWrite("Player Two: Who's bad side did you get on to deserve this? Supplies decreased 75%");
				}
			}
		}
		updateData();

	}

	/**
	 * @author Ryan Yanes
	 * 
	 *         This method is used to end Player2's turn and enable Player1's
	 *         turn and the options available based on position on board. Also
	 *         is used to determine the good fortune/natural disaster for player
	 *         1
	 */
	public void player2EndTrn() {
		Random ran = new Random();
		int chance = ran.nextInt(10) + 1;
		enableBoard(false);
		p2CrewBtn.setEnabled(false);
		p2SecurityBtn.setEnabled(false);
		p2SuppBtn.setEnabled(false);
		p2EndTurnBtn.setEnabled(false);
		p2MoveBtn.setEnabled(false);
		p2RefillBtn.setEnabled(false);
		p2StealBtn.setEnabled(false);
		if (player1NumOfTrns < 1) {
			p1CrewBtn.setEnabled(true);
			p1SecurityBtn.setEnabled(true);
			p1SuppBtn.setEnabled(true);
			p1StealBtn.setEnabled(false);
			p1MoveBtn.setEnabled(false);
		} else {
			if (game.getPlayer1().isBuySupplies()) {
				p1CrewBtn.setEnabled(true);
				p1SecurityBtn.setEnabled(true);
				p1SuppBtn.setEnabled(true);
			} else {
				p1CrewBtn.setEnabled(false);
				p1SecurityBtn.setEnabled(false);
				p1SuppBtn.setEnabled(false);
			}
			p1StealBtn.setEnabled(true);
			p1MoveBtn.setEnabled(true);
		}
		p1EndTurnBtn.setEnabled(true);
		p1RefillBtn.setEnabled(game.isRefillLocation(game.getPlayer1()));
		player2NumOfTrns++;
		if (player1NumOfTrns > 1) {
			// This is where the random events happen for player 1. If the 10%
			// requirement is met, it generates one of 12 events and displays
			// the proper message
			if (chance == 1) {
				int event = game.randomEvents(1);
				if (event == 1) {
					JOptionPane
							.showMessageDialog(null,
									"You picked up some muscle along the way. Security increased 10%");
					GameLog.fileWrite("Player One: You picked up some muscle along the way. Security increased 10%");
				} else if (event == 2) {
					JOptionPane
							.showMessageDialog(null,
									"Some guards were caught sleeping on the job. Security decreased 10%");
					GameLog.fileWrite("Player One: Some guards were caught sleeping on the job. Security decreased 10%");
				} else if (event == 3) {
					JOptionPane
							.showMessageDialog(null,
									"You hire some workers willing to help the cause. Crew increased 20%");
					GameLog.fileWrite("Player One: You hire some workers willing to help the cause. Crew increased 20%");
				} else if (event == 4) {
					JOptionPane
							.showMessageDialog(null,
									"Some of your crew forgot to put in comments and javadocs. Crew decreased 20%");
					GameLog.fileWrite("Player One: Some of your crew forgot to put in comments and javadocs. Crew decreased 20%");
				} else if (event == 5) {
					JOptionPane
							.showMessageDialog(null,
									"You reached the end of a rainbow and found supplies. Supplies increased 30%");
					GameLog.fileWrite("Player One: You reached the end of a rainbow and found supplies. Supplies increased 30%");
				} else if (event == 6) {
					JOptionPane
							.showMessageDialog(null,
									"You forgot to forward that chain letter. Supplies decreased 30%");
					GameLog.fileWrite("Player One: You forgot to forward that chain letter. Supplies decreased 30%");
				} else if (event == 7) {
					JOptionPane
							.showMessageDialog(null,
									"Santa came early this year. Supplies increased 40%");
					GameLog.fileWrite("Player One: Santa came early this year. Supplies increased 40%");
				} else if (event == 8) {
					JOptionPane
							.showMessageDialog(null,
									"Obligitory Dysentary reference. Crew decreased 40%");
					GameLog.fileWrite("Player One: Obligitory Dysentary reference. Crew decreased 40%");
				} else if (event == 9) {
					JOptionPane.showMessageDialog(null,
							"You won the lottery. Supplies increased 50%");
					GameLog.fileWrite("Player One: You won the lottery. Supplies increased 50%");
				} else if (event == 10) {
					JOptionPane
							.showMessageDialog(null,
									"You left most of your supplies behind. Supplies decreased 50%");
					GameLog.fileWrite("Player One: You left most of your supplies behind. Supplies decreased 50%");
				} else if (event == 11) {
					JOptionPane
							.showMessageDialog(null,
									"The game must really want you to win. Supplies doubled");
					GameLog.fileWrite("Player One: The game must really want you to win. Supplies doubled");
				} else if (event == 12) {
					JOptionPane
							.showMessageDialog(null,
									"Who's bad side did you get on to deserve this? Supplies decreased 75%");
					GameLog.fileWrite("Player One: Who's bad side did you get on to deserve this? Supplies decreased 75%");
				}
			}
		}
		updateData();
	}

}
