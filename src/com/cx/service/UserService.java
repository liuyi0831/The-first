package com.cx.service;

import java.util.List;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.User;

public interface UserService {
	int updateUser(User user);//�޸��û���Ϣ
	int deleteUserById(String id);//ɾ���û���Ϣ
	User getUserById(String id);//ͨ���û�id����û���Ϣ
	List<User> getUsers(PageInfo pageInfo);//��ȡ�����û���Ϣ,��ҳ��ʾ
	int insertUser(User user);//������û�
	User getUserByemail(String email);//ͨ��email����û�����ϸ��Ϣ
	String userlogin(String email,String pwd);//��¼���ȡ�û���
}
