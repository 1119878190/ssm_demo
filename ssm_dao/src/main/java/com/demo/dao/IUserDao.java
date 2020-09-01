package com.demo.dao;

import com.demo.domain.Role;
import com.demo.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userDao")
public interface IUserDao {

    @Select(" select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column ="username"),
            @Result(property = "email",column ="email"),
            @Result(property = "password",column ="password"),
            @Result(property = "phoneNum",column ="phoneNum"),
            @Result(property = "status",column ="status"),
            @Result(property = "roles",column ="id",javaType =java.util.List.class,many = @Many(select = "com.demo.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username);


    /**
     * 查询所有
     * @return
     */
    @Select(" select * from users ")
    List<UserInfo> findAll();

    /**
     * 添加
     * @param userInfo
     */
    @Insert(" insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select(" select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column ="username"),
            @Result(property = "email",column ="email"),
            @Result(property = "password",column ="password"),
            @Result(property = "phoneNum",column ="phoneNum"),
            @Result(property = "status",column ="status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.demo.dao.IRoleDao.findByUserId"))
    })
    UserInfo findById(String id);


    @Select(" select * from role where id not in(select roleId from users_role where userId =#{userId})")
    List<Role> findOtherRoles(String userId);


    //这里要用到@Param
    @Insert(" insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
