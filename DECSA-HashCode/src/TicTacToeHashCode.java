
//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
      //TODO Instantiate winners array
		}

	@Override
	public int myHashCode() {
   	  int[] pwrsOfThre = {1,3,9,27,81,243,729,2187,6561};
      char[] temp = new char[10];
      int count = 0;
      for(int i = 1; i<=3;i++) {
    	  for(int j = 1; j<=3;j++) {
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

	@Override
	public boolean isWin(String s) {
      // TODO write an isWin method that takes in a String.  This should not change the board.  Board has an additional charAt 
      // TODO method to facilitate this
		return false;
      }
      
	@Override
	public boolean isWin() {
      // TODO write an isWin method that uses boardString
		return false;
      }
   public int charInt(char c) {
    	switch(c) {
    		case 'x': return 2;
    		case 'o': return 1;
    		case ' ': return 0;
    		default: return -1;
    	}
    }
  
      
	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		 while (true) {
		   board.displayRandomString();
		   Thread.sleep(4000);
		 }
	}

}
