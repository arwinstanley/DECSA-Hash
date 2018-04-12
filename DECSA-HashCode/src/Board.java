import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author Professor Kelly
 * @user arwinstanley
 * @Date 4/12/18
 * 
 * This Class board and is mainly running the graphical interface and showing the board
 */
abstract class Board extends JFrame implements ActionListener {

	private JButton buttons[][];
	private JLabel lblHashCode;
	private JLabel lblWinTitle;

	private String boardString = "";
	/**
	 * @param title is the name of the board
	 * @return none
	 * 
	 * creates a board with the name 'title' and calls setupFrame
	 * 
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}
	/**
	 * @param hashcode is the generated hashcode of a board
	 * @return none
	 * 
	 * standard setter that changes the on-screen label for hashcode
	 * 
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}
	/**
	 * @param result is the generated win/lose message
	 * @return none
	 * 
	 * standard setter that changes the on-screen label for winner/loser
	 * 
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}
	/**
	 * @param result is the generated win/lose condition
	 * @return none
	 * 
	 * same as the one above except it takes in an boolean and changes it to a string
	 * 
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	/**
	 * @param none
	 * @return panel, a Jpanel used for the non-gameplay information
	 * 
	 * creates a Jpanel and adds in labels for the hashcode and for win/lose
	 * 
	 */
	public JPanel setupPanelOne() {
		//I wrote in the 'public' just to make it look nicer
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}
	/**
	 * @param none
	 * @return panel, a Jpanel used for the gameplay information
	 * 
	 * creates a Jpanel and adds in a 3x3 grid that is made up of buttons so that they can be changed by a click
	 * 
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}
	/**
	 * @param ch is a char that comes off the board
	 * @return a char that is next to the one passed in, in the base 3 system
	 * 
	 * switches the char to the next one in the base 3 system used to represent tic-tac-toe
	 * 
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}
	/**
	 * @param none
	 * @return none
	 * 
	 * sets up the GUI and calls setup methods for both panels
	 * 
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}
	/**
	 * @param none
	 * @return a random char ' ','x', or 'o'
	 * 
	 * returns a random state of any given board slot
	 * 
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}
	/**
	 * @param none
	 * @return int a hashcode generated for this board
	 * 
	 * creates hashcode using a base 3 number system by converting the individual slots and multiplying that times 3^n 
	 * where n is the position on the board, starting at 0
	 * 
	 */
	abstract int myHashCode();
	/**
	 * @param s a string that is a representation of the board
	 * @return true if there is a winner false otherwise
	 * 
	 * determines whether there is a winner or not
	 * 
	 */
	abstract boolean isWin(String s);
	/**
	 * @param none
	 * @return true if there is a winner false otherwise
	 * 
	 * determines whether there is a winner or not using the board string
	 * 
	 */
	abstract boolean isWin();
	/**
	 * @param row an int index in the row
	 * @param col an int index in the column 
	 * @return the char at the specified position
	 * 
	 * returns a char at any specified position from the board string
	 * 
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}
	/**
	 * @param s a string that is a representation of the board
	 * @param row an int index in the row
	 * @param col an int index in the column 
	 * @return the char at the specified position
	 * 
	 * returns a char at any specified position from the given string
	 * 
	 */
   public char charAt(String s, int row, int col) {
     int pos = row * TicTacToe.COLS + col;
     if (s.length() >= pos)
       return s.charAt(pos);
     else
       return '*';   
   }
	/**
	 * @param s a string that is a representation of the board
	 * @return none
	 * 
	 * changes the buttons on the grid to show the correct char from the base 3 system
	 * 
	 */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}
	/**
	 * @param none
	 * @return none
	 * 
	 * resets the board string and the tic-tac-toe class
	 * 
	 */
	public void resetBoardString() {
   boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}
	/**
	 * @param s a String representation of the board
	 * @return none
	 * 
	 * standard setter that changes board string to s and then calls show() with s
	 * 
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}
	/**
	 * @param none
	 * @return the boardString
	 * 
	 * standard getter that returns the boardstring
	 * 
	 */
	public String getBoardString() {
		return boardString;
	}
	/**
	 * @param none
	 * @return none
	 * 
	 * generates a random board state and adjusts the board accordingly 
	 * 
	 */
	public void displayRandomString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}