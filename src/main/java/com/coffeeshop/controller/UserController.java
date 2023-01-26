package com.coffeeshop.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.dto.UserDTO;
import com.coffeeshop.reponse.ResponseHandler;
import com.coffeeshop.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<Object> saveUpdateUser(@RequestBody UserDTO userDTO){
		return  ResponseHandler.generateResponse("success", HttpStatus.OK, userService.saveUpdateUser(userDTO));
	}
	
	@GetMapping()
	public ResponseEntity<Object> getUsers(){
	
		return ResponseHandler.generateResponse("success", HttpStatus.OK,userService.getUsers());
		
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id){
		return ResponseHandler.generateResponse("success", HttpStatus.OK, userService.getUserDetailsById(id));
		
	}
	
	@PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO){
		 UserDTO updateUser = userService.saveUpdateUser(userDTO);
		return ResponseHandler.generateResponse("success", HttpStatus.OK,updateUser.getId());
		
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<Object> deleteUser(@PathVariable Long id){
			   userService.getUserDetailsById(id);
			   return ResponseHandler.generateResponse("User Deleted Successfully", HttpStatus.OK, null);
			
		
	}

}
