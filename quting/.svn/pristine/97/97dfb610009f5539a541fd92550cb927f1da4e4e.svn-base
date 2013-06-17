package com.example.quting.database.model;

import com.example.quting.database.annotataion.DatabaseName;
import com.example.quting.database.annotataion.IndentifyID;
import com.example.quting.database.annotataion.TableColumn;
import com.example.quting.database.annotataion.TableName;
/**
 * 此处是一个实体的示例，采用注解方式标示数据库名称
 * 以及表名称
 * @see DatabaseName	数据库名称
 * @see TableName		表名称
 * @see IndentifyID	表ID
 * @see TableColumn	属性名称所对应的setter和getter方法，在安卓系统中由于没有EJB bean需要定义bean的结构
 * @author eoeye
 *
 */
@DatabaseName( name="ezIUMS")
@TableName ( name = "user" )
public class UserName extends BaseEntity{
	@IndentifyID
	@TableColumn
	private Long id ;
	@TableColumn
	private String userName;
	@TableColumn
	private String userPassword;
	@TableColumn
	private Boolean isNow;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Boolean getIsNow() {
		return isNow;
	}
	public void setIsNow(Boolean isNow) {
		this.isNow = isNow;
	}

	@Override
	public String createStructure() {
		String sql="CREATE TABLE if not exists user (" +
				"id Integer PRIMARY KEY NOT NULL," +
				" userName varchar(255), " +
				" userPassword varchar(255), " +
				" isNow BOOLEAN" +
				");";

		return sql;
	}
}
