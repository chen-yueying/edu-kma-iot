package edu.kma.iot.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User  {
	@Id
	@Pattern(regexp="^[0-9]{10,11}$")
	@Column(name = "username", unique = true, nullable = true)
	private String username;
	
	@Size(max = 35)
	@NotBlank
	@Column(name = "fullname")
	private String fullname;
	
	@Pattern(regexp="[^\\s].{6,16}$")
	@Column(name= "password", nullable = true)
	private String password;
	
	
	@Column(name = "address")
	private String address;
	
	@Email(regexp= "\\S+")
	@Column(name= "email")
	private String email;
	
	@Column(name = "date_create")
	private String create_date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
}
