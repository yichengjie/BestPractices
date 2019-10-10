package com.yicj.study.constructor;

class Flower{
    /**
     * 让普通人喜欢
     */
    public void like(){
        System.out.println("like");
    }
}
class RoseFlower extends Flower{
    /**
     * 让女孩喜欢
     */
    public void love(){
        System.out.println("love....");
    }
}

class Person{
    public Flower buy(){
        System.out.println("buy Flower...");
        return new Flower();
    }
}
/**
 * 男孩
 */
class Boy extends Person{

    /**
     * 这里覆盖Person类的buy
     * @return
     */
    @Override
    public RoseFlower buy(){
        System.out.println("buy RoseFlower...");
        //注意这里，这里就是协变返回类型
        //导出类**（子类）覆盖（即重写）**基类**（父类）方法时，
        // 返回的类型可以是基类方法返回类型的子类
        return new RoseFlower();
    }
}


public class Ay {

	public static void main(String[] args) {
        Person person = new Boy();
        Flower flower = person.buy();
        flower.like();
        //! flower.love();  编译错误
        //因为是协变返回类型，所以可以向下转型
        RoseFlower roseFlower  = (RoseFlower) person.buy();
        //可以调用like方法
        roseFlower.like();
        //可以调用love方法
        roseFlower.love();
    }

}
