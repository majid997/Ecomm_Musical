package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Products;
import com.app.pojos.User;

public interface ProductRepository extends JpaRepository<Products, Integer> {
//Use : inherited method : List<User> findAll()
	//Use inherited method : <S extends T> S save(S entity)
}