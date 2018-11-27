package edu.kma.iot.dao;

import edu.kma.iot.dao.model.User;

public interface UserDAO {
	public User getUser(String username);
	public void Insert_or_update(User user);
	public void delete(String username);
}
