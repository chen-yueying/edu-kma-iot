package edu.kma.iot.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Entity
@Table(name = "CLASSIFY_DEVICE", uniqueConstraints = {@UniqueConstraint(columnNames="type_code")})
@JsonPropertyOrder({ "type_code", "type_name"})
public class ClassifyDevice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "type_code")
	private String type_code;
	
	@Column(name="type_name")
	private String type_name;
	
	@OneToMany(mappedBy = "classify")
	 @JsonBackReference
	private List<Device> devices;
	
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
}
