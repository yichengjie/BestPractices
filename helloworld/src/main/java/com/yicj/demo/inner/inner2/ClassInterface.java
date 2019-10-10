package com.yicj.demo.inner.inner2;
import static com.yicj.demo.common.util.CommonUtil.println;


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
