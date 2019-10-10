package com.yicj.demo.duotai.abstractfactory;

public class Application {
	
	public static void main(String[] args) {
		
		Shop shop = new Shop() ;
		ClothesFactory factory = new BeijingClothesFactory() ;
		shop.giveSuit(factory, 110, 82, 170);
		System.out.println("---------------------");
		factory = new ShanghaiClothesFactory() ;
		shop.giveSuit(factory, 120, 88, 180);
	}

}
