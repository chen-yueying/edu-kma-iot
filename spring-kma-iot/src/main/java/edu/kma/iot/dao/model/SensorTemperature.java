package edu.kma.iot.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SENSOR_TEMPERATURE")
public class SensorTemperature extends Sensor {
	@Column(name = "temperature_value")
	private float  temperature_value;
	@Column(name = "moisture_value")
	private float moisture_value;
	public float getTemperature_value() {
		return temperature_value;
	}
	public void setTemperature_value(float temperature_value) {
		this.temperature_value = temperature_value;
	}
	public float getMoisture_value() {
		return moisture_value;
	}
	public void setMoisture_value(float moisture_value) {
		this.moisture_value = moisture_value;
	}
	
}
