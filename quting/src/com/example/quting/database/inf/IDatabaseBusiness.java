package com.example.quting.database.inf;

import java.util.List;
/**
 * 数据库业务支持接口
 * @author eoeye
 *
 */
public interface IDatabaseBusiness {
	
	/**
	 * 更新方法
	 * @param <T>
	 * @param record	记录对象
	 * @return			更新记录的个数，如果为－1则更新失败
	 */
	public <T> long update( T records ) throws Exception;
	
	/**
	 * 更新方法
	 * @param <T>
	 * @param records	所有纪录
	 * @return			更新记录的个数，如果为－1则更新失败
	 */
	public <T> long updateAll( List<T> records ) throws Exception;
	/**
	 * 插入新纪录的方法
	 * @param records	记录对象
	 * @return			新纪录的ID数组，如果为－1则插入失败
	 * @throws Exception 
	 */
	public <T> long insert( T records ) throws Exception;
	
	/**
	 * 插入新纪录的方法
	 * @param records	记录对象
	 * @return			新纪录的ID数组，如果为null则插入失败
	 * @throws Exception 
	 */
	public <T> long[] insertAll( List<T> records ) throws Exception;
	
	/**
	 * 删除方法
	 * @param <T>
	 * @param record	记录对象
	 * @return			删除记录的ID，如果为－1则删除失败
	 */
	public <T> long delete( T record ) throws Exception;

	/**
	 * 删除方法
	 * @param <T>
	 * @param records	纪录对象
	 * @return			删除记录的ID，如果为－1则删除失败
	 */
	public <T> long deleteAll( List<T> records ) throws Exception;
	/**
	 * 记录查询
	 * @param <T>
	 * @param table				表
	 * @param distinct			是否去除重复
	 * @param where				where 子句
	 * @param args				参数列表
	 * @param groupBY			groupBy 子句
	 * @param having			having 子句
	 * @param orderBy			排序子句
	 * @param limit				limit子句
	 * @return					结果集合
	 * @throws Exception
	 */
	public <T> List<T> query( T table , boolean distinct , String where , String[] args , 
			String groupBY , String having , String orderBy , String limit ) throws Exception;
}
