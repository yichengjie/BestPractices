package com.yicj.swing.s1;

import static com.yicj.common.util.SwingConsole.run;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayout2 extends JFrame {
	
	public BorderLayout2(){
		//add(BorderLayout.NORTH,new JButton("North")) ;
		add(BorderLayout.SOUTH,new JButton("South")) ;
		//add(BorderLayout.EAST,new JButton("East")) ;
		//add(BorderLayout.WEST,new JButton("West")) ;
		add(BorderLayout.CENTER,new JButton("Center")) ;
	}
	public static void main(String[] args) {
		run(new BorderLayout2(), 300, 250);
	}
}
