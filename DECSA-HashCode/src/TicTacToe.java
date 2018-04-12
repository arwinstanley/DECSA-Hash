import java.util.Arrays;
/**
 * @author Professor Kelly
 * @user arwinstanley
 * @Date 4/12/18
 * 
 * This Class is a the tic-tac-toe game and represents the gamestate of any given game
 */
public class TicTacToe {
   public final static int ROWS = 3;
   public final static int COLS = 3;
   public final static int POSSIBILITIES = (int) Math.pow(3,9);
   public final static int CHAR_POSSIBILITIES = 3; // x, o or space
	/**
	 * @param b is the char 2d array representing the board
	 * @param ch is the char your checking
	 * @return total is the number of times that 'ch' occurs in 2d array 'b'
	 * 
	 * returns the number of chars in any given 2d car array
	 * 
	 */
   private static int numChars(char[][] b, char ch) {
      int total = 0;
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++)
            if (ch == b[r][c]) 
               total++;
      return total;
   }
	/**
	 * @param board is a 2d char array representing the board
	 * @return returns true if this is a valid tic-tac-toe game false otherwise
	 * 
	 * checks to see if the given board is a valid board or not
	 * 
	 */
   public static boolean valid(char[][] board) {
   
   // Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
   // Make sure there are at least one more x or one more o
      int numX = numChars(board, 'x');
      int numO = numChars(board, 'o');
      if (! (numX > 2 || numO > 2)) 
         return false;
      if ((numX == numO + 1) || (numO == numX + 1)) 
         return true;
      return false;
   }
	/**
	 * @param board is a 2d char array representing the board
	 * @return returns true if this is a valid tic-tac-toe game false otherwise
	 * 
	 * checks to see if the given board is a valid board or not
	 * 
	 */
   public static String boardToString(char[][] b) {
      String result = "";
      for (int r = 0; r < ROWS; r++) {
         for (int c = 0; c < COLS; c++) 
            result += b[r][c];
      //     result += "\n";
      }
      return result;
   }
	/**
	 * @param board is a string that represents the tic-tac-toe board
	 * @return b a char 2d array that represents how the tic-tac-toe board would actually look
	 * 
	 * checks to see if the given board is a valid board or not
	 * 
	 */
   public static char[][] stringToBoard(String board) {
      char[][] b = new char[ROWS][COLS];
      int index = 0;
      for (int r = 0; r < ROWS; r++) {
         for (int c = 0; c < COLS; c++) 
            b[r][c] = whichLetter(board.charAt(index++));
      }
      return b;
   }

	/**
	 * @param ch is the char your checking
	 * @return a numeric value of the given char in a base 3 system but it returns the number as a char
	 * 
	 * checks to see if the given board is a valid board or not
	 * 
	 */
   public static char whichLetter(char ch) {
      switch (ch) {
         case '1' : 
            return 'x';
         case '2' : 
            return 'o';
         case '0'  : 
            return ' ';
         default: 
            return ch;
      }
   }
	/**
	 * @param s is the string representation of the board
	 * @return a char 2d array which represents the given string as a game board
	 * 
	 * takes in a string of numbers in base 3 and returns a 2d array of the what the game board would look like
	 * 
	 */
   public static char[][] makeBoard(String s) {
      char[][] b = new char[ROWS][COLS];
      int ch = 0;
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++){         
            b[r][c] = whichLetter(s.charAt(ch));
            ch++;
         }
      return b;
   }
	/**
	 * @param s is a string of numbers in base 3 that is 9 characters long
	 * @return the new string after adjustment
	 * 
	 * adds 1 to the last char and adjusts the rest of the string accordingly
	 * 
	 */
   private static String addOne(String s) {
   // s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
   // all the rest of the characters as necessary.
      boolean carry = false;
      char ch[] = s.toCharArray();
      ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
      for (int n = ch.length-1; n >=0; n--) {
         if (carry) ch[n] = (char)((int)(ch[n])+1);
         if (ch[n] == '3') {
            carry = true;
            ch[n] = '0';
         }
         else
            carry = false;      
      }
      return new String(ch);
   }
	/**
	 * @param none
	 * @return returns a String [] of all the possibilities of a board
	 * 
	 * checks to see if the given board is a valid board or not
	 * 
	 */
   public static String[] fillValues() {
      String strBoard = "000000000";
      String[] values = new String[POSSIBILITIES];
      int index = 0;
      values[index++] = strBoard;
      while (!strBoard.equals("222222222") ) {
         strBoard = addOne(strBoard);
         values[index++] = strBoard;
      }
      return values;
   }
	/**
	 * @param board is a 2d char array representing the board
	 * @return returns true if this board has a diagonal win false otherwise
	 * 
	 * checks to see if a player has won diagonally 
	 * 
	 */
   private static boolean diagonalWin(char[][] board) {
      int wins = 0;
      if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
         (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) 
         wins++;
      

         if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
           (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) 
            wins++;
         
      return wins == 1;
   }
	/**
	 * @param board is a 2d char array representing the board
	 * @return returns true if this board has a row win false otherwise
	 * 
	 * checks to see if a player has won with a row 
	 * 
	 */
   private static boolean rowWin(char[][] board) {
      char ch;
      int wins = 0;
      boolean win = true;
      for (int r = 0; r < ROWS; r++) {
         win = true;
         ch = board[r][0];
         for (int c = 0; c < COLS; c++) 
            if (ch != board[r][c]) {
               win = false;
               break;
            }
         if (win && ch != ' ')
            wins++;
      } 
      return wins == 1;
   } 	
   	/**
	 * @param board is a 2d char array representing the board
	 * @return returns true if this board has a column win false otherwise
	 * 
	 * checks to see if a player has won with a column
	 * 
	 */
   private static boolean colWin(char[][] board) {
      char ch;
      int wins = 0;
      boolean win = true;
      for (int c = 0; c < COLS; c++) {
         win = true;
         ch = board[0][c];
         for (int r = 0; r < ROWS; r++) 
            if (ch != board[r][c]) {
               win = false;
               break;
            }
         if (win && ch != ' ') 
            wins++;
      } 
        return wins == 1;
   } 
  	/**
	 * @param b is a 2d char array representing the board
	 * @return returns true if this board a win false otherwise
	 * 
	 * checks first to see if the board is valid then checks for a win of any type
	 * 
	 */
   public static boolean isWin(char[][]b) {
     return valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b));
     }

  	/**
	 * @param b is a 2d char array representing the board
	 * @return a String of with either winner or loser and how they won/lost 
	 * 
	 * returns a string of how the player won or lost, i.e. row, column, diagonal
	 * 
	 */
   public static String isWinString(char[][]b) {
     boolean v = valid(b);
     boolean r = rowWin(b);
     boolean c = colWin(b);
     boolean d = diagonalWin(b);
     if (valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b))) {
       if (r) return "Row";
       if (c) return "Col";
       if (d) return "Dia";
       return "winner";
     }
     else
       return "loser";
   }
  	/**
	 * @param s is a string representing the board state
	 * @return true if the player won false otherwise
	 * 
	 * runs the original isWin function with a string
	 * 
	 */
        public static boolean isWin(String s) {
      char[][] b = stringToBoard(s);
      return isWin(b);
   }
      	/**
    	 * @param s is a string representing the board state
    	 * @return a string of how the player won or lost
    	 * 
    	 * runs the original isWinString function with a string
    	 * 
    	 */
   public static String isWinString(String s) {
      char[][] b = stringToBoard(s);
      return isWinString(b);
   }
 	/**
	 * @param args is the arguments taken in by the program
	 * @return none
	 * 
	 * runs the tic-tac-toe game
	 * 
	 */
   public static void main(String[] args) {
      String s = "ooooxxxox";
      char[][] b = stringToBoard(s);
      System.out.println("Valid:   " + valid(b));
      System.out.println("Row Win: " + rowWin(b));
      System.out.println("Col Win: " + colWin(b));
      System.out.println("Dia Win: " + diagonalWin(b));
      System.out.println(isWin(b));
       
   }    
}
