package com.yicj.swing.s2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static com.yicj.study.common.util.SwingConsole.run;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class TrackEvent extends JFrame {

	private HashMap<String, JTextField> h = 
			new HashMap<String,JTextField>() ;
	private String[] event = {
		"focusGained", "focusLost", "keyPressed",
		"keyReleased", "keyTyped", "mouseClicked", 
		"mouseEntered", "mouseExited", "mousePressed",
		"mouseReleased", "mouseDragged", "mouseMoved"
	} ;
	private MyButtton 
		b1 = new MyButtton(Color.BLUE, "test1"),
		b2 = new MyButtton(Color.RED, "test2") ;
	class MyButtton extends JButton{
		void report(String field, String msg) {
			h.get(field).setText(msg);
		}
		FocusListener fl = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				report("focusGained", e.paramString());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				report("focusLost", e.paramString());
			}
		};
		
		KeyListener kl = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				report("keyTyped", e.paramString());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				report("keyReleased", e.paramString());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				report("keyPressed", e.paramString());
			}
		};
		
		MouseListener ml = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				report("mouseReleased", e.paramString());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				report("mousePressed", e.paramString());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				report("mouseExited", e.paramString());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				report("mouseEntered", e.paramString());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				report("mouseClicked", e.paramString());
			}
		};
		
		MouseMotionListener mml = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				report("mouseMoved", e.paramString());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				report("mouseDragged", e.paramString());
			}
		};
		
		public MyButtton(Color color, String label) {
			super(label) ;
			setBackground(color);
			addFocusListener(fl);
			addKeyListener(kl);
			addMouseListener(ml);
			addMouseMotionListener(mml);
		}
		
	}
	public TrackEvent() {
		setLayout(new GridLayout(event.length + 1, 2));
		for(String evt : event) {
			JTextField t = new JTextField() ;
			t.setEditable(false);
			add(new JLabel(evt, JLabel.RIGHT)) ;
			add(t) ;
			h.put(evt, t) ;
		}
		add(b1) ;
		add(b2) ;
	}
	
	public static void main(String[] args) {
		run(new TrackEvent(), 700, 500);
	}

}
