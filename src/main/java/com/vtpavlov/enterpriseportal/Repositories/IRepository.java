package com.vtpavlov.enterpriseportal.Repositories;

import java.util.List;

public interface IRepository<T> {
	T add(T item);
	T update(String id, T item);
	void delete(String id);
	void delete(T item);
	T get(String id);
	List<T> getAll();
}
