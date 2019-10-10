package com.yicj.annotation.db;

public @interface Uniqueness {
	Constrains constrains() default @Constrains(unique=true) ;
}
