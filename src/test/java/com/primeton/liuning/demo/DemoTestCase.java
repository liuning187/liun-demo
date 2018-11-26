package com.primeton.liuning.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.primeton.liuning.demo.controller.OrgController;
import com.primeton.liuning.demo.controller.UserController;
import com.primeton.liuning.demo.model.OrgEntity;
import com.primeton.liuning.demo.model.UserEntity;
import com.primeton.liuning.demo.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liuning
 * @date 2018/11/16 14:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { LiuNingDemoApplication.class })
public class DemoTestCase {

	@Autowired
	private UserController userController;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private OrgController orgController;

	/**
	 * 用戶測試
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUser() throws Exception {
		// 登录
		UserEntity user1 = new UserEntity();
		user1.setUserName("333");
		user1.setUserPassword("333");
		iUserService.login(user1);
		// 新增
		UserEntity user = new UserEntity();
		user.setUserName("新增");
		user.setUserPassword("123");
		user.setOrgId(1);
		userController.createUser(user);
		Assert.assertNotNull("创建数据记录失败", userController.createUser(user));
		// 修改
		user.setUserName("修改");
		user.setUserPassword("123");
		user.setOrgId(2);
		userController.modifyUser(user);
		Assert.assertNotNull("修改数据记录失败", userController.modifyUser(user));
		// 查询
		userController.getUser(user.getUserId());
		Assert.assertNotNull("查询数据记录失败", userController.getUser(user.getUserId()));
		// 删除
		userController.removeUser(user.getUserId());
		Assert.assertNotNull("删除数据记录失败", userController.removeUser(user.getUserId()));
	}

	/**
	 * 组织机构测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOrg() throws Exception {
		// 新增
		OrgEntity org = new OrgEntity();
		org.setOrgName("新增");
		org.setOrgShortName("123");
		orgController.createOrg(org);
		Assert.assertNotNull("创建数据记录失败", orgController.createOrg(org));
		// 修改
		org.setOrgName("修改");
		org.setOrgShortName("123");
		orgController.modifyOrg(org);
		Assert.assertNotNull("修改数据记录失败", orgController.modifyOrg(org));
		// 查询
		orgController.getOrg(org.getOrgId());
		Assert.assertNotNull("查询数据记录失败", orgController.getOrg(org.getOrgId()));
		// 删除
		orgController.removeOrg(org.getOrgId());
		Assert.assertNotNull("删除数据记录失败", orgController.removeOrg(org.getOrgId()));
	}
}
