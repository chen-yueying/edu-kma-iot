package edu.kma.iot.controller.rest;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.UserDAO;
import edu.kma.iot.dao.model.Sensor;
import edu.kma.iot.dao.model.SensorTemperature;

@RestController
@RequestMapping("/device/sensor/rest")
public class SensorRestController {
	private static final Logger LOG = Logger.getLogger(SensorRestController.class);
	@Autowired
	private DeviceDAO temperatureDAO;
	

	@RequestMapping("/get/{mac_address}")
	public SensorTemperature get(@PathVariable String mac_adress) {
		return  (SensorTemperature) temperatureDAO.get(mac_adress);
	}


	@SuppressWarnings("rawtypes")
	@RequestMapping("/list")
	public List sensors_of_user(Principal principal) {
		return temperatureDAO.list(principal.getName());
	}

}
