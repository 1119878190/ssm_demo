package com.demo.dao;

import com.demo.domain.Permission;
import com.demo.domain.Role;
import com.demo.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //user调用的,根据用户id查询角色(中间表users_role)
    @Select(" select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType =java.util.List.class,many = @Many(select = "com.demo.dao.IPermissionDao.findByRoleId"))

    })
    public List<Role> findByUserId(String userId);


    /**
     * 查询所有
     * @return
     */
    @Select(" select * from role ")
    List<Role> findAll();


    @Insert(" insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据roleid查询
     * @param id
     * @return
     */
    @Select(" select * from role where id = #{id} ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.demo.dao.IPermissionDao.findByRoleId"))
    })
    Role findById(String id);

    @Delete(" delete from users_role where roleId = #{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId);

    @Delete(" delete from role_permission where roleId = #{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);

    @Delete(" delete from role where id = #{roleId}")
    void deleteRole(String roleId);


    @Select(" select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findRoleByIdAndPermission(String roleId);

    @Insert(" insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}

