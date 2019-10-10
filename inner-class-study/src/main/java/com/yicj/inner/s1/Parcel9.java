package com.yicj.inner.s1;

public class Parcel9 {
	
	public Destination destination(final String dest) {
		
		return new Destination() {
			private String label = dest ;
			@Override
			public String readLabel() {
				return label;
			}
		};
	}
	
	public static void main(String[] args) {
		Parcel9 p = new Parcel9() ;
		Destination d = p.destination("Tasmania") ;
	}

}
