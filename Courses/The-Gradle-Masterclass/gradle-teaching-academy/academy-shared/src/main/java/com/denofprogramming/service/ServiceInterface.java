package com.denofprogramming.service;

import com.denofprogramming.model.DomainObject;

public interface ServiceInterface<T extends DomainObject> {

	
	T read(final T domainObject);
	
	T update(final T domainObject);
	
	
}
