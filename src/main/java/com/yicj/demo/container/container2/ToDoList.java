package com.yicj.demo.container.container2;
import com.yicj.demo.container.container2.ToDoList.ToDoItem;
import java.util.PriorityQueue;

public class ToDoList extends PriorityQueue<ToDoItem> {
	
	static class ToDoItem implements Comparable<ToDoItem>{
		private char primary ;
		private int secondary ;
		private String item ;
		
		public ToDoItem(String td, char pri, int sec) {
			primary = pri ;
			secondary = sec ;
			item = td ;
		}
		
		@Override
		public int compareTo(ToDoItem o) {
			if(primary > o.primary)
				return +1 ;
			if(primary == o.primary)
				if(secondary > o.secondary) 
					return +1 ;
				else if(secondary == o.secondary)
					return 0 ;
			return -1;
		}
		@Override
		public String toString() {
			return Character.toString(primary) + 
					secondary +": " + item;
		}
	}
	public void add(String td, char pri, int sec) {
		super.add(new ToDoItem(td, pri, sec)) ;
	}
	
	public static void main(String[] args) {
		ToDoList toDoList = new ToDoList() ;
		toDoList.add("Empty trash", 'C', 4) ;
		toDoList.add("Feed dog", 'A', 2) ;
		toDoList.add("Feed bird", 'B', 7) ;
		toDoList.add("Mow lawn", 'C', 3) ;
		toDoList.add("Water lawn", 'A', 1) ;
		toDoList.add("Feed cat", 'B', 1) ;
		while(!toDoList.isEmpty())
			System.out.println(toDoList.remove());
	}
}
