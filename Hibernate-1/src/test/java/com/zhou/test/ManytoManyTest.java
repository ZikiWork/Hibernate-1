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
 * һ��ʹ���м��ά�������в�����
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
			// ��������ͨ���������ȡsession����
			session = HibernateUtils.getCurrentSession();
			// ���Ĳ�����������
			transaction = session.beginTransaction();
			// ���岽��д�����crud����

			User user = new User();
			user.setUser_name("Tom");
			user.setUser_password("123");

			Role role = new Role();
			role.setRole_name("������");
			role.setRole_describe("������");

			Role role2 = new Role();
			role2.setRole_name("���³�");
			role2.setRole_describe("���³�");
			// ���û�����������������ɫ
			user.getRoles().add(role);
			user.getRoles().add(role2);
			// ����session����ʵ������
//			session.save(role);
//			session.save(role2);
			session.save(user);
			// ���������ύ����
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// �ع�����
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	/**
	 * ���û�ӵ��ĳ����ɫ��
	 */
	@Test
	public void testAdd1() {
		Session session = null;
		Transaction transaction = null;
		try {
			// ��������ͨ���������ȡsession����
			session = HibernateUtils.getCurrentSession();
			// ���Ĳ�����������
			transaction = session.beginTransaction();
			// ���岽��д�����crud����
			Role role = session.get(Role.class, 4);
			// ���û�����������������ɫ
			User user = session.get(User.class, 9);
//			User user = new User();
//			user.setUser_name("Sony");
//			user.setUser_password("123");
			user.getRoles().add(role);
			// ����session����ʵ������
			session.save(user);
			// ���������ύ����
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// �ع�����
			transaction.rollback();
		} finally {
			session.close();
		}

	}

	/**
	 * �����ô�ɾ�����ܣ������� �������⣺����ɾ��User ͬʱ���Role�е�����ɾ��
	 */
	@Test
	public void testPassDelete() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrentSession();
			transaction = session.beginTransaction();
			// ���岽��д�����crud����
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
	 * ɾ���û�����User��set�����е�����remove()���ɣ�ʵ��ɾ��
	 */
	@Test
	public void testDelete() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrentSession();
			transaction = session.beginTransaction();
			// ���岽��д�����crud����
			User user = session.get(User.class, 10);
			user.getRoles().clear();// ȥ���û�����Ȩ�ޣ�
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
	 * hibernate API ��ѯ
	 */
	@Test
	public void testSelect() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrentSession();
			transaction = session.beginTransaction();

			// Query ��ѯ
//			Query query = session.createQuery("From User");
//			List<User> users = query.getResultList();

			// Criteria ��ѯ
//			Criteria criteria = session.createCriteria(User.class);
//			List<User> users = criteria.list();

			// SQlQuery ��ѯ
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
