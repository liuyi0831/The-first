package com.cx.service;

import java.util.List;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.Admin;

public interface AdminService {
int insertAdmin(Admin admin);
int deleteAdmin(String adminid);
int updateAdmin(Admin admin);
List<Admin> getAll(PageInfo pageInfo);
List<Admin> findByPower(String adminPower);
List<Admin> findByName(String name);
Admin findByid(String id);
Admin login(Admin admin);//登录返回管理员信息
}
