package com.yicj.demo.constructors;
import static com.yicj.demo.common.util.CommonUtil.println;


class Glyph {
	void draw() {
		println("Glyph.draw()") ;
	}
	public Glyph() {
		println("Glyph() before draw()");
		draw();
		println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph{
	private int radius = 1 ;
	public RoundGlyph(int r) {
		radius = r ;
		println("RoundGlyph.RoundGlyph(). radius = " + radius);
	}
	
	void draw() {
		println("RoundGlyph.draw(). radius = " + radius);
	}
}


public class PolyConstructors {

	public static void main(String[] args) {
		new RoundGlyph(5) ;
	}
}
