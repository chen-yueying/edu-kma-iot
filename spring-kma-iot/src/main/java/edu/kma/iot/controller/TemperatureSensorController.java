package edu.kma.iot.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.kma.iot.dao.ClassifyDeviceDAO;
import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.Device;
import edu.kma.iot.dao.model.SensorTemperature;

@Controller
@RequestMapping("/device/cbnd")
public class TemperatureSensorController {
	
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
	
	@RequestMapping("/get-{mac_address}")
	public ModelAndView temperatureDetails(@PathVariable("mac_address") String mac_address, Principal principal) {
		SensorTemperature sensor = (SensorTemperature) temperatureDAO.get(mac_address);
		if(!principal.getName().equals(sensor.getOwner())) return null;
		ModelAndView mv = new ModelAndView("/Device/temperatureSensor.details");
		mv.addObject("sensor", sensor);
		return mv;
	}
	
	@RequestMapping("/delete/{mac_address}")
	public ModelAndView delete(@PathVariable("mac_address") String mac_address, Principal principal) {
		SensorTemperature sensor = (SensorTemperature) temperatureDAO.get(mac_address);
		if(!principal.getName().equals(sensor.getOwner())) return null;
		temperatureDAO.delete(mac_address);
		ModelAndView mv = new ModelAndView("redirect:/device/details");
		mv.addObject("message", "Xóa thành công!");
		return mv;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("device-info") Device sensor, Principal principal) {
		sensor.setOwner(principal.getName());
		temperatureDAO.insert(sensor);
		ModelAndView mv = new ModelAndView("redirect:/device/details");
		mv.addObject("message", "Thêm thành công!");
		return mv;
	}
}
