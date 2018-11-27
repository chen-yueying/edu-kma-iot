package edu.kma.iot.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import edu.kma.iot.dao.SensorDAO;
import edu.kma.iot.dao.model.Sensor;
import edu.kma.iot.dao.model.User;

@Component("sensorDAO")
public class SensorDAOimpl implements SensorDAO {
	private static final Logger LOGGER = Logger.getLogger(SensorDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFatory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Sensor getSensor(String mac_address) {
		try (Session session = sessionFactory.getObject().openSession()) {
			Sensor sensor = session.get(Sensor.class, mac_address);
			return sensor;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sensor> listAll() {
		try (Session session = sessionFactory.getObject().openSession()) {
			Query<Sensor> query = session.createQuery("from Sensor order by id desc");
			return (List<Sensor>) query.list();
		}
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Sensor> list_of_user(User user) {
		try (Session session = sessionFactory.getObject().openSession()) {
			String hql = "select from Sensor where owner = :name";
			Query<Sensor> query = session.createQuery(hql);
			query.setParameter("name", user.getUsername());
			session.beginTransaction().commit();
			return (List<Sensor>) query.list();
		}
	}

	@Override
	public void insert_or_update(Sensor sensor) {
		try (Session session = sessionFactory.getObject().openSession()) {
			if (session.get(Sensor.class, sensor.getMac_address()) == null) {
				session.save(sensor);
				session.flush();
				session.beginTransaction().commit();
				LOGGER.info("Insert Senser " + sensor.getName() + " done!");
			} else {
				session.update(sensor);
				session.flush();
				session.beginTransaction().commit();
			}
		}
	}

	@Override
	public void delete(String mac_address) {
		try(Session session = sessionFactory.getObject().openSession()){
			Sensor sensor = session.get(Sensor.class, mac_address);
			if(sensor == null) return;
			session.delete(sensor);
			session.flush();
			session.beginTransaction().commit();
			LOGGER.info("Delete Sensor " + sensor.getMac_address() +" successful!");
		}
	}
}
