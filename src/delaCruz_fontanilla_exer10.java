/*
sources:
	xo:https://d13yacurqjgara.cloudfront.net/users/107759/screenshots/901671/xo.png
*/

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class delaCruz_fontanilla_exer10 extends JFrame{
	public static void main(String args[]){
		tictactoe_board tb = new tictactoe_board();
		tictactoe_ai tictactoe = new tictactoe_ai();
	}
}

class tictactoe_board extends JFrame implements ActionListener{
	int i, j, play=0;
	JPanel panel1, panel2;
	JButton buttons[][], play_human, play_ai, reset, exit;
	ImageIcon n = new ImageIcon("n.jpg");
	ImageIcon x = new ImageIcon("x.jpg");
	ImageIcon o = new ImageIcon("o.jpg");

	public tictactoe_board(){
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		setTitle("::TicTacToe::");
		setSize(500,500);
		setVisible(true);
		setResizable(false);

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,3));

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		buttons = new JButton[3][3];
		for(i=0; i<3; i++){
			for(j=0; j<3; j++){
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				buttons[i][j].setIcon(n);
				panel1.add(buttons[i][j]);
			}
		}

		play_human = new JButton("I GO FIRST");
		play_human.addActionListener(this);
		play_ai = new JButton("AI GOES FIRST");
		play_ai.addActionListener(this);
		reset = new JButton("RESET");
		reset.addActionListener(this);
		exit = new JButton("EXIT");
		exit.addActionListener(this);

		panel2.add(play_human);
		panel2.add(play_ai);
		panel2.add(reset);
		panel2.add(exit);

		c.add(BorderLayout.CENTER, panel1);
		c.add(BorderLayout.SOUTH, panel2);
	}

	public void disable_buttons(){
		play_human.setEnabled(false);
		play_ai.setEnabled(false);
	}

	public void enable_buttons(){
		play_human.setEnabled(true);
		play_ai.setEnabled(true);
	}

	public void actionPerformed(ActionEvent evt){
		try{
			if(evt.getSource()==play_human){
				play=1;
				disable_buttons();

			}
			else if(evt.getSource()==play_ai){
				play=2;
				disable_buttons();

			}
			else if(evt.getSource()==reset){
				for(i=0; i<3; i++){
					for(j=0; j<3; j++){
						buttons[i][j].setIcon(n);
					}
				}
				play=0;
				enable_buttons();
			}
			else if(evt.getSource()==exit){
				dispose();
			}
			else{
				if(play!=0){
					for(i=0; i<3; i++){
						for(j=0; j<3; j++){
							if(evt.getSource()==buttons[i][j]){
								if(play==1) buttons[i][j].setIcon(x);
								else if(play==2) buttons[i][j].setIcon(o);
							}
						}
					}
				}
				else JOptionPane.showMessageDialog(null, "Choose option to go first or to make the AI go first.");
			}
		}
		catch(Exception e){

		}
	}
}


class tictactoe_ai{
	public tictactoe_ai(){

	}
}
