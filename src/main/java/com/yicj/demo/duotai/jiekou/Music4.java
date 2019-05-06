package com.yicj.demo.duotai.jiekou;

import static com.yicj.demo.common.util.CommonUtil.println;

import com.yicj.demo.init.init2.Note;



public class Music4 {
	static abstract class Instrument{
		private int i ; //Storage allocated for each
		public abstract void play(Note n) ;
		public String what() {
			return "Instrument" ;
		}
		public abstract void adjust() ;
	}

	static class Wind extends Instrument {
		@Override
		public void play(Note n) {
			println("Wind.play() " + n);
		}
		@Override
		public String what() {return "Wind";}
		@Override
		public void adjust() {}
	}

	static class Percussion extends Instrument {
		@Override
		public void play(Note n) {
			println("Percussion.play() "+ n);
		}
		@Override
		public String what() {return "Percussion";}
		@Override
		public void adjust() {}
	}

	static class Stringed extends Instrument {
		@Override
		public void play(Note n) {
			println("Stringed.play() " + n);
		}
		@Override
		public String what() {return "Stringed";}
		@Override
		public void adjust() {}
	}

	static class Brass extends Wind {
		@Override
		public void play(Note n) {
			println("Brass.play() " + n);
		}
		@Override
		public void adjust() {
			println("Brass.adjust()");
		}
	}

	static class Woodwind extends Wind {
		@Override
		public void play(Note n) {
			println("Woodwind.play() " + n);
		}
		@Override
		public String what() {
			return "Woodwind";
		}
	}

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
