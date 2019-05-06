package com.yicj.demo.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class JedisDemo01 {
	private Jedis jedis = new Jedis("localhost", 6379) ;
	
	
	public static void main(String[] args) {
        System.out.println("Connection to server sucessfully");
        JedisDemo01 demo = new JedisDemo01() ;
        demo.test();
	}
	
	public void test() {
		//this.addList();
		this.printList("tutorial-list");
		//this.printAllKey();
		//jedis.flushAll();
	}
	
	private void printAllKey() {
		Set<String> list1 =jedis.keys("*");
	    Iterator<String> ite=list1.iterator();
	    while(ite.hasNext()){
	        System.out.println("y======:"+ite.next());
	    }
	}
	
	private void printList(String key) {
		 List<String> list = jedis.lrange(key, 0 ,5);
	     for(int i=0; i<list.size(); i++) {
	        System.out.println("Stored string in redis:: "+list.get(i));
	     }
	}
	
	
	private void addList() {
	   jedis.lpush("tutorial-list", "Redis");
       jedis.lpush("tutorial-list", "Mongodb");
       jedis.lpush("tutorial-list", "Mysql");
	}
	
	private void addStr() {
	   jedis.set("runoobkey", "Redis tutorial");
	   jedis.set("a", "a1");
       jedis.set("b", "b1");
       jedis.set("c", "c1");
       jedis.set("d", "d1");
	}
	
	

}
