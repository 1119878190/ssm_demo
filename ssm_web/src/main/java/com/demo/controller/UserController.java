package com.demo.controller;


import com.demo.domain.Role;
import com.demo.domain.UserInfo;
import com.demo.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  //方法权限,表达式
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size){
        ModelAndView mv = new ModelAndView();

        List<UserInfo> userList = userService.findAll(page,size);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userList);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");

        return mv;
    }


    /**
     * 保存用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username == 'tom'")  //方法权限,表达式,只有tom用户可以添加用户
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }

    /**
     * 根据id查询
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id ){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }


    /**
     *查询用户,以及用户可以添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);

        //2.根据用户id查询可以添加的角色
       List<Role> roleList =  userService.findOtherRoles(userId);

       mv.addObject("user",userInfo);
       mv.addObject("roleList",roleList);
       mv.setViewName("user-role-add");
        return mv;
    }


    /**
     * 给用户添加角色
     */
    @RequestMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)  String userId,@RequestParam(name = "ids",required = true) String[] roleIds){

        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll";
    }
}
