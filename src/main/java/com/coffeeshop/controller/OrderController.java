package com.coffeeshop.controller;

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

import com.coffeeshop.dto.OrderDTO;

import com.coffeeshop.reponse.ResponseHandler;
import com.coffeeshop.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping
	public ResponseEntity<Object> saveUpdateOrder(@RequestBody OrderDTO orderDTO){
		return  ResponseHandler.generateResponse("success", HttpStatus.OK, orderService.saveUpdateOrder(orderDTO));
	}
	
	@GetMapping()
	public ResponseEntity<Object> getOrders(){
	
		return ResponseHandler.generateResponse("success", HttpStatus.OK,orderService.getOrders());
		
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id){
		return ResponseHandler.generateResponse("success", HttpStatus.OK, orderService.getOrderDetailsById(id));
		
	}
	
	@PutMapping()
    public ResponseEntity<Object> updateOrder(@RequestBody OrderDTO orderDTO){
		 OrderDTO updateOrder = orderService.saveUpdateOrder(orderDTO);
		return ResponseHandler.generateResponse("success", HttpStatus.OK,updateOrder.getId());
		
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<Object> deleteOrder(@PathVariable Long id){
			   orderService.getOrderDetailsById(id);
			   return ResponseHandler.generateResponse("Order Deleted Successfully", HttpStatus.OK, null);
			
		
	}

}
