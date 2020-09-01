package com.demo.service;

import com.demo.domain.Role;
import com.demo.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService extends UserDetailsService {
    /**
     * 查询
     * @return
     */
    List<UserInfo> findAll(int page,int size);

    /**
     * 添加
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserInfo findById(String id);

    List<Role> findOtherRoles(String userId);


    void addRoleToUser(String userId, String[] roleIds);
}
