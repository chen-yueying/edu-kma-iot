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

import edu.kma.iot.dao.ClassifyDeviceDAO;
import edu.kma.iot.dao.model.ClassifyDevice;
@Component("classifyDAO")
public class ClassifyDAOimpl  implements ClassifyDeviceDAO{
	private static final Logger LOG = Logger.getLogger(ClassifyDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ClassifyDevice> list() {
		try(Session session = sessionFactory.getObject().openSession()){
		String hql = "from ClassifyDevice";
		Query<ClassifyDevice> query = session.createQuery(hql);
		return query.list();
		}
	}

	@Override
	public ClassifyDevice get(String type_code) {
		try(Session session = sessionFactory.getObject().openSession()){
			ClassifyDevice classify = session.get(ClassifyDevice.class, type_code);
			return classify;
		}
	}

	@Override
	public void delete(String type_code) {
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		ClassifyDevice classify = session.get(ClassifyDevice.class, type_code);
		session.delete(classify);
		tran.commit();
		session.close();
	}

}
