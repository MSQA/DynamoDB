package org.ala.domain;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "ShoppingCart")
public class ShoppingCart {
	
	private String name;
	private List<String> items = null;


	@DynamoDBHashKey(attributeName = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getItems() {
		return items;
	}

	@DynamoDBAttribute(attributeName = "items")
	public void setItems(List<String> items) {
		this.items = items;
	}
}
