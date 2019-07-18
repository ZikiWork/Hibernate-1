package com.zhou.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SessionFactory工具类
 * 
 * @author zyx
 *
 */
public class HibernateUtils {
	// 在创建SessionFactory这个过程中根据映射关系在配置数据库里面将表更新或者创建【当有多个操作时，非常消耗资源】
	// 为解决上述问题：一般在一个项目中只创建一个SessionFactory对象！！！（静态代码块中创建即可）
	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().buildSessionFactory();
	}

	/**
	 * 获取默认session的方法
	 * 
	 * @return
	 */
	public static Session getSession() {
		return sf.openSession();
	}

	/**
	 * 获取与本地线程绑定的session方法
	 * 
	 * @return
	 */
	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}

	public static void main(String[] args) {

	}
}
