import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;



public class Computer {

	private Board board = new Board();
	protected boolean win=false, found=false;
	protected String piece="", opponent="";
	protected int row, column;
	protected String[][] cell;



	public Computer() {}

	protected void turn() {

		cell = board.getBoard();

		if( !checkIfWin() ) 
			if( !checkIfLock() ) 
//				if( !checkIfFork() ) 
					if( !checkIfBlock() ) 
//						if( !checkIfOpposite() ) 
							if( !checkIfCenter() ) 
								if( !checkIfCorner() ) 
									found = checkIfSide();
//										found = checkIfFree();

		board.updateBoard( row, column, piece );
	}

	/* Win: If you have two in a row, you can place a third to get three in a row. */
	private boolean checkIfWin() {

		for(int i=0; i<3; i++) {
			row = i;
				
			if( cell[i][0]==piece && cell[i][1]==piece && cell[i][2]=="free" ) {
				column = 2;
				return true;
			}
			if( cell[i][0]==piece && cell[i][2]==piece && cell[i][1]=="free" ) {
				column = 1;
				return true;
			}
			if( cell[i][1]==piece && cell[i][2]==piece && cell[i][0]=="free" ) {
				column = 0;
				return true;
			}
		}

		for(int i=0; i<3; i++) {
			column = i;

			if( cell[0][i]==piece && cell[1][i]==piece && cell[2][i]=="free" ) {
				row = 2;
				return true;
			}
			if( cell[0][i]==piece && cell[2][i]==piece && cell[1][i]=="free" ) {
				row = 1;
				return true;
			}
			if( cell[1][i]==piece && cell[2][i]==piece && cell[0][i]=="free" ) {
				row = 0;
				return true;
			}
		}

		if( cell[1][1]==piece ) {
			if( cell[0][0]==piece && cell[2][2]=="free" ) {
				row = 2;
				column = 2;
				return true;
			}
			if( cell[2][2]==piece && cell[0][0]=="free" ) {
				row = 0;
				column = 0;
				return true;
			}
			if( cell[2][0]==piece && cell[0][2]=="free" ) {
				row = 0;
				column = 2;
				return true;
			}
			if( cell[0][2]==piece && cell[2][0]=="free" ) {
				row = 2;
				column = 0;
				return true;
			}
		}

		if( ((cell[0][2]==piece && cell[2][0]==piece) || (cell[0][0]==piece && cell[2][2]==piece)) && cell[1][1]=="free" ) {
			row = 1;
			column = 1;
			return true;
		}

		return false;
	}
	
	/* Lock: If the opponent has two in a row, you must play the third to block the opponent. */
	private boolean checkIfLock() {
		
		for(int i=0; i<3; i++) {
			row = i;
				
			if( cell[i][0]==opponent && cell[i][1]==opponent && cell[i][2]=="free" ) {
				column = 2;
				return true;
			}
			if( cell[i][0]==opponent && cell[i][2]==opponent && cell[i][1]=="free" ) {
				column = 1;
				return true;
			}
			if( cell[i][1]==opponent && cell[i][2]==opponent && cell[i][0]=="free" ) {
				column = 0;
				return true;
			}
		}

		for(int i=0; i<3; i++) {
			column = i;

			if( cell[0][i]==opponent && cell[1][i]==opponent && cell[2][i]=="free" ) {
				row = 2;
				return true;
			}
			if( cell[0][i]==opponent && cell[2][i]==opponent && cell[1][i]=="free" ) {
				row = 1;
				return true;
			}
			if( cell[1][i]==opponent && cell[2][i]==opponent && cell[0][i]=="free" ) {
				row = 0;
				return true;
			}
		}

		if( cell[1][1]==opponent ) {
			if( cell[0][0]==opponent && cell[2][2]=="free" ) {
				row = 2;
				column = 2;
				return true;
			}
			if( cell[2][2]==opponent && cell[0][0]=="free" ) {
				row = 0;
				column = 0;
				return true;
			}
			if( cell[2][0]==opponent && cell[0][2]=="free" ) {
				row = 0;
				column = 2;
				return true;
			}
			if( cell[0][2]==opponent && cell[2][0]=="free" ) {
				row = 2;
				column = 0;
				return true;
			}
		}

		if( ((cell[0][2]==opponent && cell[2][0]==opponent) || (cell[0][0]==opponent && cell[2][2]==opponent)) && cell[1][1]=="free" ) {
			row = 1;
			column = 1;
			return true;
		}

		return false;
	}
	
	/* Fork: Create an opportunity where you have two threats to win (two non-blocked lines of 2). */
	private boolean checkIfFork() {
		return false;
	}
	
	/* Blocking an opponent's fork: If there is a configuration where the opponent can fork, you must block that fork. */
	private boolean checkIfBlock() {
		
		if( cell[0][2]==opponent && cell[2][0]==opponent && cell[1][1]==piece ) {
			boolean temp = checkIfSide();
			return true;
		}
		if( cell[0][0]==opponent && cell[2][2]==opponent && cell[1][1]==piece ) {
			boolean temp = checkIfSide();
			return true;
		}

		return false;
	}

	/* Opposite corner: If the opponent is in the corner, you play the opposite corner. */
	private boolean checkIfOpposite() {
		return false;
	}
	
	/* Center: You play the center if open. */
	private boolean checkIfCenter() {

		if( cell[1][1]=="free" ) {
			row = 1;
			column = 1;
			return true;
		}

		return false;
	}
	
	/* Empty corner: You play in a corner square. */
	private boolean checkIfCorner() {

		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if( cell[i][j]=="free" && !(i==1 || j==1) ) {
					row = i;
					column = j;
					return true;
				}
			}
		}

		return false;
	}
	
	/*	Empty side: You play in a middle square on any of the 4 sides.	*/
	private boolean checkIfSide() {

		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if( Math.abs(i-j)==1 && cell[i][j]=="free" ) {
					row = i;
					column = j;
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkIfFree() {

		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if( cell[i][j]=="free" ) {
					row = i;
					column = j;
					return true;
				}
			}
		}

		return false;
	}
}