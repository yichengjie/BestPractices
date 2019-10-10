package com.yicj.swing.s2;

import static com.yicj.study.common.util.SwingConsole.run;

import java.awt.FlowLayout;
import java.lang.reflect.Constructor;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class ButtonGroups extends JFrame {
	private static String[] ids = {
		"June", "Ward", "Beaver", "Wally", "Eddie", "Lumpy"
	} ;
	static JPanel makeBPanel(
		Class<? extends AbstractButton> kind, String[] ids) {
		ButtonGroup bg = new ButtonGroup() ;
		JPanel jp = new JPanel() ;
		String title = kind.getName() ;
		System.out.println("title : " + title);
		title = title.substring(title.lastIndexOf('.') + 1) ;
		jp.setBorder(new TitledBorder(title));
		for(String id : ids) {
			AbstractButton ab = new JButton("failed") ;
			try {
				// Get the dynamic constructor method
				// that takes a String argument:
				Constructor<? extends AbstractButton> ctor = kind.getConstructor(String.class);
				// Create a new object:
				ab = (AbstractButton)ctor.newInstance(id) ;
			} catch (Exception e) {
				System.err.println("can't create " + kind);
			}
			bg.add(ab);
			jp.add(ab) ;
		}
		return jp ;
	}
	public ButtonGroups() {
		setLayout(new FlowLayout());
		add(makeBPanel(JButton.class, ids)) ;
		add(makeBPanel(JToggleButton.class, ids)) ;
		add(makeBPanel(JCheckBox.class, ids)) ;
		add(makeBPanel(JRadioButton.class, ids)) ;
	}
	
	public static void main(String[] args) {
		run(new ButtonGroups(), 500, 350);
	}

}
