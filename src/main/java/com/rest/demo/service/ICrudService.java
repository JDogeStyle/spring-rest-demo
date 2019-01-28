package com.rest.demo.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T,K> {
	
	public List<T> findAll();
	public T save(T entity);
	public void deleteById(K id);
	public Optional<T> findByID(K id);
	public boolean exists(K id);
	
}
