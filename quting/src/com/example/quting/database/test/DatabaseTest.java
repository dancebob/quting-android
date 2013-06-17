package com.example.quting.database.test;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;

import com.example.quting.database.DatabaseBusiness;
import com.example.quting.database.model.UserName;
/**
 * 数据库组件测试用例
 * @author eoeye
 *
 */
public class DatabaseTest extends AndroidTestCase{
	private DatabaseBusiness databse ;
	@Override
	public void setUp(){
		databse = DatabaseBusiness.instance();
	}
	
	
	/**
	 * 插入一条纪录
	 */
	public void testInsertOne(){
		
		UserName userName = new UserName();
//		userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
//		userName.setId( 4l );
		userName.setIsNow(true);
		userName.setUserPassword("pass");
		userName.setUserName("user1");
		try {
			long id = databse.insert( userName );
			assertTrue( id > 0 );
			System.out.println( "id:" + id );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 删除一条纪录
	 */
	public void testDeleteOne(){
		UserName userName = new UserName();
		userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
		userName.setId( 4l );
		userName.setIsNow(true);
		userName.setUserPassword("pass");
		userName.setUserName("user1");
		try {
			long id = databse.delete( userName ) ;
			assertTrue( id > 0 );
			System.out.println( "id:" + id );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 更新一条纪录
	 */
	public void testUpdateOne(){
		UserName userName = new UserName();
		userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
		userName.setId( 12l );
		userName.setIsNow(false);
		userName.setUserPassword("changed");
		userName.setUserName("changed");
		try {
			long id = databse.update( userName );
			assertTrue( id > 0);
			System.out.println( "id:" + id );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询
	 */
	public void testQuery(){
		UserName userName = new UserName();
		userName.setId(null);
		try {
			List< UserName > resultList = databse.query(userName, true , null , null , null, null, "id desc", "1");
			assertNotNull( resultList );
			assertTrue( resultList.size() > 0 );
			System.out.println( resultList );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入所有纪录
	 */
	public void testInsertAll(){
		try {
			List<UserName> list = new ArrayList<UserName>();
			UserName userName = new UserName();
			userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
			userName.setIsNow(true);
			userName.setUserPassword("passabc1");
			userName.setUserName("userabc1");
			list.add(userName);
			userName = new UserName();
			userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
			userName.setIsNow(true);
			userName.setUserPassword("passabc2");
			userName.setUserName("userabc2");
			list.add(userName);
			userName = new UserName();
			userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
			userName.setIsNow(true);
			userName.setUserPassword("passabc3");
			userName.setUserName("userabc3");
			list.add(userName);
			userName = new UserName();
			userName.setId( ( Long.MAX_VALUE + Long.MIN_VALUE  ) / 2 );
			userName.setIsNow(true);
			userName.setUserPassword("passabc4");
			userName.setUserName("userabc4");
			list.add(userName);
			long[] ids = databse.insertAll(list);
			assertTrue( ids != null && ids.length > 0 );
			System.out.println( "id:" + ids );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除所有纪录
	 */
	public void testDeleteAll(){
		List<UserName> list = new ArrayList<UserName>();
		UserName userName = new UserName();
		userName.setId(5l);
		list.add(userName);
		userName = new UserName();
		userName.setId(6l);
		list.add(userName);
		userName.setId(7l);
		list.add(userName);
		userName = new UserName();
		userName.setId(8l);
		list.add(userName);
		long num;
		try {
			num = databse.deleteAll(list);
			assertTrue( num > 0);
			System.out.println( "id:" + num );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 更新所有纪录
	 */
	public void testUpdateAll(){
		List<UserName> list = new ArrayList<UserName>();
		UserName userName = new UserName();
		userName.setId(8l);
		userName.setUserName("tt1");
		list.add(userName);
		userName = new UserName();
		userName.setId(9l);
		userName.setUserName("tt2");
		list.add(userName);
		userName = new UserName();
		userName.setId(10l);
		userName.setUserName("tt3");
		list.add(userName);
		userName = new UserName();
		userName.setId(11l);
		userName.setUserName("tt4");
		list.add(userName);
		long num;
		try {
			num = databse.updateAll(list);
			assertTrue( num > 0);
			System.out.println( "id:" + num );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
