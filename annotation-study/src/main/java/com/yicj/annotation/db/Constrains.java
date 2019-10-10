package com.yicj.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constrains {
	boolean primaryKey() default false;
	boolean allowNull() default true ;
	boolean unique() default false ;
}