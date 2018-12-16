package edu.kma.iot.dao.impl;

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

@Component("deviceDAO")
public class DeviceDAOimpl implements DeviceDAO {
	private static final Logger LOG = Logger.getLogger(DeviceDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Device get(String mac_address) {
		try (Session session = sessionFactory.getObject().openSession()) {
			Device device = session.get(Device.class, mac_address);
			return device;
		}
	}

	@Override
	public void insert(Device device) {
		Session session = sessionFactory.getObject().openSession();
		session.save(device);
		session.beginTransaction().commit();
		session.close();
		LOG.info("Insert device " + device.getMac_address() + " done!");
	}

	@Override
	public void update(Device device) {
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		Device dev = (Device) session.merge(device);
		session.save(dev);
		tran.commit();
		session.close();
		LOG.info("update Device " + device.getMac_address() + " done!");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Device> list(String owner) {
		try (Session session = sessionFactory.getObject().openSession()) {
			String hql;
			if (owner == null) {
				hql = "from Device";
			}else {
				hql = "from Device where owner=:owner ";
			}
			Query<Device> query = session.createQuery(hql);
			query.setParameter("owner", owner);
			session.beginTransaction().commit();
			return query.list();
		}
	}
}
