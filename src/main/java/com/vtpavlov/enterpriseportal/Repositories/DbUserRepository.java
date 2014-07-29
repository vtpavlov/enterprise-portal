package com.vtpavlov.enterpriseportal.Repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vtpavlov.enterpriseportal.dto.User;

public class DbUserRepository implements IRepository<User> {

	public DbUserRepository() {
	}

	@Override
	public User add(User item) {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
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
	public User update(String id, User item) {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			user.setEmail(item.getEmail());
			user.setPassword(item.getPassword());
			user.setRoles(item.getRoles());
			user.setUsername(item.getUsername());
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public void delete(String id) {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(User item) {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public User get(String id) {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public List<User> getAll() {
		Session session = null;
		Transaction tx = null;
		List<User> users = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			users = castList(User.class, session.createQuery("FROM User")
					.list());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;
	}

	private static <T> List<T> castList(Class<? extends T> clazz,
			Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
}
