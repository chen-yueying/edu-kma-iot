package edu.kma.iot.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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
	@SuppressWarnings("rawtypes")
	public void insert(Device device) {
		SensorTemperature sensor = (SensorTemperature) device;
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ss:mm:hh dd/MM/yyy");
		String sql = " insert into SENSOR_TEMPERATURE (mac_address, status_time, temperature_value, moisture_value)"
				+ " values (:mac, :time, :temp, :mois) ";
		Query query = session.createNativeQuery(sql);
		query.setParameter("mac", sensor.getMac_address());
		query.setParameter("temp", sensor.getTemperature_value());
		query.setParameter("mois", sensor.getMoisture_value());
		query.setParameter("time", dateFormat.format(new Date()));
		query.executeUpdate();
		tran.commit();
		session.close();
		LOG.info("Insert SensorTemperator " + sensor.getMac_address() + " done!");
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public SensorTemperature get(String mac_address) {
		try (Session session = sessionFactory.getObject().openSession()) {
			Transaction tran = session.beginTransaction();
			SensorTemperature temperature = session.get(SensorTemperature.class, mac_address);
			if(temperature == null) {
				String sql = "insert into SENSOR_TEMPERATURE (mac_address) values (:mac)";
				Query query = session.createNativeQuery(sql);
				query.setParameter("mac", mac_address);
				query.executeUpdate();
				temperature = session.get(SensorTemperature.class, mac_address);
				session.flush();
				tran.commit();
				LOG.info("Insert SensorTemperator " + mac_address + " done!");
			}
			return  temperature;
		}
	}

	@Override
	public void update(Device device) {
		SensorTemperature sen = (SensorTemperature) device;
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ss:mm:hh dd/MM/yyy");
		SensorTemperature sensor = (SensorTemperature) session.merge(sen);
		sensor.setTemperature_value(sen.getTemperature_value());
		sensor.setMoisture_value(sen.getMoisture_value());
		sen.setStatus_time(dateFormat.format(new Date()));
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
