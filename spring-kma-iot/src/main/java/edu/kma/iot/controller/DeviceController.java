package edu.kma.iot.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.kma.iot.dao.ClassifyDeviceDAO;
import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.ClassifyDevice;
import edu.kma.iot.dao.model.Device;
import edu.kma.iot.dao.model.SensorTemperature;

@Controller
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private DeviceDAO deviceDAO;
	@Autowired
	private ClassifyDeviceDAO classifyDAO;
	
	@RequestMapping("/details")
	public ModelAndView devices(Principal principal, String message) {
		ModelAndView mv = new ModelAndView("/Device/device.details");
		mv.addObject("devices", deviceDAO.list(principal.getName()));
		mv.addObject("message", message);
		mv.addObject("classifies", classifyDAO.list());
		return mv;
	}
	
	private Map<String, String> toClassifyMap(List<ClassifyDevice> list){
		Map<String,String> map = new HashMap<>();
		list.forEach(classify -> map.put(classify.getType_code(), classify.getType_name()));
		return map;
	}
	
	@RequestMapping("/add")
	public ModelAndView addDevice(String message) {
		ModelAndView mv = new ModelAndView("/Device/device.add", "device-info", new Device());
		mv.addObject("message", message);
		mv.addObject("classifies", toClassifyMap(classifyDAO.list()));
		return mv;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("device-info") Device device, BindingResult result) {
		if(result.hasErrors()) {
			ModelAndView mv = addDevice("Xảy ra lỗi!");
			mv.addObject("errors", result);
			mv.addObject("device-info", device);
			return mv;
		}
		if(deviceDAO.get(device.getMac_address()) != null) return addDevice("Thiết bị đã tồn tại, không thể tạo mới!");
		ModelAndView mv = new ModelAndView("forward:/device/" + device.getType_code() + "/save");
		mv.addObject("device-info",device);
		return mv;
	}
}
