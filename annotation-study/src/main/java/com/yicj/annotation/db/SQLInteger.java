package com.yicj.annotation.db;

public @interface SQLInteger {
	String name() default ""; 
	Constrains constrains() default @Constrains ;
}
