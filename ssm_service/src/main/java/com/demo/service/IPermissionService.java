package com.demo.service;

import com.demo.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll(int page,int size);

    void save(Permission permission);

    Permission findById(String id);


    void deletePermission(String id);
}
