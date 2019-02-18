package com.yicj.demo.jiekou;

import com.yicj.demo.init2.Note;
import com.yicj.demo.jiekou.Brass;
import com.yicj.demo.jiekou.Instrument;
import com.yicj.demo.jiekou.Percussion;
import com.yicj.demo.jiekou.Stringed;
import com.yicj.demo.jiekou.Wind;
import com.yicj.demo.jiekou.Woodwind;

import static com.yicj.demo.CommonUtil.print;

abstract class Instrument{
	private int i ; //Storage allocated for each
	public abstract void play(Note n) ;
	public String what() {
		return "Instrument" ;
	}
	public abstract void adjust() ;
}

class Wind extends Instrument{
	@Override
	public void play(Note n) {
		print("Wind.play() " + n);
	}
	@Override
	public String what() {return "Wind";}
	@Override
	public void adjust() {}
}

class Percussion extends Instrument{
	@Override
	public void play(Note n) {
		print("Percussion.play() "+ n);
	}
	@Override
	public String what() {return "Percussion";}
	@Override
	public void adjust() {}
}

class Stringed extends Instrument{
	@Override
	public void play(Note n) {
		print("Stringed.play() " + n);
	}
	@Override
	public String what() {return "Stringed";}
	@Override
	public void adjust() {}
}

class Brass extends Wind{
	@Override
	public void play(Note n) {
		print("Brass.play() " + n);
	}
	@Override
	public void adjust() {
		print("Brass.adjust()");
	}
}

class Woodwind extends Wind{
	@Override
	public void play(Note n) {
		print("Woodwind.play() " + n);
	}
	@Override
	public String what() {
		return "Woodwind";
	}
}

public class Music4 {
	// Doesn't care about type, so new types 
	// added to the system still work right:
	static void tune(Instrument i) {
		i.play(Note.MODDLE_C);
	}
	
	static void tuneAll(Instrument[] e) {
		for(Instrument i : e) {
			tune(i);
		}
	}
	public static void main(String[] args) {
		// Upcasting during addition to the array:
		Instrument[] orchestra = {
				new Wind(),
				new Percussion(),
				new Stringed(),
				new Brass(),
				new Woodwind()
		} ;
		tuneAll(orchestra);
	}
}
