package com.primeton.liuning.demo.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeton.liuning.demo.dao.UserDao;
import com.primeton.liuning.demo.dto.UserDTO;
import com.primeton.liuning.demo.enums.CustomEnum;
import com.primeton.liuning.demo.exception.DemoException;
import com.primeton.liuning.demo.exception.JsonResult;
import com.primeton.liuning.demo.model.UserEntity;
import com.primeton.liuning.demo.service.IUserService;

/**
 * 用户实现类
 * 
 * @author liuning
 * @date 2018/11/14 16:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 用户登录
	 */
	@Override
	@Transactional(readOnly = true)
	public JsonResult login(UserEntity user) throws Exception {
		if (user.getUserName() == null || user.getUserPassword() == null) {
			throw new DemoException(CustomEnum.ERROR_FORMATUSER);
		}
		JsonResult jsonResult = new JsonResult<UserEntity>(user);
		if (userDao.login(user) != null) {
			jsonResult.setState("0");
			jsonResult.setMessage("登陆成功");
		} else {
			throw new DemoException(CustomEnum.ERROR_FORMATPASSWORD);
		}
		return jsonResult;
	}

	/**
	 * 新增用户
	 */
	@Override
	public JsonResult createUser(UserEntity user) throws Exception {
		JsonResult jsonResult = new JsonResult<UserEntity>(user);
		if (user != null) {
			if (user.getUserPassword() == null || user.getUserName() == null) {
				throw new DemoException(CustomEnum.ERROR_FORMATUSER);
			} else if (user.getOrgId() == null) {
				throw new DemoException(CustomEnum.ERROR_FORMATOGRIDNULL);
			} else {
				userDao.insertUser(user);
				jsonResult.setState("0");
				jsonResult.setMessage("创建成功");
				jsonResult.setData(user);
			}
		}
		return jsonResult;
	}

	/**
	 * 删除用户
	 */
	@Override
	public JsonResult removeUser(Integer userId) throws Exception {
		JsonResult jsonResult = new JsonResult<UserEntity>();
		if (userId == null) {
			throw new DemoException(CustomEnum.ERROR_FORMATUSERID);
		} else {
			userDao.deleteUser(userId);
			jsonResult.setState("0");
			jsonResult.setMessage("删除成功");
		}
		return jsonResult;
	}

	/**
	 * 修改用户
	 */
	@Override
	public JsonResult modifyUser(UserEntity user) throws Exception {
		JsonResult jsonResult = new JsonResult<UserEntity>(user);
		if (user != null) {
			if (user.getUserId() == null) {
				throw new DemoException(CustomEnum.ERROR_FORMATUSERID);
			} else {
				userDao.updateUser(user);
				jsonResult.setState("0");
				jsonResult.setMessage("修改成功");
				jsonResult.setData(user);
			}
		}
		return jsonResult;
	}

	/**
	 * 查询全部用户
	 */
	@Override
	@Transactional(readOnly = true)
	public Map queryUsers(Integer pageSize, Integer pageNumber, String userName, Integer orgId) throws Exception {
		Map map = new HashMap();
		Integer beginPage = 0;
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		if (pageNumber != null && pageNumber != 0) {
			beginPage = (pageNumber - 1) * pageSize;
		}
		int totalPage = userDao.countUser(userName, orgId) / pageSize;
		if (userDao.countUser(userName, orgId) % pageSize != 0) {
			totalPage++;
		}
		map.put("list", userDao.query(pageSize, beginPage, userName, orgId));
		map.put("pageCount", userDao.countUser(userName, orgId));
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		return map;
	}

	/**
	 * 根据ID查询用户
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDTO getUser(Integer userId) throws Exception {
		UserDTO dto = new UserDTO();
		if (userId != null) {
			dto = userDao.getUser(userId);
		}
		return dto;
	}
}
