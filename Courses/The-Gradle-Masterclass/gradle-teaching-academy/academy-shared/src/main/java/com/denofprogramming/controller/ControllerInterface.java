package com.denofprogramming.controller;

import com.denofprogramming.model.DomainObject;

public interface ControllerInterface<T extends DomainObject> {
	
	T read(final T DomainObject);
	
	T update(final T DomainObject);
	
}
