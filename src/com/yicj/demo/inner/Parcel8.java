package com.yicj.demo.inner;


public class Parcel8 {
	
	public Wrapping wrapping(int x) {
		//Base constructor call
		return new Wrapping(x) {
			@Override
			public int value() {
				return super.value() * 47;
			}
		}; // Semicolon required
	}
	public static void main(String[] args) {
		Parcel8 p = new Parcel8() ;
		Wrapping w = p.wrapping(10) ;
	}

}