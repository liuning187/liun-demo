package com.primeton.liuning.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.primeton.liuning.demo.dto.UserDTO;
import com.primeton.liuning.demo.exception.JsonResult;
import com.primeton.liuning.demo.model.UserEntity;
import com.primeton.liuning.demo.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户控制层
 * @author liuning
 * @date 2018/11/15 16:17
 */
@RestController
@RequestMapping("/api/users")
@Api(tags = { "用户管理接口", "UserController" })
public class UserController {

	@Autowired
	private IUserService iUserService;

	/**
	 * 用户登录
	 * 
	 * @param users
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/actions/login")
	@ApiOperation(value = "用户登录")
	public JsonResult login(@ApiParam("用户实体类") @RequestBody UserEntity users, HttpSession session) throws Exception {
		session.setAttribute("name", users.getUserName());
		return iUserService.login(users);
	}

	/**
	 * 新增
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/")
	@ApiOperation(value = "用户新增")
	public JsonResult createUser(@ApiParam("用户实体类") @RequestBody UserEntity user) throws Exception {
		return iUserService.createUser(user);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "删除用户")
	public JsonResult removeUser(@ApiParam("主键ID") @PathVariable("id") Integer id) throws Exception {
		return iUserService.removeUser(id);
	}

	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/{userId}")
	@ApiOperation(value = "修改用户")
	public JsonResult modifyUser(@ApiParam("用户实体类") @RequestBody UserEntity user) throws Exception {
		return iUserService.modifyUser(user);
	}

	/**
	 * 查询全部
	 * 
	 * @param pageSize
	 * @param pageNumber
	 * @param userName
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@GetMapping
	@ApiOperation(value = "用户查询全部")
	public Map queryUsers(@ApiParam("每页条数")  Integer pageSize, @ApiParam("当前页")  Integer pageNumber,
			@ApiParam("用户名")  String userName, @ApiParam("组织ID")  Integer orgId) throws Exception {
		return iUserService.queryUsers(pageSize, pageNumber, userName, orgId);
	}

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "用户根据ID查询")
	public UserDTO getUser(@ApiParam("用户ID") @PathVariable("id") Integer id) throws Exception {
		UserDTO record = iUserService.getUser(id);
		return record;
	}
}
