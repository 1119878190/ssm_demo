package com.demo.service.impl;

import com.demo.dao.IRoleDao;
import com.demo.domain.Permission;
import com.demo.domain.Role;
import com.demo.service.IRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRole(String roleId) {
        //从user-role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRole(roleId);
    }

    @Override
    public List<Permission> findOthersPermission(String roleId) {
        return roleDao.findRoleByIdAndPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {

        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }

    }
}
