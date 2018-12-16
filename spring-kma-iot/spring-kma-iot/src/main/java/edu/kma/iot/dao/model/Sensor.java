package edu.kma.iot.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="SENSOR")
public class Sensor extends Device {
	@Column(name = "status_time")
	private String status_time;
	
	public String getStatus_time() {
		return status_time;
	}
	public void setStatus_time(String status_time) {
		this.status_time = status_time;
	}
	
}
