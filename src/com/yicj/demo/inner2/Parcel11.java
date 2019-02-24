package com.yicj.demo.inner2;

import com.yicj.demo.inner.Contents;
import com.yicj.demo.inner.Destination;

public class Parcel11 {
	
	private static class ParcelContents implements Contents{
		private int i = 11 ;
		@Override
		public int value() {
			return i;
		}
	}
	
	protected static class PracelDestination 
	implements Destination{
		private String label ;
		private PracelDestination(String whereTo) {
			label = whereTo ;
		}
		@Override
		public String readLabel() {
			return label;
		}
		// Nested classes can contain other static elements:
		public static void f() {}
		static int x = 10 ;
		static class AnotherLevel{
			public static void f() {}
			static int x = 10 ;
		}
	}
	
	public static Destination destination(String s) {
		return new PracelDestination(s) ;
	}
	
	public static Contents contents() {
		return new ParcelContents() ;
	}
	
	public static void main(String[] args) {
		Contents c = contents() ;
		Destination d = destination("Tasmania") ;
	}
	
}
