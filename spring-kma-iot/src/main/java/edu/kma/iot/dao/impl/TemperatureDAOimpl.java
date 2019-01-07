package edu.kma.iot.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import edu.kma.iot.dao.DeviceDAO;
import edu.kma.iot.dao.model.Device;
import edu.kma.iot.dao.model.SensorTemperature;

@Component("temperatureDAO")
public class TemperatureDAOimpl implements DeviceDAO{
	private static final Logger LOG = Logger.getLogger(TemperatureDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFatory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insert(Device device) {
		System.out.println("============");
		SensorTemperature sensor = new SensorTemperature();
		Session session = sessionFactory.getObject().openSession();
		sensor.setMac_address(device.getMac_address());
		sensor.setOwner(device.getOwner());
		sensor.setName(device.getName());
		sensor.setLocation(device.getLocation());
		sensor.setClassify(device.getClassify());
		sensor.setType_code(device.getType_code());
		session.save(sensor);
		session.beginTransaction().commit();
		session.close();
		LOG.info("Insert SensorTemperator " + sensor.getMac_address() + " done!");
	}
	
	@Override
	public SensorTemperature get(String mac_address) {
		try (Session session = sessionFactory.getObject().openSession()) {
			SensorTemperature temperature = session.get(SensorTemperature.class, mac_address);
			return  temperature;
		}
	}

	@Override
	public void update(Device device) {
		SensorTemperature sen = (SensorTemperature) device;
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyy");
		SensorTemperature sensor = (SensorTemperature) session.merge(sen);
		sensor.setTemperature_value(sen.getTemperature_value());
		sensor.setHumidity_value(sen.getHumidity_value());
		sensor.setStatus_time(dateFormat.format(new Date()).toString());
		session.save(sensor);
		tran.commit();
		session.close();
		LOG.info("update SensorTemperature " + sensor.getMac_address() + " done!");
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List list(String owner) {
		try (Session session = sessionFactory.getObject().openSession()) {
			String hql = "from SensorTemperature where owner=:owner";
			Query query = session.createQuery(hql);
			query.setParameter("owner", owner);
			return (List<SensorTemperature>) query.list();
		}
	}

	@Override
	public void delete(String mac_address) {
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		SensorTemperature tem = session.get(SensorTemperature.class, mac_address);
		session.delete(tem);
		tran.commit();
		session.close();
	}

}
