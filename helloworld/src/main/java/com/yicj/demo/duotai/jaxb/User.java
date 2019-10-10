package com.yicj.demo.duotai.jaxb;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

//JavaBean代码

@XmlType(propOrder = {})
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class User implements Serializable {

    private String userName;
    private int age;
    private String role;
    private String bibi;

    public User() {
    }

    public User(String userName, int age, String role, String bibi) {
        this.userName = userName;
        this.age = age;
        this.role = role;
        this.bibi = bibi;
    }
    
    @XmlElement(required=true/*,defaultValue="zhangsan"*/)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public String getBibi() {
        return bibi;
    }

    public void setBibi(String bibi) {
        this.bibi = bibi;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", bibi='" + bibi + '\'' +
                '}';
    }
}

//测试
