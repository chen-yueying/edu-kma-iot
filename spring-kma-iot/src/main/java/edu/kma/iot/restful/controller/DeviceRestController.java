package edu.kma.iot.restful.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.Device;

@RestController
@RequestMapping("/rest/device")
public class DeviceRestController {
	@Autowired
	private DeviceDAO deviceDAO;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public List<Device> listAll(Principal principal) {
		if(principal == null) return deviceDAO.list(null);
		return deviceDAO.list(principal.getName().toString());
	}
	
	public void addDevice() {
		
	}
	
}
