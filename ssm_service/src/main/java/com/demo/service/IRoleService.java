package com.demo.service;

import com.demo.domain.Permission;
import com.demo.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll(int page,int size);

    void save(Role role);

    Role findById(String id);

    void deleteRole(String roleId);

    List<Permission> findOthersPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
