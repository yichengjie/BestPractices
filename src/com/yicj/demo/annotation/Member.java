package com.yicj.demo.annotation;

import com.yicj.demo.annotation.db.Constrains;
import com.yicj.demo.annotation.db.SQLInteger;
import com.yicj.demo.annotation.db.SQLString;

public class Member {
	@SQLString(30)
	String firstName ;
	@SQLString(50)
	String lastName ;
	@SQLInteger 
	Integer age ;
	@SQLString(value=30,constrains=@Constrains(primaryKey=true))
	String handle ;
	static int memberCount ;
	public String getHandle() {
		return handle ;
	}
	public String getFirstName() {
		return firstName ;
	}
	public String getLastName() {
		return lastName ;
	}
	public String toString() {
		return handle ;
	}
	public Integer getAge() {
		return age ;
	}
}