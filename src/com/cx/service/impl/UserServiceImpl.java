package com.cx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.UserMapper;
import com.cx.pagelist.PageInfo;
import com.cx.pojo.User;
import com.cx.service.UserService;
@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserMapper userservice;
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userservice.updateByPrimaryKey(user);
	}

	@Override
	public int deleteUserById(String id) {
		// TODO Auto-generated method stub
		return userservice.deleteByPrimaryKey(id);
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userservice.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getUsers(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return userservice.selectAllListPage(pageInfo);
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		boolean exist=userservice.selectByEmail(user.getUserEmail())==null;
		if (exist) {
			return userservice.insert(user);
		}
		return 0;
	}

	@Override
	public String userlogin(String email, String pwd) {
		// TODO Auto-generated method stub
		if (pwd.equals(userservice.selectByEmail(email).getUserPassword())) {
			return userservice.selectByEmail(email).getUserName();
		}
		else {
			return null;
		}
	}

	@Override
	public User getUserByemail(String email) {
		// TODO Auto-generated method stub
		return userservice.selectByEmail(email);
	}

}
