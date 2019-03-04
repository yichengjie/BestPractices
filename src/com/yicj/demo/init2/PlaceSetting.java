package com.yicj.demo.init2;
import static com.yicj.demo.util.CommonUtil.println;

class Plate{
	Plate(int i){
		println("Plate constructor");
	}
}

class DinnerPlate extends Plate{
	DinnerPlate(int i) {
		super(i) ;
		println("DinnerPlate constructor");
	}
}

class Utensil extends Plate{

	Utensil(int i) {
		super(i);
		println("Utensil constructor");
	}
}

class Spoon extends Utensil{
	Spoon(int i) {
		super(i);
		println("Spoon constructor");
	}
}

class Fork extends Utensil{
	Fork(int i) {
		super(i);
		println("Fork constructor");
	}
}

class Knife extends Utensil{
	Knife(int i) {
		super(i);
		println("Knife constructor");
	}
}


class Custom{
	Custom(int i) {
		println("Custom constructor") ;
	}
}


public class PlaceSetting extends Custom{
	private Spoon sp ;
	private Fork frk ;
	private Knife kn ;
	private DinnerPlate pl ;
	
	PlaceSetting(int i) {
		super(i + 1);
		sp = new Spoon(i +2);
		frk  = new Fork(i + 3) ;
		kn = new Knife(i + 4) ;
		pl = new DinnerPlate(i + 5) ;
		println("PlaceSetting constructor");
	}
	public static void main(String[] args) {
		PlaceSetting x = new PlaceSetting(9) ;
	}
}
