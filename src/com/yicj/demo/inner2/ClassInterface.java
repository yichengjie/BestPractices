package com.yicj.demo.inner2;
import static com.yicj.demo.CommonUtil.print ;


public interface ClassInterface {
	
	void howdy() ;
	class Test implements ClassInterface{
		@Override
		public void howdy() {
			print("Howdy!");
		}
		public static void main(String[] args) {
			new Test().howdy();
		}
	}

}
