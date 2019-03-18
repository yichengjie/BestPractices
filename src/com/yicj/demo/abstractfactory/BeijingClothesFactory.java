package com.yicj.demo.abstractfactory;

public class BeijingClothesFactory extends ClothesFactory {

	@Override
	public UpperClothes createUpperClothes(int chestSize, int height) {
		return new WesternUpperClothes("北京牌西服上衣", chestSize, height);
	}

	@Override
	public Trousers createTrousers(int waistSize, int height) {
		return new WesternTrousers("北京牌西服裤子", waistSize, height);
	}

}
