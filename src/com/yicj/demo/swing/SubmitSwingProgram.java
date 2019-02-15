package com.yicj.demo.swing;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SubmitSwingProgram extends JFrame {
	
	JLabel label ;
	public SubmitSwingProgram(){
		super("Hello Swing") ;
		label = new JLabel("A Label") ;
		add(label) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,100);
		setVisible(true);
	}
	
	
	
	
	static SubmitSwingProgram ssp ;
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ssp = new SubmitSwingProgram() ;
			}
		});
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ssp.label.setText("Hey! This is Different!");
			}
		});
	}
}
