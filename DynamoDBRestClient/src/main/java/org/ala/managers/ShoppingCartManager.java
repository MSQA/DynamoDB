package org.ala.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ala.domain.ShoppingCart;
import org.ala.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartManager implements IShoppingManager{
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;


	@Override
	public ShoppingCart saveCart(String items, String name) {
		ShoppingCart cart = getCart(name);
		if(cart == null){
			cart = new ShoppingCart();
			cart.setName(name);
		}

		List<String> itemList = cart.getItems() == null? new ArrayList<String>(): cart.getItems();
		itemList.addAll(Arrays.asList(items.split(",")));
		cart.setItems(itemList);		
		return shoppingCartRepository.save(cart);
	}

	@Override
	public ShoppingCart getCart(String name) {
		return shoppingCartRepository.findOne(name);
	}

	@Override
	public List<ShoppingCart> getAllCarts() {
		return (List<ShoppingCart>) shoppingCartRepository.findAll();
	}

	@Override
	public boolean deleteCart(String name) {
		shoppingCartRepository.delete(name);
		return true;
	}

}
