package edu.kma.iot.dao;

import java.util.List;

import edu.kma.iot.dao.model.Device;
@SuppressWarnings("rawtypes")
public interface DeviceDAO {
	public void insert(Device device);
	public void update(Device device);
	public Device get(String mac_address);
	public List list(String owner);
	public void delete(String mac_address);
}
