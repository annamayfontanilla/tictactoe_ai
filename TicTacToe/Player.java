import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;



public class Player {

	private Board board = new Board();
	protected boolean win=false;
	protected String piece="";



	public Player() {}

	protected void turn( int row, int column ) {

		board.updateBoard( row, column, piece );
	}
}