package com.yicj.demo.init3;
import static com.yicj.demo.CommonUtil.print; 

class Characteristic{
	private String s ;
	Characteristic(String s){
		this.s = s ;
		print("Creating Characteristic " + s);
	}
	protected void dispose(){
		print("disposing Characteristic " + s);
	}
}

class Description {
	private String s ;
	Description(String s){
		this.s =s ;
		print("Creating Description " + s);
	}
	protected void dispose(){
		print("");
	}
}



public class Forg {

}
