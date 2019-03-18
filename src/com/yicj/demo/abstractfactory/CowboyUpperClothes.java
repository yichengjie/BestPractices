package com.yicj.demo.abstractfactory;

public class CowboyUpperClothes extends UpperClothes {
	private int chestSize ;
	private int height ;
	private String name ;
	
	public CowboyUpperClothes(String name, int chestSize, int height) {
		this.name = name ;
		this.chestSize = chestSize ;
		this.height = height ;
	}
	
	@Override
	public int getChestSize() {
		return chestSize;
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
