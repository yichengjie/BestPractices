package com.yicj.demo.generator.generator0;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.yicj.demo.common.util.Sets;


public class ContainerMethodDifferences {
	static Set<String> methodSet(Class<?> type){
		Set<String> result = new TreeSet<String>() ;
		for(Method m : type.getMethods()) {
			result.add(m.getName()) ;
		}
		return result ;
	}
	static void interfaces(Class<?> type) {
		System.out.print("Interfaces in " + type.getSimpleName());
		List<String> result = new ArrayList<String>() ;
		for(Class<?> c : type.getInterfaces()) {
			result.add(c.getSimpleName()) ;
		}
		System.out.println(result);
	}
	static Set<String> object = methodSet(Object.class) ;
	static {
		object.add("clone") ;
	}
	static void difference(Class<?> superset,Class<?> subset) {
		System.out.println(superset.getSimpleName() + 
				" extends " + subset.getSimpleName() + " , adds : ");
		Set<String> comp = Sets.difference(
				methodSet(superset), 
				methodSet(subset)) ;
		comp.removeAll(object) ; //Don't show 'Object' methods
		System.out.println("difference : " + comp);
		interfaces(superset);
	}
	
	public static void main(String[] args) {
		System.out.println("Collection : " + methodSet(Collection.class));
		System.out.println("---------------------------------------------");
		interfaces(Collection.class);
		System.out.println("---------------------------------------------");
		difference(Set.class, Collection.class);
		System.out.println("---------------------------------------------");
		difference(HashSet.class, Set.class);
		System.out.println("---------------------------------------------");
		difference(LinkedHashSet.class, HashSet.class);
		System.out.println("---------------------------------------------");
		
	}
	
}
