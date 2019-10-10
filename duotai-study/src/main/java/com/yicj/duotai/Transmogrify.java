package com.yicj.duotai;
import static com.yicj.common.util.CommonUtil.println;

class Actor{
	public void act() {}
}

class HappyActor extends Actor{
	public void act() {
		println("HappyActor") ;
	}
}

class SadActor extends Actor{
	public void act() {
		println("SadActor");
	}
}

class Stage {
	private Actor actor = new HappyActor() ;
	public void change(){
		actor = new SadActor() ;
	}
	public void performPlay(){
		actor.act();
	}
}


public class Transmogrify {
	public static void main(String[] args) {
		Stage stage = new Stage() ;
		stage.performPlay(); 
		stage.change(); 
		stage.performPlay();
	}
}
