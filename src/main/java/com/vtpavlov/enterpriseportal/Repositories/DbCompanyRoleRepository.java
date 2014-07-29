package com.vtpavlov.enterpriseportal.Repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vtpavlov.enterpriseportal.dto.CompanyRole;

public class DbCompanyRoleRepository implements IRepository<CompanyRole>{

	public DbCompanyRoleRepository() {
	}
	
	@Override
	public CompanyRole add(CompanyRole item) {
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
	public CompanyRole update(String id, CompanyRole item) {
		Session session = null;
		Transaction tx = null;
		CompanyRole role = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			role = (CompanyRole) session.get(CompanyRole.class, id);
			role.setCompanyRole(item.getCompanyRole());
			session.update(role);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return role;
	}

	@Override
	public void delete(String id) {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CompanyRole role = (CompanyRole) session.get(CompanyRole.class, id);
			session.delete(role);
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
	public void delete(CompanyRole item) {
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
	public CompanyRole get(String id) {
		Session session = null;
		Transaction tx = null;
		CompanyRole role = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			role = (CompanyRole) session.get(CompanyRole.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return role;
	}

	@Override
	public List<CompanyRole> getAll() {
		Session session = null;
		Transaction tx = null;
		List<CompanyRole> roles = null;
		try {
			session = SessionFactoryInstance.getSessionFactory().openSession();
			tx = session.beginTransaction();
			roles = castList(CompanyRole.class, session.createQuery("FROM CompanyRole")
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

		return roles;
	}
	
	private static <T> List<T> castList(Class<? extends T> clazz,
			Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
}
