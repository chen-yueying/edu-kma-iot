package edu.kma.iot.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import edu.kma.iot.dao.UserDAO;
import edu.kma.iot.dao.model.User;

@Component("userDAO")
public class UserDAOimpl implements UserDAO {
	private static final Logger LOG = Logger.getLogger(UserDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(String username) {
		try(Session session = sessionFactory.getObject().openSession();) {
		User user = session.get(User.class, username);
		return user;
		}
	}

	@Override
	public void Insert(User user) {
		Session session = sessionFactory.getObject().openSession();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/YYYY ");
		user.setCreate_date(dateFormat.format(new Date()).toString());
		session.save(user);
		session.beginTransaction().commit();
		session.close();
		LOG.info("Insert user " + user.getUsername() + " done!");
	}

	@Override
	public void delete(String username) {
			Session session = sessionFactory.getObject().openSession();
			Transaction tran = session.beginTransaction();
			User user = session.get(User.class, username);
			if (user == null) return;
			session.delete(user);
			tran.commit();
			session.close();
			LOG.info("Delete User " + user.getUsername() + " successful!");
		}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void update(User user) {
		Session session = sessionFactory.getObject().openSession();
		Transaction tran = session.beginTransaction();
		String hql = "update User  set  fullname = :fullname, "
				+ "email = :email, "
				+ "address = :address, "
				+ "password = :password "
				+ "where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("fullname", user.getFullname());
		query.setParameter("email", user.getEmail());
		query.setParameter("address", user.getAddress());
		query.setParameter("password", user.getPassword());
		query.setParameter("username", user.getUsername());
		query.executeUpdate();
		tran.commit();
		session.close();
		LOG.info("Update User " + user.getUsername());
	}

}
