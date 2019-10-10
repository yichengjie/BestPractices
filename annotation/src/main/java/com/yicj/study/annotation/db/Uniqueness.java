package com.yicj.study.annotation.db;

public @interface Uniqueness {
	Constrains constrains() default @Constrains(unique=true) ;
}
