package com.yicj.container.s1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Snow{}
class Powder extends Snow{}
class Light extends Powder{}
class Heavy extends Powder{}
//
class Crusty extends Snow{}
class Slush extends Snow{}


public class AsListInference {

	public static void main(String[] args) {
		
		List<Snow> snow1 = Arrays.asList(
			new Crusty(),new Slush(),new Powder()) ;
		
		// won't compile 
		//List<Snow> snow2 = Arrays.asList(
			//new Light(),new Heavy()) ;
		//found : java.util.List<Powder>
		//required : java.util.List<Snow>
		List<Snow> snow3 = new ArrayList<Snow>() ;
		Collections.addAll(snow3, new Light(),new Heavy()) ;
		// give a hint using an 
		// explicit type argument specificaiton
		List<Snow> snow4 = Arrays.<Snow>asList(
			new Light(),new Heavy()) ;
		
	}
	
	
}
