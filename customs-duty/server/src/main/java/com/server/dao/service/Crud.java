package com.server.dao.service;

import java.util.List;

public interface Crud<T> {

	List<T> selectAll();

	boolean insert(T t);

	boolean update(T t);

	boolean delete(T t);

}
