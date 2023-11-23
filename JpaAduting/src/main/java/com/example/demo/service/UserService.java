package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user)
	{
		return repository.save(user);
	}
	
	public List<User> getAll()
	{
		return repository.findAll();
	}
	
	public User updateUser(User user,long id) throws ResourceNotFound
	{
		User user2=repository.findById(id).orElseThrow(()->new ResourceNotFound("User Doesn't Exsit In Database"));
		user2.setFirstname(user.getFirstname());
		user2.setLastname(user.getLastname());
		user2.setEmail(user.getEmail());
		
		repository.save(user2);
		return user2;
	}
	
	public void deleteUser(long id) throws ResourceNotFound
	{
		repository.findById(id).orElseThrow(()->new ResourceNotFound("User Doesn't Exsit In Database"));
		repository.deleteById(id);
	}
}
