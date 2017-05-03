package org.bluedon.lightweb.frame.service;

import java.io.Serializable;


public interface IBaseService<T> {

	public Serializable save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public void saveOrUpdate(T t);
}
