package com.yicj.demo.jiekou2;

import com.yicj.demo.init2.Note;
import static com.yicj.demo.CommonUtil.print;

interface Instrument{
	// Compile-time constant:
	int VALUE = 5 ; //static & final
	// Cannot have method definitions:
	void play(Note n) ; // Automatically public
	void adjust() ;
}

class Wind implements Instrument{
	@Override
	public void play(Note n) {
		print(this + ".play() " + n);
	}
	@Override
	public String toString() {return "Wind";}
	@Override
	public void adjust() {
		print(this + ".adjust()");
	}
}

class Percussion implements Instrument{
	@Override
	public void play(Note n) {
		print(this + ".play() "+ n);
	}
	@Override
	public String toString() {return "Percussion";}
	@Override
	public void adjust() {
		print(this + ".adjust()");
	}
}

class Stringed implements Instrument{
	@Override
	public void play(Note n) {
		print(this + ".play() " + n);
	}
	@Override
	public String toString() {return "Stringed";}
	@Override
	public void adjust() {
		print(this + ".adjust()");
	}
}

class Brass extends Wind{
	@Override
	public String toString() {
		return "Brass";
	}
}

class Woodwind extends Wind{
	@Override
	public String toString() {
		return "Woodwind";
	}
}

public class Music5 {
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
