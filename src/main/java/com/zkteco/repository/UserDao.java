package com.zkteco.repository;

import java.util.List;

import com.zkteco.model.User;

public interface UserDao{

	boolean saveUser(User user);

	List<User> fetchAllUser();

	User fetchUserById(Long id);

	boolean deleteUser(Long id);

}
