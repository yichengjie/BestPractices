package com.yicj.demo.inner.inner1;

public class Parcel1 {
	
	class Contents{
		private int i = 11 ;
		public int value(){
			return i ;
		}
	}
	
	class Destination{
		private String label ;
		Destination(String whereTo) {
			label = whereTo ;
		}
		public String readLabel(){
			return label ;
		}
	}
	
	//Using inner classes looks just like 
	//uing any other class ,within Parcel1
	public void ship(String dest){
		Contents c = new Contents() ;
		Destination d = new Destination(dest) ;
		System.out.println(d.readLabel());
	}
	
	public static void main(String[] args) {
		Parcel1 p = new Parcel1() ;
		p.ship("Tasmania");
	}
}