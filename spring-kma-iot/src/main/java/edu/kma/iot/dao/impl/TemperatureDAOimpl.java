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
public class TemperatureDAOimpl implements DeviceDAO {
	private static final Logger LOG = Logger.getLogger(TemperatureDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFatory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(Device device) {
		Session session = sessionFactory.getObject().openSession();
		session.save(device);
		session.beginTransaction().commit();
		session.close();
		LOG.info("Insert SensorTemperator " + device.getMac_address() + " done!");
	}

	@Override
	public Device get(String mac_address) {
		try (Session session = sessionFactory.getObject().openSession()) {
			SensorTemperature temperature = session.get(SensorTemperature.class, mac_address);
			return temperature;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update(Device device) {
		SensorTemperature sen = (SensorTemperature) device;
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ss:mm:hh dd/MM/yyy");
		String hql = "update SensorTemperature set temperature_value=:temperature," + "moisture_value=:moisture,"
				+ "status_time=:status," + "where mac_address=:mac";
		Query<SensorTemperature> query = session.createQuery(hql);
		query.setParameter("temperature", sen.getTemperature_value());
		query.setParameter("moisture", sen.getMoisture_value());
		query.setParameter("status_time", dateFormat.format(new Date()));
		query.setParameter("mac_address", sen.getMac_address());
		query.executeUpdate();
		tran.commit();
		session.close();
		LOG.info("update SensorTemperature " + sen.getMac_address() + " done!");
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List list(String owner) {
		try (Session session = sessionFactory.getObject().openSession()) {
			String hql = "from SensorTemperature where owner=:owner";
			Query query = session.createQuery(hql);
			return (List<SensorTemperature>) query.list();
		}
	}

}
