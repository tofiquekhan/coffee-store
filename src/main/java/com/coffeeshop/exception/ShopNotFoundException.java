package com.coffeeshop.exception;

public class ShopNotFoundException extends RuntimeException {
	public ShopNotFoundException() {

	}

	public ShopNotFoundException(String msg) {
		super(msg);
	}

}
