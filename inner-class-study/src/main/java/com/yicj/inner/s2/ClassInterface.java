package com.yicj.inner.s2;
import static com.yicj.common.util.CommonUtil.println;


public interface ClassInterface {
	
	void howdy() ;
	class Test implements ClassInterface{
		@Override
		public void howdy() {
			println("Howdy!");
		}
		public static void main(String[] args) {
			new Test().howdy();
		}
	}

}
