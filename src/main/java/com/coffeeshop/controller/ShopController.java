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

import com.coffeeshop.dto.ShopDTO;
import com.coffeeshop.reponse.ResponseHandler;
import com.coffeeshop.service.ShopService;


@RestController	
@RequestMapping("/shops")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	
	@PostMapping
	public ResponseEntity<Object> saveUpdateShop(@RequestBody ShopDTO shopDTO){
		return  ResponseHandler.generateResponse("success", HttpStatus.OK, shopService.saveUpdateShop(shopDTO));
	}
	
	@GetMapping()
	public ResponseEntity<Object> getShops(){
	
		return ResponseHandler.generateResponse("success", HttpStatus.OK,shopService.getShops());
		
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getShop(@PathVariable Long id){
		return ResponseHandler.generateResponse("success", HttpStatus.OK, shopService.getShopDetailsById(id));
		
	}
	
	@PutMapping()
    public ResponseEntity<Object> updateShop(@RequestBody ShopDTO shopDTO){
		 ShopDTO updateShop = shopService.saveUpdateShop(shopDTO);
		return ResponseHandler.generateResponse("success", HttpStatus.OK,updateShop.getId());
		
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<Object> deleteShop(@PathVariable Long id){
			   shopService.getShopDetailsById(id);
			   return ResponseHandler.generateResponse("Shop Deleted Successfully", HttpStatus.OK, null);
			
		
	}
	

}
