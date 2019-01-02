package edu.kma.iot.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.SensorTemperature;

@RestController
@RequestMapping("/rest/cbnd")
public class SensorTemperatureRest {
	
	@Autowired
	private DeviceDAO temperatureDAO;
	
	@RequestMapping("/get-{mac_address}")
	public SensorTemperature getSensorTemp(@PathVariable("mac_address") String mac_address) {
		SensorTemperature sen = (SensorTemperature) temperatureDAO.get(mac_address);
		return sen;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)//arduino send to localhost:8080/rest/cbnd/save   -> JSON
	public void save(@RequestBody SensorTemperature sensor) {
		System.out.println("=================================================");
		if(temperatureDAO.get(sensor.getMac_address()) == null) {
			temperatureDAO.insert(sensor);
		}else {
			temperatureDAO.update(sensor);
		}
	}
	
	

}
