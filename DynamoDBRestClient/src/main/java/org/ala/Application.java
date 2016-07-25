package org.ala;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ala.domain.ShoppingCart;
import org.ala.repository.ShoppingCartRepository;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;


@SpringBootApplication
@EnableAutoConfiguration
@Path("/")
public class Application {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Path("/cart/additem")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response shoppingCartSave(@FormParam("items") String items,@FormParam("name") String name) {	
		ShoppingCart cart = shoppingCartRepository.findOne(name);
		if(cart == null){
			cart = new ShoppingCart();
			cart.setName(name);
		}

		List<String> itemList = cart.getItems() == null? new ArrayList<String>(): cart.getItems();
		itemList.addAll(Arrays.asList(items.split(",")));
		cart.setItems(itemList);		
		return Response.status(Response.Status.ACCEPTED).entity( shoppingCartRepository.save(cart)).build();
	}

	@Path("/cart/fetch")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response shoppingCartFetch(@QueryParam("name") String name) {	
		return Response.status(Response.Status.ACCEPTED).entity(shoppingCartRepository.findByName(name)).build();
	}

	@Path("/cart/delete")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response shoppingCartDelete(@QueryParam("name") String name) {
		try{
			shoppingCartRepository.delete(name);
		}catch(EmptyResultDataAccessException e){
			return Response.status(Response.Status.ACCEPTED).entity("No item found to delete").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity(true).build();
	}

	@Path("/cart/fetchall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response shoppingCartFetchAll() {
		return Response.status(Response.Status.ACCEPTED).entity(shoppingCartRepository.findAll()).build();
	}


	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registration;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
