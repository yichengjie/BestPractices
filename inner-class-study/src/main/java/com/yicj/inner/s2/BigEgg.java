package com.yicj.inner.s2;

import static com.yicj.common.util.CommonUtil.println;

class Egg{
	private Yolk y ;
	protected class Yolk{
		public Yolk() {
			println("Egg.Yolk()");
		}
	}
	public Egg() {
		println("New Egg()");
		y = new Yolk();
	}
}



public class BigEgg extends Egg{
	public class Yolk{
		public Yolk() {
			println("BigEgg.Yolk()");
		}
	}
	public static void main(String[] args) {
		new BigEgg() ;
	}

}
