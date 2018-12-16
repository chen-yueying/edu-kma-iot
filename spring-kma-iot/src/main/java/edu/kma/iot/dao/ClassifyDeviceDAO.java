package edu.kma.iot.dao;

import java.util.List;

import edu.kma.iot.dao.model.ClassifyDevice;

public interface ClassifyDeviceDAO {
	public List<ClassifyDevice> list();
	public ClassifyDevice get(String type_code);
	public void delete(String type_code);
}
