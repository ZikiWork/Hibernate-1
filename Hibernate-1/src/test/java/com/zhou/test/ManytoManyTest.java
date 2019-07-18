package com.zhou.test;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zhou.entity.Role;
import com.zhou.entity.User;
import com.zhou.utils.HibernateUtils;

/**
 * 一般使用中间表维护，进行操作！
 * 
 * @author zyx
 *
 */
public class ManytoManyTest {
	@Test
	public void testAdd() {
		Session session = null;
		Transaction transaction = null;
		try {
			// 第三步：通过工具类获取session对象
			session = HibernateUtils.getCurrentSession();
			// 第四步：开启事务
			transaction = session.beginTransaction();
			// 第五步：写具体的crud操作

			User user = new User();
			user.setUser_name("Tom");
			user.setUser_password("123");

			Role role = new Role();
			role.setRole_name("经纪人");
			role.setRole_describe("经纪人");

			Role role2 = new Role();
			role2.setRole_name("董事长");
			role2.setRole_describe("董事长");
			// 让用户有以下上述两个角色
			user.getRoles().add(role);
			user.getRoles().add(role2);
			// 调用session方法实现增加
//			session.save(role);
//			session.save(role2);
			session.save(user);
			// 第六步：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	/**
	 * 让用户拥有某个角色！
	 */
	@Test
	public void testAdd1() {
		Session session = null;
		Transaction transaction = null;
		try {
			// 第三步：通过工具类获取session对象
			session = HibernateUtils.getCurrentSession();
			// 第四步：开启事务
			transaction = session.beginTransaction();
			// 第五步：写具体的crud操作
			Role role = session.get(Role.class, 4);
			// 让用户有以下上述两个角色
			User user = session.get(User.class, 9);
//			User user = new User();
//			user.setUser_name("Sony");
//			user.setUser_password("123");
			user.getRoles().add(role);
			// 调用session方法实现增加
			session.save(user);
			// 第六步：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	/**
	 * （不用此删除功能！！！） 存在问题：比如删除User 同时会把Role中的数据删掉
	 */
	@Test
	public void testPassDelete() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrentSession();
			transaction = session.beginTransaction();
			// 第五步：写具体的crud操作
			User user = session.get(User.class, 10);
			System.out.println(user);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	/**
	 * 删除用户，将User的set集合中的数据remove()即可！实现删除
	 */
	@Test
	public void testDelete() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrentSession();
			transaction = session.beginTransaction();
			// 第五步：写具体的crud操作
			User user = session.get(User.class, 10);
			user.getRoles().clear();// 去除用户所有权限！
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	
	
	/**
	 * hibernate API 查询
	 */
	@Test
	public void testSelect() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrentSession();
			transaction = session.beginTransaction();

			// Query 查询
//			Query query = session.createQuery("From User");
//			List<User> users = query.getResultList();

			// Criteria 查询
//			Criteria criteria = session.createCriteria(User.class);
//			List<User> users = criteria.list();

			// SQlQuery 查询
			SQLQuery sqlQuery = session.createSQLQuery("select * from  user");
			sqlQuery.addEntity(Class.forName("com.zhou.entity.User"));
			List<User> users = sqlQuery.getResultList();

			users.forEach(action -> System.out.println(action));
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

	}
}
