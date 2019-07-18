package com.zhou.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SessionFactory������
 * 
 * @author zyx
 *
 */
public class HibernateUtils {
	// �ڴ���SessionFactory��������и���ӳ���ϵ���������ݿ����潫����»��ߴ��������ж������ʱ���ǳ�������Դ��
	// Ϊ����������⣺һ����һ����Ŀ��ֻ����һ��SessionFactory���󣡣�������̬������д������ɣ�
	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().buildSessionFactory();
	}

	/**
	 * ��ȡĬ��session�ķ���
	 * 
	 * @return
	 */
	public static Session getSession() {
		return sf.openSession();
	}

	/**
	 * ��ȡ�뱾���̰߳󶨵�session����
	 * 
	 * @return
	 */
	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}

	public static void main(String[] args) {

	}
}
