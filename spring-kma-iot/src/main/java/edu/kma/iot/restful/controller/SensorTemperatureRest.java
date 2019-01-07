package edu.kma.iot.restful.controller;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.SensorTemperature;

@RestController
@RequestMapping("/rest/device/cbnd")
public class SensorTemperatureRest {

	@Autowired
	private DeviceDAO temperatureDAO;

	@RequestMapping("/get-{mac_address}")
	public SensorTemperature getSensorTemp(@PathVariable("mac_address") String mac_address) {
		System.out.println("================");
		SensorTemperature sen = (SensorTemperature) temperatureDAO.get(mac_address);
		return sen;
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST) // arduino send to localhost:8080/rest/cbnd/save ->// JSON
	public void save(@RequestBody SensorTemperature sensor){
		System.out.println("============================================================================");
		if(temperatureDAO.get(sensor.getMac_address()) != null) {
			temperatureDAO.update(sensor);
		}
	}
}
