package lostinfomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Forb{}
class Fnorkle{}
class Quark<Q>{}
class Particle<POSITION,MOMENTUM>{}

public class LostInfomation {
	
	public static void main(String[] args) {
		List<Forb> list = new ArrayList<Forb>() ;
		Map<Forb,Fnorkle> map = new HashMap<Forb,Fnorkle>() ;
		Quark<Fnorkle> quark = new Quark<Fnorkle>() ;
		Particle<Long,Double> p = new Particle<Long,Double>() ;
		System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
		System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
		System.out.println(quark.getClass().getTypeParameters());
		System.out.println(p.getClass().getTypeParameters());
	}
}
