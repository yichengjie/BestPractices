package com.yicj.demo.generics.generics8;

import com.yicj.demo.common.util.CommonUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;


interface IBaseDao<T> {
     void findById(Serializable id) ;

}

abstract class BaseDaoImpl<T> implements IBaseDao<T> {
    private Class<T> clazz ;
    public BaseDaoImpl(){
        //获取当前实例的class
        Class<?> curClazz =  this.getClass() ;
        //获取参数化父类类型
        ParameterizedType type = (ParameterizedType)curClazz.getGenericSuperclass() ;
        clazz = (Class<T>)type.getActualTypeArguments()[0] ;
    }

    @Override
    public void findById(Serializable id) {

        System.out.println("class name : " + clazz.getSimpleName());
    }
}

class Test<T> {

    public Class<T> getTClass(){
        return (Class<T>)((ParameterizedType)this
                .getClass().getGenericSuperclass())
                .getActualTypeArguments()[0] ;
    }

    public void test(){
        System.out.println(getTClass().getSimpleName());
    }

    public static void main(String[] args) {
        //注意这里需要使用匿名内部类，不然获取不到
        new Test<User>(){}.test();
    }

}


class User{
    private String username ;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}

interface IUserDao extends IBaseDao<User>{
}

class UserDaoImpl extends BaseDaoImpl<User>  implements IUserDao{
}


public class App1 {

    public void testFindById(){
        IUserDao userDao = new UserDaoImpl() ;
        userDao.findById(1);
    }

    public static void main(String[] args) {
//        App1 app1 = new App1() ;
//        app1.testFindById();
        Test test = new Test() ;
        test.getTClass() ;

    }

}
