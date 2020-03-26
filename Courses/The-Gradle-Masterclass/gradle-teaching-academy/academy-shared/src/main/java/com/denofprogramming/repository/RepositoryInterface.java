package com.denofprogramming.repository;

public interface RepositoryInterface<T> {

	
	T read(final T domainObject);
	
	T update(final T domainObject);
	
}
