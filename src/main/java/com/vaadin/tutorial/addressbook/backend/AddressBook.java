package com.vaadin.tutorial.addressbook.backend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


	 
	public class AddressBook implements Serializable {
		private static final long serialVersionUID = 1L;
	 
		private Long id;
		private String title;
		private BigDecimal price;
		private Calendar published;
		  private String firstName = "";
		    private String lastName = "";
		    private String phone = "";
		    private String email = "";
		    private Date birthDate;
		    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public Calendar getPublished() {
			return published;
		}
		public void setPublished(Calendar published) {
			this.published = published;
		}
		public void setId(long l) {
			// TODO Auto-generated method stub
			
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Date getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
	 
		// getter+setter..
	}




 