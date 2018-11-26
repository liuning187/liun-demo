package com.primeton.liuning.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.liuning.demo.exception.JsonResult;
import com.primeton.liuning.demo.model.OrgEntity;
import com.primeton.liuning.demo.service.IOrgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 组织机构控制层
 * @author liuning
 * @date 2018/11/16 16:37
 */
@RestController
@RequestMapping(value = "/api/orgs")
@Api(tags = { "组织管理接口", "OrgController" })
public class OrgController {

	@Autowired
	private IOrgService iOrgService;

	@PostMapping(value = "/")
	@ApiOperation(value = "新增组织结构")
	public JsonResult createOrg(@ApiParam("组织管理实体类") @RequestBody OrgEntity org) throws Exception {
		return iOrgService.createOrg(org);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "删除组织结构")
	public JsonResult removeOrg(@ApiParam("主键ID") @PathVariable("id") Integer id) throws Exception {
		return iOrgService.removeOrg(id);
	}

	@PutMapping(value = "/{orgId}")
	@ApiOperation(value = "修改组织结构")
	public JsonResult modifyOrg(@ApiParam("组织管理实体类") @RequestBody OrgEntity org) throws Exception {
		return iOrgService.modifyOrg(org);
	}

	@GetMapping(value = "/")
	@ApiOperation(value = "查询所有组织")
	public List<OrgEntity> queryOrgs(@ApiParam("组织管理实体类") OrgEntity org) throws Exception {
		return iOrgService.queryOrgs(org);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "根据ID查询组织")
	public OrgEntity getOrg(@ApiParam("主键ID") @PathVariable("id") Integer id) throws Exception {
		return iOrgService.getOrg(id);
	}
}
