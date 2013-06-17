package com.example.quting.database.annotataion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用来标示实体类的getter和setter，也用于表示数据库列字段
 * 根据此属性可以很快的查询到访问器和设置器
 * 由于没有标准的封装方法，设置这个属性比较实用，效率高、简便
 * @author eoeye
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumn {
	/**
	 * 设置器名称
	 * @return
	 */
	//String setterName();
	
	/**
	 * 获取器名称
	 * @return
	 */
	//String getterName();
}
