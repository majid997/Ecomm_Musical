package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserRestController {
	//dependency : service layer i/f
	@Autowired
	private IUserService userService;
	public UserRestController() {
		System.out.println("in ctor " + getClass().getName());
	}
	//add REST API endpoint : for getting all users 
	@GetMapping
	public List<User> fetchAllUsers()
	{
		System.out.println("in fetch all users");
		return userService.getAllUsers();
	}
//@PostMapping
//public ResponseEntity<User> addNewUserDetails(@RequestBody User transientUser)
//{
//	System.out.println("in add user"+transientUser);
//	
//	//return userService.addUser(transientUser)
//	return ResponseEntity.ok(userService.addUser(transientUser));
//}
	@PostMapping
	public ResponseEntity<?> addNewUserDetails(@RequestBody User transientUser) {
		System.out.println("in add user " + transientUser);
		// invoke service layer's method for saving user details
		try {
			return new ResponseEntity<>(userService.addUser(transientUser), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add " + e);
			return new ResponseEntity<>(new ErrorResponse("Adding User failed!!!!!", e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
