package com.example.quting.database;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quting.database.annotataion.DatabaseName;
import com.example.quting.database.annotataion.IndentifyID;
import com.example.quting.database.annotataion.TableColumn;
import com.example.quting.database.annotataion.TableName;
import com.example.quting.database.inf.IDatabaseBusiness;
import com.example.quting.database.inf.IDatabaseConnection;
import com.example.quting.database.model.BaseEntity;

public class DatabaseBusiness implements IDatabaseBusiness {
	/**
	 * connection 数据库连接对象 instance 自身实例 _lock
	 * 信号锁：在系统运行过程中不允许打开多个数据库，当已经打开数据库时，所有请求线程必须排队
	 */
	private IDatabaseConnection connection = new AndroidConnection();
	private static DatabaseBusiness instance;
	private Object _connectionLock = new Object();

	public static DatabaseBusiness instance() {
		if (instance == null)
			instance = new DatabaseBusiness();
		return instance;
	}

	@Override
	public <T> long updateAll(List<T> records) throws Exception {
		if (records == null || records.size() == 0)
			return -1l;
		else if (records.size() > 0) {
			checkEntity(records.get(0));
			// 获得表明、数据库名
			DatabaseName databaseName = records.get(0).getClass().getAnnotation(DatabaseName.class);
			if (databaseName == null)
				return -1;
			TableName tableName = records.get(0).getClass().getAnnotation(TableName.class);
			if (tableName == null)
				return -1;
			boolean wrongOccured = false;
			// 进行批量操作同时禁止再次打开数据库
			synchronized (_connectionLock) {
				SQLiteDatabase sqlDatabase = connection.open(databaseName.name());// 打开数据库连接
				sqlDatabase.beginTransaction();
				int count = 0;
				try {
					for (T record : records) {

						// 纪录id属性
						IndentifyID idField = null;
						String idfieldName = null;
						Long id = null;
						HashMap<String, Object> params = new HashMap<String, Object>();
						// 获取属性字段
						for (Field field : record.getClass().getDeclaredFields()) {
							String fieldName = field.getName();// 属性名称
							// 查找获取器
							TableColumn methodName = field.getAnnotation(TableColumn.class);
							if (null==methodName) continue;
							//Method m = record.getClass().getMethod(methodName.getterName());
							//Object obj = m.invoke(record);
							field.setAccessible(true);
							Object obj = field.get(record);
							if (obj != null)
								params.put(fieldName, obj);
							if (idField == null)
								idField = field.getAnnotation(IndentifyID.class);
							if (idField != null && idfieldName == null) {
								idfieldName = fieldName;
								id = (Long) obj;
							}

						}
						if (idField == null || id == null)
							continue;
						long result = connection.update(tableName.name(), idfieldName, id, params);
						idfieldName = null;
						idField = null;
						if (result > 0)
							count += result;
						else if (result == 0 || result == -1) {
							wrongOccured = true;
							break;
						}

					}

				} catch (Exception e) {
					connection.rollBack();
					connection.endTransation();
					connection.close();

					throw (e);
				} finally {
				}
				if (!wrongOccured) {
					connection.commit();
					connection.endTransation();
					connection.close();
					return count;
				} else {
					connection.endTransation();
					connection.close();
					return -1l;
				}

			}
		}
		return -1l;
	}

	@Override
	public <T> long[] insertAll(List<T> records) throws Exception {
		if (records != null && records.size() > 0) {
			checkEntity(records.get(0));
			DatabaseName databaseName = records.get(0).getClass().getAnnotation(DatabaseName.class);
			if (databaseName == null)
				return null;
			TableName tableName = records.get(0).getClass().getAnnotation(TableName.class);
			if (tableName == null)
				return null;
			// 进行批量操作同时禁止再次打开数据库
			boolean wrongOccured = false;
			synchronized (_connectionLock) {
				connection.open(databaseName.name());// 打开数据库连接
				HashMap<String, Object> hashMap;
				long[] ids = new long[records.size()];
				connection.beginTransaction();
				try {
					/*
					 * 根据记录中的类属性获得属性的值 将这些值保存到数据库
					 */
					for (int i = 0; i < records.size(); i++) {
							hashMap = new HashMap<String, Object>();
						// 获取属性字段
						for (Field field : records.get(i).getClass().getDeclaredFields()) {
							IndentifyID indentifyID = field.getAnnotation(IndentifyID.class);
							if (indentifyID != null)
								continue;
							field.setAccessible(true);
							String fieldName = field.getName();// 属性名称
							// String fieldType =
							// field.getGenericType().toString();//属性类型
							TableColumn methodName = field.getAnnotation(TableColumn.class);
							if (methodName != null) {
								// 查找获取器
								//Method m = records.get(i).getClass().getMethod(methodName.getterName());
								//hashMap.put(fieldName, m.invoke(records.get(i)));
								hashMap.put(fieldName, field.get(records.get(i)));
								}
						}
						ids[i] = connection.insert(tableName.name(), hashMap);
						if (ids[i] == -1l) {
							wrongOccured = true;
							break;
						}
					}
				} catch (Exception e) {
					connection.endTransation();
					connection.close();
					throw e;
				} finally {
				}
				if (!wrongOccured) {
					connection.commit();
					connection.endTransation();
					connection.close();
					return ids;

				} else {
					connection.endTransation();
					connection.close();
					return null;
				}
			}

		}

		else
			return null;
	}

	@Override
	public <T> long deleteAll(List<T> records) throws Exception {
		if (records != null && records.size() > 0) {
			checkEntity(records.get(0));
			DatabaseName databaseName = records.get(0).getClass().getAnnotation(DatabaseName.class);
			if (databaseName == null)
				return -1;
			TableName tableName = records.get(0).getClass().getAnnotation(TableName.class);
			if (tableName == null)
				return -1;
			boolean wrongOccured = false;
			synchronized (_connectionLock) {
				connection.open(databaseName.name());// 打开数据库连接
				connection.beginTransaction();
				long sums = 0;
				try {
					for (T record : records) {
						IndentifyID idField = null;
						String idfieldName = null;
						Long id = null;
						// 获取属性字段
						for (Field field : record.getClass().getDeclaredFields()) {
							idField = field.getAnnotation(IndentifyID.class);
							if (idField == null)
								continue;
							else {
								String fieldName = field.getName();// 属性名称
								// 查找获取器
								TableColumn methodName = field.getAnnotation(TableColumn.class);
								if (methodName == null)
									continue;
								//Method m = record.getClass().getMethod(methodName.getterName());
								//Object obj = m.invoke(record);
								field.setAccessible(true);
								Object obj = field.get(record);
								if (idField != null && obj != null) {
									idfieldName = fieldName;
									id = (Long) obj;
									break;
								}
							}
						}
						if (idField != null && id != null && idfieldName != null) {
							long nums = connection.delete(tableName.name(), idfieldName, id);
							if (nums != -1)
								sums += nums;
							else {
								wrongOccured = true;
								break;
							}
						}
					}
				} catch (Exception e) {
					connection.rollBack();
					connection.endTransation();
					connection.close();

					throw e;
				} finally {

				}
				if (!wrongOccured) {
					connection.commit();
					connection.endTransation();
					connection.close();
					return sums;
				} else {
					connection.endTransation();
					connection.close();
					return -1l;
				}
			}
		}
		return -1;
	}

	@Override
	public <T> List<T> query(T record, boolean distinct, String where, String[] args, String groupBY, String having,
			String orderBy, String limit) throws Exception {
		if (record == null)
			return null;
		try {
			checkEntity(record);
			DatabaseName databaseName = record.getClass().getAnnotation(DatabaseName.class);
			TableName tableName = record.getClass().getAnnotation(TableName.class);
			synchronized (_connectionLock) {
				connection.open(databaseName.name());
				String[] columns = new String[record.getClass().getDeclaredFields().length];
				int i = 0;
				// 获取属性字段
				for (Field field : record.getClass().getDeclaredFields()) {
					String fieldName = field.getName();// 属性名称
					// 查找获取器
					TableColumn methodName = field.getAnnotation(TableColumn.class);
					if (methodName == null)
						continue;
					//Method m = record.getClass().getMethod(methodName.getterName());
					//Object obj = m.invoke(record);
					columns[i] = fieldName;
					i++;
				}
				List<Map<String, Object>> result;
				result = connection.query(tableName.name(), distinct, columns, where, args, groupBY, having, orderBy,
						limit);
				// sqllite结果集转换成对象
				if (result != null && result.size() > 0) {
					ArrayList<T> resultList = new ArrayList<T>();
					for (i = 0; i < result.size(); i++) {
						// 属性注入
						@SuppressWarnings("unchecked")
						T t = (T) record.getClass().newInstance();
						for (Field field : t.getClass().getDeclaredFields()) {
							String fieldName = field.getName();// 属性名称
							Class<?> fieldType = field.getType();
							if (result.get(i).get(fieldName) == null) {
								continue;
							}
							TableColumn methodName = field.getAnnotation(TableColumn.class);
							if (methodName == null)
								continue;
							field.setAccessible(true);
							//field.set(t, result.get(i).get(fieldName));
//							// 查找设置器
//							Method m = t.getClass().getMethod(methodName.setterName(), field.getType());
//							// 根据属性类型获得属性信息
							if (fieldType.equals(Long.class))
								field.set(t, Long.parseLong((String) result.get(i).get(fieldName)));
							else if (fieldType.equals(String.class))
								field.set(t, (String) result.get(i).get(fieldName));
							else if (fieldType.equals(Short.class))
								field.set(t, Short.parseShort((String) result.get(i).get(fieldName)));
							else if (fieldType.equals(Integer.class))
								field.set(t, Integer.parseInt((String) result.get(i).get(fieldName)));
							else if (fieldType.equals(Boolean.class))
								field.set(t, Boolean.parseBoolean((String) result.get(i).get(fieldName)));
							else if (fieldType.equals(Date.class)) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								field.set(t, sdf.parse((String) result.get(i).get(fieldName)));
							} else if (fieldType.equals(Double.class))
								field.set(t, Double.parseDouble((String) result.get(i).get(fieldName)));
							else if (fieldType.equals(Float.class))
								field.set(t, Float.parseFloat((String) result.get(i).get(fieldName)));
							else if (fieldType.equals(Byte.class))
								field.set(t, Byte.parseByte((String) result.get(i).get(fieldName)));
						}
						resultList.add(t);
					}
					connection.close();
					return resultList;
				}
			}
		} catch (Exception e) {
			connection.close();

			throw (e);
		} finally {
		}

		return null;
	}

	@Override
	public <T> long update(T record) throws Exception {
		if (record == null)
			return -1l;
		else {
			checkEntity(record);
			DatabaseName databaseName = record.getClass().getAnnotation(DatabaseName.class);
			TableName tableName = record.getClass().getAnnotation(TableName.class);
			// 进行批量操作同时禁止再次打开数据库
			synchronized (_connectionLock) {
				long result = -1;
				connection.open(databaseName.name());// 打开数据库连接
				try {
					// 纪录id属性
					IndentifyID idField = null;
					String idfieldName = null;
					Long id = null;
					HashMap<String, Object> params = new HashMap<String, Object>();
					for (Field field : record.getClass().getDeclaredFields()) {
						String fieldName = field.getName();// 属性名称
						// 查找获取器
						TableColumn methodName = field.getAnnotation(TableColumn.class);
						if (null==methodName) continue;
						//Method m = record.getClass().getMethod(methodName.getterName());
						//Object obj = m.invoke(record);
						field.setAccessible(true);
						Object obj = field.get(record);
						if (obj != null)
							params.put(fieldName, obj);
						if (idField == null)
							idField = field.getAnnotation(IndentifyID.class);
						if (idField != null && idfieldName == null) {
							idfieldName = fieldName;
							id = (Long) obj;
						}

					}
					if (idField == null || id == null)
						return -1l;
					result = connection.update(tableName.name(), idfieldName, id, params);
				} catch (Exception e) {
					connection.close();
					throw (e);
				} finally {
				}
				connection.close();
				return result;
			}
		}
	}

	@Override
	public <T> long insert(T record) throws Exception {
		if (record != null) {
			checkEntity(record);
			DatabaseName databaseName = record.getClass().getAnnotation(DatabaseName.class);
			TableName tableName = record.getClass().getAnnotation(TableName.class);
			// 进行批量操作同时禁止再次打开数据库
			long id = -1;
			synchronized (_connectionLock) {

				try {
					/*
					 * 根据记录中的类属性获得属性的值 将这些值保存到数据库
					 */
					connection.open(databaseName.name());// 打开数据库连接
					HashMap<String, Object> hashMap;
					hashMap = new HashMap<String, Object>();
					for (Field field : record.getClass().getDeclaredFields()) {
						IndentifyID indentifyID = field.getAnnotation(IndentifyID.class);
						if (indentifyID != null)
							continue;
						String fieldName = field.getName();// 属性名称
						// String fieldType =
						// field.getGenericType().toString();//属性类型
						TableColumn methodName = field.getAnnotation(TableColumn.class);
						if (methodName != null) {
							// 查找获取器
							//Method m = record.getClass().getMethod(methodName.getterName());
							//hashMap.put(fieldName, m.invoke(record));
							field.setAccessible(true);
							hashMap.put(fieldName, field.get(record));
						}
					}
					id = connection.insert(tableName.name(), hashMap);
				} catch (Exception e) {
					connection.close();
					throw e;
				} finally {
				}

			}
			connection.close();
			return id;
		}
		return -1l;
	}

	@Override
	public <T> long delete(T record) throws Exception {
		if (record != null) {
			checkEntity(record);
			DatabaseName databaseName = record.getClass().getAnnotation(DatabaseName.class);
			TableName tableName = record.getClass().getAnnotation(TableName.class);
			synchronized (_connectionLock) {
				connection.open(databaseName.name());// 打开数据库连接
				long nums = -1;
				try {

					IndentifyID idField = null;
					String idfieldName = null;
					Long id = null;
					for (Field field : record.getClass().getDeclaredFields()) {
						idField = field.getAnnotation(IndentifyID.class);
						if (idField == null)
							continue;
						else {
							String fieldName = field.getName();// 属性名称
							// 查找获取器
							TableColumn methodName = field.getAnnotation(TableColumn.class);
							if (methodName == null)
								continue;
							//Method m = record.getClass().getMethod(methodName.getterName());
							//Object obj = m.invoke(record);
							field.setAccessible(true);
							Object obj = field.get(record);
							if (idField != null && obj != null) {
								idfieldName = fieldName;
								id = (Long) obj;
								break;
							}
						}
					}
					if (idField != null && id != null && idfieldName != null) {
						nums = connection.delete(tableName.name(), idfieldName, id);
						connection.close();
						return nums;
					} else {
						connection.close();
						return -1;
					}

				} catch (Exception e) {
					connection.close();
					throw e;
				} finally {
				}
			}
		}
		return -1l;
	}

	/** 检查数据表实例是否符合要求 */
	private <T> void checkEntity(T record) throws Exception {
		if (null == record) {
			Log.e("com.thtf.soft.andorid.mobile.common.database", "数据表为空");
			throw new Exception();
		}
		if (!(record instanceof BaseEntity)) {
			Log.e("com.thtf.soft.andorid.mobile.common.database", "不是正确的数据表");
			throw new Exception();
		}
		DatabaseName databaseName = record.getClass().getAnnotation(DatabaseName.class);

		if (null == databaseName) {
			Log.e("com.thtf.soft.andorid.mobile.common.database", "数据表的数据库名没有定义");
			throw new Exception();
		}
		TableName tableName = record.getClass().getAnnotation(TableName.class);
		if (null == tableName) {
			Log.e("com.thtf.soft.andorid.mobile.common.database", "数据表的数据库表名没有定义");
			throw new Exception();
		}
		synchronized (_connectionLock) {
			try {
				connection.open(databaseName.name());// 打开数据库连接
				BaseEntity entity = (BaseEntity) record;
				String sql = entity.createStructure();
				connection.execSQL(sql);
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
		}
	}
}
