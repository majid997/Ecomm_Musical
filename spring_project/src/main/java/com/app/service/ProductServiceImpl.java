package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.app.dao.ProductRepository;

import com.app.custom_exception.ProductHandlingException;
import com.app.pojos.Products;


@Service
@Transactional
public class ProductServiceImpl implements Iproducts {
	// dependency : dao layer i/f
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Products>getAllProducts () {
		// invoke dao's method
		return productRepo.findAll();
	}

	@Override
	public Products addProduct(Products product) {

		return productRepo.save(product);
	}

	@Override
	public String deleteProduct(int productId) {
		productRepo.deleteById(productId);
		return "productdetails deleted for ID=" + productId;
	}

	@Override
	public Products getDetails(int userId) {
		// Method of CrudRepository :
		/*
		 * findById Optional<T> findById(ID id) Rets Optional with entity in case of id
		 * found or rets empty Optional
		 */
		return productRepo.findById(userId).
				orElseThrow(() -> new ProductHandlingException("Invalid User ID !!!!!"));
		
	}

	
	@Override
	public Products updateProDetails(Products detachedProduct) {
		return productRepo.save(detachedProduct);
	}

	
}