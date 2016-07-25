package org.ala.repository;

import java.util.List;

import org.ala.domain.ShoppingCart;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@EnableScan
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
	public List<ShoppingCart> findByName(@Param("name") String name);
}