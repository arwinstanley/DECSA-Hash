import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author Professor Kelly
 * @editor arwinstanley
 * @Date 4/12/18
 * 
 * This Class is a the tic-tac-toe hash code generator
 */
public class TicTacToeHashCode extends Board {

	public static boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
		}

	@Override
	public int myHashCode() {
   	  int[] pwrsOfThre = {1,3,9,27,81,243,729,2187,6561};
      char[] temp = new char[9];
      int count = 0;
      for(int i = 0; i<=2;i++) {
    	  for(int j = 0; j<=2;j++) {
    		  temp[count] = super.charAt(i,j);
    		  count++;
    	  }
      } 
      int sum = 0;
      for(int i = 0; i < temp.length ; i++) {
    	  sum += charInt(temp[i])*pwrsOfThre[i];
      }
      return sum;
	}
	public static int myHashCode(char[] arr) {
	   	  int[] pwrsOfThre = {1,3,9,27,81,243,729,2187,6561};
	      int sum = 0;
	      for(int i = 0; i < arr.length ; i++) {
	    	  sum += statCharInt(arr[i])*pwrsOfThre[i];
	      }
	      return sum;
		}

	@Override
	public boolean isWin(String s) {
		return winners[myHashCode(s.toCharArray())];
      }
      
	@Override
	public boolean isWin() {
		return winners[myHashCode(getBoardString().toCharArray())];
      }
   public int charInt(char c) {
    	switch(c) {
    		case 'x': return 2;
    		case 'o': return 1;
    		case ' ': return 0;
    		default: return -1;
    	}
    }
   public static int statCharInt(char c) {
   	switch(c) {
   		case 'x': return 2;
   		case 'o': return 1;
   		case ' ': return 0;
   		default: return -1;
   	}
   }
	/* 
	 * @author WinstanleyA
	 * @Date 3/8/18
	 * @param fNme the name of the file you wish to read
	 * @return input a Scanner to read the file you gave as input
	 */
	public static Scanner reader(String fNme) {
		File scannable = new File( fNme );
		Scanner input = null;
		try {
			input = new Scanner(scannable);
		} catch (FileNotFoundException ex) {
			System.out.println("Cant open file: " + fNme);
			return null;
		}
		return input;
	}
	public static void main(String[] args) throws InterruptedException {
		winners = new boolean[198683];
		Scanner win = reader(args[0]);
		while(win.hasNextLine()) {
			char[] cArr = win.nextLine().toCharArray();
			winners[myHashCode(cArr)] = true;
		}
		String[] allTests = new String[198683];
		int count = 0;
		Scanner all = reader(args[1]);
		while(all.hasNextLine()) {
			allTests[count] = all.nextLine();
			count++;
		}
		String[] halfAndHalf = new String[10];
		int count2 = 0;
		Scanner half = reader(args[2]);
		while(half.hasNextLine()) {
			halfAndHalf[count2] = half.nextLine();
			count2++;
		}
		
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		 for(String y : halfAndHalf) {
		   board.setBoardString(y);
		   board.resetBoardString();
		   board.setHashCodeLabel(board.myHashCode());
		   board.setWinnerLabel(board.isWin());
		   Thread.sleep(1000);
		 }
	}

}
