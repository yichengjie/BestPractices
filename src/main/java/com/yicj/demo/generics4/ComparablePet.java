package com.yicj.demo.generics4;


public class ComparablePet implements Comparable<ComparablePet>{
	@Override
	public int compareTo(ComparablePet arg) {
		return 0;
	}
}

//class Cat extends ComparablePet  implements Comparable<Cat>{
//
//	@Override
//	public int compareTo(Cat o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
//}