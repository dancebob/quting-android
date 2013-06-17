package com.example.quting.database.annotataion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用于表示实体类所对应的数据库的名称
 * 要在类上进行注解
 * @author eoeye
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseName {
	/**
	 * 数据库名称
	 * @return	数据库名称
	 */
	String name();
}
