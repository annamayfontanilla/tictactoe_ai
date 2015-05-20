public class Board {
	
	protected static String[][] cell = new String[3][3];
	protected static String winner="";
	protected static boolean full=true;



	public Board() {

		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				cell[i][j] = "free";
	}

	protected void updateBoard( int row, int column, String entry ) {

		this.cell[row][column] = entry;
	}

	protected String[][] getBoard() {

		return cell;
	}

	protected void check() {

		full = true;

		//check if full
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if( cell[i][j]=="free" ) {
					full = false;
				}
			}
		}

		if( full==true ) {
			winner = "draw";
		}
		else {
			//check rows
			for(int i=0; i<3; i++) {
				if( cell[i][0]=="x" && cell[i][1]=="x" && cell[i][2]=="x" )
					winner = "x";
				if( cell[i][0]=="o" && cell[i][1]=="o" && cell[i][2]=="o" )
					winner = "o";
			}

			//check columns
			for(int i=0; i<3; i++) {
				if( cell[0][i]=="x" && cell[1][i]=="x" && cell[2][i]=="x" )
					winner = "x";
				if( cell[0][i]=="o" && cell[1][i]=="o" && cell[2][i]=="o" )
					winner = "o";
			}

			//check diagonals
			if( cell[0][0]=="x" && cell[1][1]=="x" && cell[2][2]=="x" )
				winner = "x";
			if( cell[0][0]=="o" && cell[1][1]=="o" && cell[2][2]=="o" )
				winner = "o";
			if( cell[0][2]=="x" && cell[1][1]=="x" && cell[2][0]=="x" )
				winner = "x";
			if( cell[0][2]=="o" && cell[1][1]=="o" && cell[2][0]=="o" )
				winner = "o";
		}
	}

	protected void resetBoard() {

		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				cell[i][j] = "free";
	}
}