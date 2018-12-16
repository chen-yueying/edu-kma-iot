package edu.kma.iot.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.Device;

@RestController
@RequestMapping("/device/rest")
public class DeviceRestController {
	@Autowired
	private DeviceDAO deviceDAO;
	
	@RequestMapping("/list")
	public List<Device> listAll() {
		return deviceDAO.list(null);
	}
	
}
