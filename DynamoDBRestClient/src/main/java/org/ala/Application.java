package org.ala;
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

import org.ala.config.JerseyConfig;
import org.ala.managers.IShoppingManager;
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
	IShoppingManager shoppingCartManager;

	@Path("/cart/additem")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response shoppingCartSave(@FormParam(Parameters.ITEMS) String items,@FormParam(Parameters.NAME) String name) {		
		return Response.status(Response.Status.ACCEPTED).entity(shoppingCartManager.saveCart(items, name)).build();
	}

	@Path("/cart/fetch")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response shoppingCartFetch(@QueryParam(Parameters.NAME) String name) {	
		return Response.status(Response.Status.ACCEPTED).entity(shoppingCartManager.getCart(name)).build();
	}

	@Path("/cart/delete")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response shoppingCartDelete(@QueryParam(Parameters.NAME) String name) {
		try{
			return Response.status(Response.Status.ACCEPTED).entity(shoppingCartManager.deleteCart(name)).build();
		}catch(EmptyResultDataAccessException e){
			return Response.status(Response.Status.ACCEPTED).entity(false).build();
		}	
	}

	@Path("/cart/fetchall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response shoppingCartFetchAll() {
		return Response.status(Response.Status.ACCEPTED).entity(shoppingCartManager.getAllCarts()).build();
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