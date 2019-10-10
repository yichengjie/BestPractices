package com.yicj.study.container.s2;

public class DequeTest {
	//[26, 25, 24, 23, 22, 21, 20,
	//50, 51, 52, 53, 54]
	static void fillTest(Deque<Integer> deque) {
		for(int i = 20 ; i < 27 ; i ++)
			deque.addFirst(i);
		for(int i =50 ; i< 55 ;i++)
			deque.addLast(i);
	}
	
	public static void main(String[] args) {
		Deque<Integer> di = new Deque<Integer>() ;
		fillTest(di);
		//26 25 24 23 22 21 20 50 51 52 53 54 
		System.out.println(di);
		while(di.size()!=0)
			System.out.print(di.removeFirst() +" ");
		System.out.println();
		fillTest(di) ;
		//54 53 52 51 50 20 21 22 23 24 25 26 
		while(di.size() !=0)
			System.out.print(di.removeLast() + " ");
	}
}
