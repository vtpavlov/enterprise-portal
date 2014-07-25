package com.vtpavlov.enterpriseportal.Repositories;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vtpavlov.enterpriseportal.dto.Page;

@ManagedBean
@NoneScoped
public class DbPageRepository implements IRepository<Page>{
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public static void createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public DbPageRepository() {
		createSessionFactory();
	}
	
	@Override
	public Page add(Page item) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return item;
	}

	@Override
	public Page update(int id, Page item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Page item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Page> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}