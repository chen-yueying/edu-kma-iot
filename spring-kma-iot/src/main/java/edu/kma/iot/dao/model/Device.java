package edu.kma.iot.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "DEVICE", uniqueConstraints = {@UniqueConstraint(columnNames = "mac_address")})
@Inheritance(strategy = InheritanceType.JOINED)
@JsonPropertyOrder({ "mac_address", "name",  "owner", "location"})
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mac_address", unique = true, nullable = true)
	@Size(min=1,max=50)
	private String mac_address;
	
	@Column(name = "name")
	@Size(max=20)
	private String name;
	
	@Column(name="owner", nullable = true, insertable = true, updatable = true)
	private String owner;
	
	@Column(name="location")
	@Size(min=1,max=35)
	private String location;
	
	@NotBlank
	@Column(name="type_code")
	private String type_code;
	
	@ManyToOne
	@JoinColumn(name = "type_code", insertable = false, updatable = false)
	private ClassifyDevice classify;
	
	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	@JsonBackReference
	public String getType_code() {
		return type_code;
	}

	public ClassifyDevice getClassify() {
		return classify;
	}

	public void setClassify(ClassifyDevice classify) {
		this.classify = classify;
	}

}
