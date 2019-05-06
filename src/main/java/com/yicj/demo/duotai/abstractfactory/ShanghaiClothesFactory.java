package com.yicj.demo.duotai.abstractfactory;

public class ShanghaiClothesFactory extends ClothesFactory {

	@Override
	public UpperClothes createUpperClothes(int chestSize, int height) {
		return new CowboyUpperClothes("上海牌牛仔上衣", chestSize, height);
	}

	@Override
	public Trousers createTrousers(int waistSize, int height) {
		return new CowboyTrousers("上海牌牛仔裤", waistSize, height);
	}

}
