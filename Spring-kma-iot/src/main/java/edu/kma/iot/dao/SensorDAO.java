package edu.kma.iot.dao;

import java.util.List;

import edu.kma.iot.dao.model.Sensor;
import edu.kma.iot.dao.model.User;


public interface SensorDAO {
	public Sensor getSensor(String mac_adress);
	public List<Sensor> listAll();
	public List<Sensor> list_of_user(User user);
	public void insert_or_update(Sensor sensor);
	public void delete(String mac_address);
}
