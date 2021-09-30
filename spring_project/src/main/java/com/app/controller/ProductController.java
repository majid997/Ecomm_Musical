package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Products;

import com.app.service.Iproducts;

@RestController
@RequestMapping("/Products")
@CrossOrigin
public class ProductController {
	// dependency : service layer i/f
	@Autowired
	private Iproducts Iproservice;

	public ProductController() {
		System.out.println("in ctor " + getClass().getName());
	}

	// add REST API endpoint : for getting all users
	@GetMapping
	public List<Products> fetchAllProducts() {
		System.out.println("in fetch all users");
		return Iproservice.getAllProducts();
	}

//	//add REST API endpoint for adding new user (create new resource)
//	@PostMapping
//	public User addNewUserDetails(@RequestBody User transientUser)
//	{
//		System.out.println("in add user "+transientUser);
//		//invoke service layer's method for saving user details
//		return userService.addUser(transientUser);
//	}
	// add REST API endpoint for adding new user (create new resource) that rets to
	// the front HTTP status code + resp body
	// API : o.s.http.ResponseEntity
	/*
	 * public ResponseEntity(@Nullable T body, HttpStatus status) Create a new
	 * ResponseEntity with the given body and status code, and no headers.
	 */
	//
	@PostMapping
	public ResponseEntity<?> addNewProDetails(@RequestBody Products transientUser) {
		System.out.println("in add user " + transientUser);
		// invoke service layer's method for saving user details
		try {
			return new ResponseEntity<>(Iproservice.addProduct(transientUser), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add " + e);
			return new ResponseEntity<>(new ErrorResponse("Adding User failed!!!!!", e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// add REST API to delete user details by id
	@DeleteMapping("/{userId}") // URI template var
	public ResponseEntity<ResponseDTO> deleteProDetails(@PathVariable int proId) {
		System.out.println("in delete user details " + proId);
		// invoke service layer method for deleting user details
		// return new ResponseEntity<>(new ResponseDTO(userService.deleteUser(userId)),
		// HttpStatus.OK);
		return ResponseEntity.ok(new ResponseDTO(Iproservice.deleteProduct(proId)));
	}

	// add REST API to get user details by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getProDetails(@PathVariable int id) {
		System.out.println("in get user details " + id);
		// invoke service method to get user details
		try {
			return ResponseEntity.ok(Iproservice.getDetails(id));
		} catch (RuntimeException e) {
			System.out.println("err in get " + e);
			return new ResponseEntity<>(new ErrorResponse("Fetching User details failed", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	// add REST API to update existing user details
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProDetails(@RequestBody Products detachedUser, @PathVariable int id) {
		System.out.println("in update user " + detachedUser + " " + id);
		try {
			// invoke service layer method for validating user id
			Products existingUser = Iproservice.getDetails(id);
			// => user is valid
			// existingUser => user details fetched from DB (stale)
			// detachedUser => updated user details from front end.
			return ResponseEntity.ok(Iproservice.updateProDetails(detachedUser));
		} catch (RuntimeException e) {
			System.out.println("err in get " + e);
			return new ResponseEntity<>(new ErrorResponse("Updating User details failed", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
}

