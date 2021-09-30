package com.app.service;

import java.util.List;

import com.app.pojos.Products;
import com.app.pojos.User;


public interface Iproducts {
	
	List<Products> getAllProducts();
	Products addProduct(Products product);
	String deleteProduct(int productId);
	Products getDetails(int productId);
	Products updateProDetails(Products detachedProduct);


}
