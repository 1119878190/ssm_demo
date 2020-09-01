package com.demo.dao;

import com.demo.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("permissionDao")
public interface IPermissionDao {
    /**
     * 角色表调用,根据角色id查询
     * @param roleId
     * @return
     */
    @Select(" select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId);


    /**
     * 查询所有
     * @return
     */
    @Select(" select * from permission ")
    public List<Permission> findAll();


    @Insert(" insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);


    @Select(" select * from permission where id = #{id}")
    Permission findById(String id);

    @Delete(" delete from role_permission where permissionId = #{id}")
    void deleteFromRole_Permission(String id);

    @Delete(" delete from permission where id = #{id}")
    void deletePermission(String id);
}
