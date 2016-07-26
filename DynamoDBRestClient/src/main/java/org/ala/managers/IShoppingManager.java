package org.ala.managers;

import java.util.List;

import org.ala.domain.ShoppingCart;

public interface IShoppingManager {

	public ShoppingCart saveCart(String items,String name);
	public ShoppingCart getCart(String name);
	public  List<ShoppingCart> getAllCarts();
	public  boolean deleteCart(String name);
}
