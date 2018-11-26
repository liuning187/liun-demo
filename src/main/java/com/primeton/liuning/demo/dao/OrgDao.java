package com.primeton.liuning.demo.dao;

import java.util.List;

import com.primeton.liuning.demo.model.OrgEntity;

/**
 * 组织机构持久层
 * @author liuning
 * @date 2018/11/4 15:51
 */
public interface OrgDao {

	 int insertOrg(OrgEntity org);

	 int deleteOrg(int id);

	 int updateOrg(OrgEntity org);

	 List<OrgEntity> query(OrgEntity org);

	 OrgEntity getOrg(Integer id);

	 int countUser(Integer id);

}
