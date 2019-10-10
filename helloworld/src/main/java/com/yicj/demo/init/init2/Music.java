package com.yicj.demo.init.init2;
import static com.yicj.demo.common.util.CommonUtil.println;

class Instrument{
	public void play(Note n) {
		println("Instrument.play()") ;
	}
}
class Wind extends Instrument{
	public void play(Note n) {
		System.out.println("Wind.play()" + n);
	}
}

public class Music {
	
	public static void tune(Instrument i) {
		i.play(Note.MODDLE_C);
	}
	
	public static void main(String[] args) {
		
		Wind flute = new Wind() ;
		tune(flute);
		
	}
}
