package com.cx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.AdminOrder;

public interface AdminOrderMapper {
List<AdminOrder> getAllListPage(@Param("page") PageInfo page);//��ҳ��ѯ�õ����еĶ�����Ϣ
}
