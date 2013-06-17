package com.example.quting.database.inf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接接口
 * @author eoeye
 * @param <K>
 *
 */
public interface IDatabaseConnection {
	
	/**
	 * 打开连接
	 * @param <T>	返回数据库实例
	 * @param databaseName	数据库名称
	 * @throws Exception	异常
	 */
	public <T> T open( String databaseName ) throws Exception;
	/**
	 * 关闭
	 */
	public void close() throws Exception;
	/**
	 * 事务开始
	 */
	public void beginTransaction() throws Exception;
	/**
	 * 提交
	 */
	public void commit() throws Exception;
	/**
	 * 事务回滚
	 * @throws Exception
	 */
	public void rollBack() throws Exception;
	/**
	 * 事务结束
	 */
	public void endTransation() throws Exception;
	/**
	 * 插入新记录方法
	 * @param <T>
	 * @param table		表名
	 * @param columAndValue		用来保存的列名称以及对应的值
	 * @return
	 */
	public long insert( String table ,   HashMap<String, Object> columAndValue ) throws Exception;
	/**
	 * 根据数据id删除数据
	 * @param table		表名称
	 * @param idName  	表格ID的名字
	 * @param id		要删除的ID
	 * @return
	 */
	public long delete( String table , String idName , long id ) throws Exception;
	/**
	 * 更新记录的方法	
	 * @param table		表名称
	 * @param id		记录ID
	 * @param columAndValue		列名称以及对应的值
	 * @return
	 */
	public long update( String table , String idName , Long id , HashMap<String,Object> columAndValue ) throws Exception;
	/**
	 * 记录查询语句
	 * @param table				表名称
	 * @param distinct			是否去掉重复
	 * @param columns			列名
	 * @param where				条件语句
	 * @param whereArgs			条件参数值
	 * @param groupBy			groupBy 子句
	 * @param having			having 子句
	 * @param orderBy			orderBy子句
	 * @param limit				limit 子句
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> query( String table, boolean distinct , String[] columns , String where , String[] whereArgs ,
			String groupBy , String having , String orderBy , String limit ) throws Exception;
	/***
	 * 执行SQL语句
	 * @param sql
	 * @throws Exception
	 */
	public void execSQL(String sql)throws Exception;
}
