package com.primeton.liuning.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.primeton.liuning.demo.dto.UserDTO;
import com.primeton.liuning.demo.model.UserEntity;

import java.util.List;

/**
 * 用户持久层
 * @author liuning
 * @date 2018/11/5 15:51
 */
public interface UserDao {

    UserEntity login(UserEntity user);

    int insertUser(UserEntity user);

    int deleteUser(int id);

    int updateUser(UserEntity user);

    List<UserDTO> query(@Param("pageSize") Integer pageSize,@Param("beginPage") Integer beginPage,@Param("userName") String userName,@Param("orgId")Integer orgId);

    UserDTO getUser(Integer id);

    int countUser(@Param("userName") String userName,@Param("orgId") Integer orgId);
}
