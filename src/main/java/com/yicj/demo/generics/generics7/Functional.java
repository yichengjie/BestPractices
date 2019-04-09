package com.yicj.demo.generics.generics7;

import static com.yicj.demo.util.CommonUtil.println;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



interface Combiner<T> {
	T combine(T x , T y) ;
}

interface UnaryFunction<R,T> {
	R function(T x) ;
}

interface Collector<T> extends UnaryFunction<T, T>{
	T result() ;
}

interface UnaryPredicate<T> {
	boolean test(T x) ;
}


public class Functional {
	//Calls the combiner object on each element to combine
	//it with a running result .which is finally returned:
	public static <T> T reduce(Iterable<T> seq ,
			Combiner<T> combiner){
		Iterator<T> it = seq.iterator() ;
		if(it.hasNext()){
			T result = it.next() ;
			while(it.hasNext()){
				result = combiner.combine(result, it.next()) ;
			}
			return result ;
		}
		//If seq is the empty list:
		return null ;
	}
	// Take a function object and call it on eash object in
	// the list , ignoring the return value . The function
	// object may act as a collectioning parameter . so it is
	// returned at the end.
	public static <T> Collector<T> forEach(Iterable<T> seq , Collector<T> func){
		for(T t : seq){
			func.function(t) ;
		}
		return func ;
	}
	// Creates a list of results by calling a
	// function object for each object in the list:
	public static <R,T> List<R> transform(Iterable<T> seq , 
			UnaryFunction<R, T> func){
		List<R> result = new ArrayList<R>(); 
		for(T t : seq){
			result.add(func.function(t)) ;
		}
		return result ;
	}
	// Applies a unary predicate to each item in a sequence .
	// and return a list of items that produced "true":
	public static <T> List<T> filter(Iterable<T> seq, 
			UnaryPredicate<T> pred){
		List<T> result = new ArrayList<T>() ;
		for(T t : seq){
			if(pred.test(t)){
				result.add(t) ;
			}
		}
		return result ;
	}
	// To use the above generic methods ,we need to create 
	// function objects to adapt to our particular needs:
	static class IntegerAdder implements Combiner<Integer> {
		@Override
		public Integer combine(Integer x, Integer y) {
			return x + y;
		}
	}
	
	static class IntegerSubtracter implements Combiner<Integer>{
		@Override
		public Integer combine(Integer x, Integer y) {
			return x - y;
		}
	}
	
	static class BigDecimalAdder implements Combiner<BigDecimal>{
		@Override
		public BigDecimal combine(BigDecimal x, BigDecimal y) {
			return x.add(y);
		}
	}
	
	static class BigIntegerAdder implements Combiner<BigInteger>{
		@Override
		public BigInteger combine(BigInteger x, BigInteger y) {
			return x.add(y);
		}
	}
	
	static class AtomicLongAdder implements Combiner<AtomicLong>{
		@Override
		public AtomicLong combine(AtomicLong x, AtomicLong y) {
			// Not clear whether this is meaningful
			return new AtomicLong(x.addAndGet(y.get()));
		}
	}
	//We can even make a unaryFunction with an "ulp"
	//(Units in the last place) :
	static class BigDecimalUlp 
	implements UnaryFunction<BigDecimal, BigDecimal>{
		@Override
		public BigDecimal function(BigDecimal x) {
			return x.ulp();
		}
	}
	
	static class GreaterThan<T extends Comparable<T>> 
	implements UnaryPredicate<T>{
		private T bound ;
		public GreaterThan(T bound){
			this.bound = bound ;
		}
		@Override
		public boolean test(T x) {
			return x.compareTo(bound) > 0; 
		}
	}
	
	static class MultiplyingInteger
	implements Collector<Integer>{
		private Integer val = 1 ;
		@Override
		public Integer function(Integer x) {
			val *= x ;
			return val ;
		}

		@Override
		public Integer result() {
			return val;
		}
	}
	
	public static void main(String[] args) {
		
		//Generics , varags & boxing working together :
		List<Integer> li = Arrays.asList(1,2,3,4,5,6,7) ;
		Integer result = reduce(li, new IntegerAdder()) ;
		println(result);
		//
		result = reduce(li, new IntegerSubtracter()) ;
		println(forEach(li, new MultiplyingInteger()).result());
		//
		MathContext mc = new MathContext(7) ;
		List<BigDecimal> lbd = Arrays.asList(
				new BigDecimal(1.2,mc) ,new BigDecimal(2.2,mc) ,
				new BigDecimal(3.3,mc) ,new BigDecimal(4.4,mc)) ;
		BigDecimal rbd = reduce(lbd, new BigDecimalAdder()) ;
		println(rbd) ;
		//
		println(filter(lbd, 
				new GreaterThan<BigDecimal>(new BigDecimal(3)))) ;
		//
		//Use the prime-generation facility of BigInteger:
		List<BigInteger> lbi = new ArrayList<BigInteger>() ; 	
		BigInteger bi = BigInteger.valueOf(1l) ;
		for(int i = 0 ; i < 11 ; i++) {
			lbi.add(bi) ;
			bi = bi.nextProbablePrime() ;
		}
		println(lbi);
		//
		BigInteger rbi = reduce(lbi, new BigIntegerAdder()) ;
		println(rbi) ;
		//The sum of this list of primes is also prime:
		println(rbi.isProbablePrime(5));
		//
		List<AtomicLong> lal = Arrays.asList(
				new AtomicLong(11), new AtomicLong(47),
				new AtomicLong(74), new AtomicLong(133)) ;
		AtomicLong ral = reduce(lal, new AtomicLongAdder()) ;
		println(ral);
		//
		println(transform(lbd, new BigDecimalUlp())) ;
	}
	
}
