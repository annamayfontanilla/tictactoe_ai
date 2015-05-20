/*
sources:
	xo:https://d13yacurqjgara.cloudfront.net/users/107759/screenshots/901671/xo.png
	http://stackoverflow.com/questions/8287084/tic-tac-toe-perfect-ai-algorithm-deeper-in-create-fork-step
*/

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;



public class TicTacToe implements ActionListener {

	private static JFrame frame;
	private static JPanel gridPanel, buttonPanel;
	private static JButton[][] button = new JButton[3][3];
	private static JButton aiBtn, playerBtn, reset, exit;
	private static ImageIcon n = new ImageIcon("n.jpg");
	private static ImageIcon x = new ImageIcon("x.jpg");
	private static ImageIcon o = new ImageIcon("o.jpg");
	private static boolean playerFirst=false, aiFirst=false, endGame=false;
	private static Player player = new Player();
	private static Computer computer = new Computer();
	private static Board board = new Board();



	public TicTacToe() {

		frame = new JFrame( "TicTacToe AI - DelaCruz-Fontanilla" );
		gridPanel = new JPanel();
		buttonPanel = new JPanel();

		initGridPanel();
		initButtonPanel();

		initFrame( 500, 500 );
		frame.setVisible( true );
	}

	private void initGridPanel() {

		gridPanel.setPreferredSize( new Dimension( 500, 450 ) );
		gridPanel.setLayout( new GridLayout( 3, 3 ) );
		gridPanel.setBackground( Color.RED );

		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				button[i][j] = new JButton();
				button[i][j].addActionListener( this );
				button[i][j].setIcon( n );
				gridPanel.add( button[i][j] );
			}
		}

		frame.add( gridPanel, BorderLayout.CENTER );
	}

	private void initButtonPanel() {

		buttonPanel.setPreferredSize( new Dimension( 500, 50 ) );
		buttonPanel.setLayout( new FlowLayout() );
		buttonPanel.setBackground( Color.BLUE );

		aiBtn = new JButton( "AI GOES FIRST" );
		aiBtn.addActionListener( this );
		buttonPanel.add( aiBtn );

		playerBtn = new JButton( "PLAYER GOES FIRST" );
		playerBtn.addActionListener( this );
		buttonPanel.add( playerBtn );
		
		reset = new JButton( "RESET" );
		reset.addActionListener( this );
		buttonPanel.add( reset );
		
		exit = new JButton( "EXIT" );
		exit.addActionListener( this );
		buttonPanel.add( exit );
		
		frame.add( buttonPanel, BorderLayout.SOUTH );
	}

	private static void initFrame( int x, int y ) {
		frame.setSize( x, y );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setResizable( false );
		frame.setLocationRelativeTo( null );
	}

	private void updateUIBoard() {

		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if( board.cell[i][j]=="x" ) {
					button[i][j].setIcon( x );
				}
				else if( board.cell[i][j]=="o" ) {
					button[i][j].setIcon( o );
				}
			}
		}

		board.check();

		if( board.winner==player.piece ) {
			player.win = true;
			endGame = true;
		}
		else if( board.winner==computer.piece ) {
			computer.win = true;
			endGame = true;
		}
		else if( board.winner=="draw" || board.full==true ) {
			endGame = true;
		}
		else {
			endGame = false;
		}

		if( endGame==true ) {
			if( player.win==true )
				JOptionPane.showMessageDialog( null, "Player wins! Impossible!" );
			else if( computer.win==true )
				JOptionPane.showMessageDialog( null, "AI wins! Burn!" );
			else
				JOptionPane.showMessageDialog( null, "Draw! Try again!" );

			frame.dispose();
		}
	}

	public void actionPerformed( ActionEvent e ) {

		if( e.getSource()==aiBtn ) {

			computer.piece = "x";
			computer.opponent = "o";
			player.piece = "o";

			aiBtn.setEnabled( false );
			playerBtn.setEnabled( false );

			computer.turn();
			updateUIBoard();
			aiFirst = false;
			playerFirst = true;			
		}
		else if( e.getSource()==playerBtn ) {
			
			player.piece = "x";
			computer.piece = "o";
			computer.opponent = "x";

			playerFirst = true;
			aiBtn.setEnabled( false );
			playerBtn.setEnabled( false );
		}
		else if( e.getSource()==reset ) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					button[i][j].setIcon( n );
				}
			}

			aiBtn.setEnabled( true );
			playerBtn.setEnabled( true );
			playerFirst = false;
			aiFirst = false;
			board.resetBoard();
		}
		else if( e.getSource()==exit ) {
			frame.dispose();
		}
		else {
			if( endGame==false ) {
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if( e.getSource()==button[i][j] ) {
							if( board.cell[i][j]!="free" ) {
								JOptionPane.showMessageDialog( null, "Cannot overwrite a turn!" );
							}
							else {
								if( playerFirst==true ) {
									player.turn( i, j );
									updateUIBoard();
//									endGame = board.check();
									if( endGame==false ) {
										computer.turn();
										updateUIBoard();
									}
								}
								else {
									JOptionPane.showMessageDialog( null, "Choose option to go first or to make the AI go first!" );
								}
							}
						}
					}
				}

//				endGame = board.check();
			}			
		}
	}

	public static void main( String[] args ) {

		TicTacToe ai = new TicTacToe();
	}
}