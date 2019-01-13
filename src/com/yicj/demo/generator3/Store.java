package com.yicj.demo.generator3;

import java.util.ArrayList;
import java.util.Random;
import com.yicj.demo.generator1.Generator;
import com.yicj.demo.generator1.Generators;

class Product {
	private final int id ;
	private String description ;
	private double price ;
	public Product(int IDnumber, String descr, double price) {
		id = IDnumber ;
		description = descr ;
		this.price = price ;
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s , price : $ %f", this.id,this.description ,this.price);
	}
	
	public void priceChange(double change) {
		price += change ;
	}
	
	public static Generator<Product> generator = 
			new Generator<Product>() {
				private Random rand = new Random(47) ;
				@Override
				public Product next() {
					return new Product(rand.nextInt(1000),
							"Test", 
							0.99);
				}
			};
}

class Shelf extends ArrayList<Product>{
	public Shelf(int nProducts) {
		Generators.fill(this, Product.generator, nProducts) ;
	}
}

class Aisle extends ArrayList<Shelf>{
	public Aisle(int nShelves ,int nProducts) {
		for(int i = 0 ; i < nShelves ; i++)
			add(new Shelf(nProducts)) ;
	}
}

class CheckoutStand{}
class Office{}

public class Store extends ArrayList<Aisle> {
	private ArrayList<CheckoutStand> checkouts = 
			new ArrayList<CheckoutStand>() ;
	private Office office = new Office() ;
	public Store(int nAisles, int nShelves, int nProducts) {
		for(int i = 0 ; i < nAisles ; i++)
			add(new Aisle(nShelves, nProducts)) ;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder() ;
		for(Aisle a : this) {
			for(Shelf s : a) {
				for(Product p : s) {
					result.append(p) ;
					result.append("\n") ;
				}
			}
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new Store(14, 5, 10));
	}
	
		
}
