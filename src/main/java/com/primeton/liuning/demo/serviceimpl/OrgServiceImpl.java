package com.primeton.liuning.demo.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeton.liuning.demo.dao.OrgDao;
import com.primeton.liuning.demo.enums.CustomEnum;
import com.primeton.liuning.demo.exception.DemoException;
import com.primeton.liuning.demo.exception.JsonResult;
import com.primeton.liuning.demo.model.OrgEntity;
import com.primeton.liuning.demo.model.UserEntity;
import com.primeton.liuning.demo.service.IOrgService;

/**
 * 组织机构实现类
 * 
 * @author liuning
 * @date 2018/11/14 16:11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgServiceImpl implements IOrgService {

	@Autowired
	private OrgDao orgDao;

	/**
	 * 新增组织
	 */
	@Override
	public JsonResult createOrg(OrgEntity org) throws Exception {
		JsonResult jsonResult = new JsonResult<OrgEntity>(org);
		if (org != null) {
			if (org.getOrgName() == null || org.getOrgShortName() == null) {
				throw new DemoException(CustomEnum.ERROR_FORMATOGRNULL);
			} else if (org.getParentId() == null) {
				org.setParentId(0);
			} else {
				orgDao.insertOrg(org);
				jsonResult.setMessage("添加成功");
				jsonResult.setState("0");
				jsonResult.setData(org);
			}
		}
		return jsonResult;
	}

	/**
	 * 删除组织
	 */
	@Override
	public JsonResult removeOrg(Integer orgId) throws Exception {
		JsonResult jsonResult = new JsonResult<OrgEntity>();
		OrgEntity org = new OrgEntity();
		org.setOrgId(orgId);
		List<OrgEntity> list = orgDao.query(org);
		if (orgId == null) {
			throw new DemoException(CustomEnum.ERROR_FORMGORISNULL);
		} else if (orgDao.countUser(orgId) > 0) {
			throw new DemoException(CustomEnum.ERROR_FORMGORISUSER);
		} else if (list.size() > 0) {
			throw new DemoException(CustomEnum.ERROR_FORMGORISORG);
		} else {
			orgDao.deleteOrg(orgId);
			jsonResult.setMessage("删除成功");
			jsonResult.setState("0");
		}
		return jsonResult;
	}

	/**
	 * 修改组织
	 */
	@Override
	public JsonResult modifyOrg(OrgEntity org) throws Exception {
		JsonResult jsonResult = new JsonResult<OrgEntity>(org);
		if (org != null) {
			if (org.getOrgId() == null) {
				throw new DemoException(CustomEnum.ERROR_FORMGORISNULL);
			} else {
				orgDao.updateOrg(org);
				jsonResult.setMessage("修改成功");
				jsonResult.setState("0");
				jsonResult.setData(org);
			}
		}
		return jsonResult;
	}

	/**
	 * 查询所有组织
	 */
	@Override
	@Transactional(readOnly = true)
	public List<OrgEntity> queryOrgs(OrgEntity org) throws Exception {
		List<OrgEntity> list = new ArrayList<OrgEntity>();
		if (org != null) {
			list = orgDao.query(org);
		}
		return list;
	}

	/**
	 * 根据ID查询组织
	 */
	@Override
	@Transactional(readOnly = true)
	public OrgEntity getOrg(Integer orgId) throws Exception {
		OrgEntity org = new OrgEntity();
		if (org != null) {
			org = orgDao.getOrg(orgId);
		}
		return org;
	}

	@Override
	@Transactional(readOnly = true)
	public int countUser(Integer orgId) throws Exception {
		int count = 0;
		if (orgId != null) {
			count = orgDao.countUser(orgId);
		}
		return count;
	}
}
