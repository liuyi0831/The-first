package com.cx.service;

import java.util.List;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.User;

public interface UserService {
	int updateUser(User user);//修改用户信息
	int deleteUserById(String id);//删除用户信息
	User getUserById(String id);//通过用户id获得用户信息
	List<User> getUsers(PageInfo pageInfo);//获取所有用户信息,分页显示
	int insertUser(User user);//添加新用户
	User getUserByemail(String email);//通过email获得用户的详细信息
	String userlogin(String email,String pwd);//登录后获取用户名
}
