package com.yicj.demo.annotation.db;

public @interface Uniqueness {
	Constrains constrains() default @Constrains(unique=true) ;
}
