package com.yicj.demo.swing;

import java.util.concurrent.TimeUnit;

import static com.yicj.demo.SwingConsole.run ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SubmitSwingProgram2 extends JFrame {
	
	JLabel label ;
	public SubmitSwingProgram2(){
		super("Hello Swing") ;
		label = new JLabel("A Label") ;
		add(label) ;
	}
	
	
	
	static SubmitSwingProgram2 ssp ;
	public static void main(String[] args) {
		ssp = new SubmitSwingProgram2() ;
		run(ssp, 300, 100);
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
