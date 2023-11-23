package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/api/user")
	public ResponseEntity<User> saveUser(@RequestBody User user)
	{
		return new ResponseEntity<User>(service.saveUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/allusers")
	public List<User> getAll()
	{
		return service.getAll();
	}
	
	@PutMapping("/api/{id}")
	public ResponseEntity<User> updateuser(@RequestBody User user,@PathVariable long id) throws ResourceNotFound
	{
		return new ResponseEntity<User>(service.updateUser(user, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) throws ResourceNotFound
	{
		service.deleteUser(id);
		return new ResponseEntity<String>("User Doesn't Exist in Database",HttpStatus.OK);
	}
}
