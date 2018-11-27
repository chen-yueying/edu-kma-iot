package edu.kma.iot.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kma.iot.dao.SensorDAO;
import edu.kma.iot.dao.UserDAO;
import edu.kma.iot.dao.model.Sensor;
import edu.kma.iot.dao.model.User;

@RestController
@RequestMapping("/rest/sensor")
public class SensorRestController {
	private static final Logger LOGGER = Logger.getLogger(SensorRestController.class);
	@Autowired
	private SensorDAO sensorDAO;
	@Autowired
	private UserDAO userDAO;
	
	
	@RequestMapping("/list-all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Sensor> ListSensor() {
		LOGGER.info("================");
		return sensorDAO.listAll();
	}
	
	@RequestMapping("/get/{mac_address}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public Sensor getSensor(@PathVariable String mac_adress) {
		return sensorDAO.getSensor(mac_adress);
	}
	
	@RequestMapping("/list-of-user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<Sensor> listofSensor(Principal principal){
		User user = userDAO.getUser(principal.getName());
		return sensorDAO.list_of_user(user);
	}
}
