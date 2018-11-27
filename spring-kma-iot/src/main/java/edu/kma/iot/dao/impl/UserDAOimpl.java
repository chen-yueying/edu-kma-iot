package edu.kma.iot.dao.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import edu.kma.iot.dao.UserDAO;
import edu.kma.iot.dao.model.User;
@Component("userDAO")
public class UserDAOimpl implements UserDAO{
	private static final Logger LOGGER = Logger.getLogger(UserDAOimpl.class);
	private LocalSessionFactoryBean sessionFactory;
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public User getUser(String username) {
		try(Session session = sessionFactory.getObject().openSession()){
			User user = session.get(User.class, username);
			return user;
		}
	}
	@Override
	public void Insert_or_update(User user) {
		try(Session session = sessionFactory.getObject().openSession()){
			if(session.get(User.class, user.getUsername()) == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				user.setCreate_date(dateFormat.format(new Date()));
				session.save(user);
				session.flush();
				session.beginTransaction().commit();
				LOGGER.info("Insert user " +user.getUsername() + " done!");
			}else {
				session.update(user);
				session.flush();
				session.beginTransaction().commit();
				LOGGER.info("Update user " + user.getUsername() + "done!");
			}
		}
	}


	@Override
	public void delete(String username) {
		try(Session session = sessionFactory.getObject().openSession()){
			User user = session.get(User.class, username);
			if(user == null) return;
			session.delete(user);
			session.flush();
			session.beginTransaction().commit();
			LOGGER.info("Delete User " + user.getUsername() + " successful!");
		}
		
	}

}
