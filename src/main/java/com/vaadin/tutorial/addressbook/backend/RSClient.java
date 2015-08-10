package com.vaadin.tutorial.addressbook.backend;

import javax.jws.WebResult;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class RSClient {

	//static Client client = ClientBuilder.newClient();
	static Client client = ClientBuilder.newClient().register(AddressBook.class);
	//Set the appropriate URL for POST request
	static String postUrl = "http://localhost:8080/JAXRS-JSON/rest/student/data/post";

	public static void postRequest(){
		
		AddressBook addressBook = new AddressBook();
		Response response = client
		.target(postUrl)
		.request().get();
		
		System.out.print(response.getStatusInfo());
	}
	
	public static void get(){
		
		AddressBook addressBook = new AddressBook();
		Response response = client
		.target(postUrl)
		.request().get();
		
		System.out.print(response.getStatusInfo());
	}
	
	public static void add(){
		
		AddressBook addressBook = new AddressBook();
		Response response = client
		.target(postUrl)
		.request().get();
		
		System.out.print(response.getStatusInfo());
	}
	
	public static void remove(){
		
		AddressBook addressBook = new AddressBook();
		Response response = client
		.target(postUrl)
		.request().get();
		
		System.out.print(response.getStatusInfo());
	}
	
	public static void update(){
		
		AddressBook addressBook = new AddressBook();
		Response response = client
		.target(postUrl)
		.request().get();
		
		System.out.print(response.getStatusInfo());
	}
}