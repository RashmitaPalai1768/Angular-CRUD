package com.txt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.txt.model.User;
import com.txt.repo.userRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin("*")
//@RequestMapping("/api/vv")
public class userController {

	@Autowired
	userRepo repo;
	//Add user
	@PostMapping("/user")
	private ResponseEntity<User> getData(@RequestBody User user) {
		System.out.println("getData.......");
		return ResponseEntity.ok(repo.save(user));
	}
	//view user
	@GetMapping("/view")
	private List<User> getAllData(@ModelAttribute User user) {
		System.out.println("getAllData.......");
		
		return repo.findAll();
	}
	
	//update User by Id
	@GetMapping("/view/{id}")
	public ResponseEntity<User> getUserById(User user,@PathVariable int id){
		//ConfigDataResource resource = null;
		User uu = repo.findById(id)
				.orElseThrow(()-> new ResourceAccessException("user Not Exist with Id :" + id));
		return ResponseEntity.ok(uu);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> UpdateUser(@RequestBody User user,@PathVariable int id){
		User uu = repo.findById(id)
				.orElseThrow(()-> new ResourceAccessException("user Not Exist with Id :" + id));
		uu.setName(user.getName());
		uu.setEmail(user.getEmail());
		uu.setMobile(user.getMobile());
		uu.setPassword(user.getPassword());
		User userUpdate=repo.save(uu);
		return ResponseEntity.ok(userUpdate);
	}
	
	//delete user
	@GetMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable int id,Model m) {
		repo.deleteById(id);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
