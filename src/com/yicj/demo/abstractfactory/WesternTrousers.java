package com.yicj.demo.abstractfactory;

public class WesternTrousers extends Trousers {
	
	private int waistSize ;
	private int height ;
	private String name ;
	
	public WesternTrousers(String name, int waistSize, int height) {
		this.name = name ;
		this.waistSize = waistSize ;
		this.height = height ;
	}

	@Override
	public int getWaistSize() {
		return waistSize;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getName() {
		return name;
	}

}
