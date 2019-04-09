package com.yicj.demo.inner.inner1;

public class Parcel5 {
	public Destination destination(String s) {
		class PDestination implements Destination{
			private String label ;
			private PDestination(String whereTo) {
				label = whereTo ;
			}
			@Override
			public String readLabel() {
				return null;
			}
		}
		return new PDestination(s) ;
	}
	public static void main(String[] args) {
		Parcel5 p = new Parcel5() ;
		Destination d = p.destination("Tasmania") ;
	}
	
}
