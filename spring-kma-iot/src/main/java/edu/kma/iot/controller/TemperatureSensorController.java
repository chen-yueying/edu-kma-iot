package edu.kma.iot.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.kma.iot.dao.ClassifyDeviceDAO;
import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.ClassifyDevice;

@Controller
@RequestMapping("/device/cbnd")
public class TemperatureSensorController {
	
	@Autowired
	private DeviceDAO deviceDAO;
	@Autowired
	private DeviceDAO temperatureDAO;
	@Autowired
	private ClassifyDeviceDAO classifyDAO;
	
	@RequestMapping("")
	public ModelAndView listAll(Principal principal) {
		ModelAndView mv = new ModelAndView("/Device/temperatureSensor.list");
		mv.addObject("sensors", temperatureDAO.list(principal.getName()));
		mv.addObject("classifies", classifyDAO.list());
		return mv;
		}
	
	private boolean checkDevice(Principal principal, String mac_address) {
		String owner = deviceDAO.get(mac_address).getOwner();
		return owner.equals(principal.getName());
	}
	
	@RequestMapping("/get-{mac_address}")
	public ModelAndView temperatureDetails(@PathVariable("mac_address") String mac_address, Principal principal) {
		if(!checkDevice(principal, mac_address)) return null;
		ModelAndView mv = new ModelAndView("/Device/temperatureSensor.details");
		mv.addObject("sensor", temperatureDAO.get(mac_address));
		return mv;
	}
	
	@RequestMapping("/delete/{mac_address}")
	public ModelAndView delete(@PathVariable("mac_address") String mac_address, Principal principal) {
		if(!checkDevice(principal, mac_address)) return null;
		deviceDAO.delete(mac_address);
		ModelAndView mv = new ModelAndView("redirect:/device/details");
		mv.addObject("message", "Xóa thành công!");
		return mv;
	}
	
}
