package com.mx.PruebaTecD1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.PruebaTecD1.model.User;
import com.mx.PruebaTecD1.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserWS {

	private final UserService userService;
	
	public UserWS(UserService userService) {
		this.userService= userService;
	}
	
	//http://localhost:8080/users
	@GetMapping
	public List<User> getUsers(
			@RequestParam(value = "sortedBy", required = false) String sortedBy,
			@RequestParam(value = "filter", required = false) String filter
	){

		if(filter != null){
	        return userService.filterUsers(filter);
	    }
		
	    return userService.getUsersSorted(sortedBy);
	}
	
	//http://localhost:8080/users
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//http://localhost:8080/users/id}
	@PatchMapping(path="/{id}")
	public User updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
		return userService.updatedUser(id, user);
	}
	
	//http://localhost:8080/users/id}
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") UUID id){
	    return userService.deleteUser(id);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User loginRequest){
	    return userService.login(loginRequest.getTaxId(), loginRequest.getPassword());
	}
}
