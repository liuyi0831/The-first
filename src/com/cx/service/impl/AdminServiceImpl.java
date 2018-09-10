package com.cx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.AdminMapper;
import com.cx.pagelist.PageInfo;
import com.cx.pojo.Admin;
import com.cx.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
@Autowired
private AdminMapper adminmap;
	@Override
	public int insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminmap.insert(admin);
	}

	@Override
	public int deleteAdmin(String adminid) {
		// TODO Auto-generated method stub
		return adminmap.deleteByPrimaryKey(adminid);
	}

	@Override
	public int updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminmap.updateByPrimaryKey(admin);
	}

	@Override
	public List<Admin> getAll(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return adminmap.selectAllListPage(pageInfo);
	}

	@Override
	public List<Admin> findByPower(String adminPower) {
		// TODO Auto-generated method stub
		return adminmap.selectByPower(adminPower);
	}

	@Override
	public List<Admin> findByName(String name) {
		// TODO Auto-generated method stub
		return adminmap.selectByname(name);
	}

	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		
		return adminmap.selectByNAP(admin.getAdminName(), admin.getAdminPassword());
	}

	@Override
	public Admin findByid(String id) {
		// TODO Auto-generated method stub
		return adminmap.selectByPrimaryKey(id);
	}

}
