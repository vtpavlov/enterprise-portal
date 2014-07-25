package com.vtpavlov.enterpriseportal.Repositories;

public interface IRepository<T> {
	T add(T item);
	T update(int id, T item);
	void delete(int id);
	void delete(T item);
	T get(int id);
	Iterable<T> getAll();
}
