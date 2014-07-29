package com.vtpavlov.enterpriseportal.Repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vtpavlov.enterpriseportal.dto.Page;

public class DbPageRepository implements IRepository<Page> {
	
	public DbPageRepository() {
		
	}

	@Override
	public Page add(Page item) {
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
	public Page update(String id, Page item) {
		Session session = null;
		Transaction tx = null;
		Page page = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			page = (Page) session.get(Page.class, id);
			page.setTitle(item.getTitle());
			page.setAllowedRoles(item.getAllowedRoles());
			page.setDescription(item.getDescription());
			page.setFilePath(item.getFilePath());
			session.update(page);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return page;
	}

	@Override
	public void delete(String id) {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Page page = (Page) session.get(Page.class, id);
			session.delete(page);
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
	public void delete(Page item) {
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
	public Page get(String id) {
		Session session = null;
		Transaction tx = null;
		Page page = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			page = (Page) session.get(Page.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return page;
	}

	@Override
	public List<Page> getAll() {
		Session session = null;
		Transaction tx = null;
		List<Page> pages = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			pages = castList(Page.class, session.createQuery("FROM Page")
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

		return pages;
	}

	private static <T> List<T> castList(Class<? extends T> clazz,
			Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
}
