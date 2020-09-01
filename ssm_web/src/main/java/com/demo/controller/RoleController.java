package com.demo.controller;

import com.demo.domain.Permission;
import com.demo.domain.Role;
import com.demo.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Role role){

        roleService.save(role);
        return "redirect:findAll";

    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");

        return mv;
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(String id){

        roleService.deleteRole(id);
        return "redirect:findAll";
    }


    /**
     * 根据roleId查询role,并查询可以添加的权限
     */
    @RequestMapping("/findRoleByIdAndPermission")
    public ModelAndView findRoleByIdAndPermission(@RequestParam(name = "id" ,required = true) String roleId){
        ModelAndView mv = new ModelAndView();

        //根据roleId查询role
        Role role = roleService.findById(roleId);

        //根据roleId查询可以添加的权限
        List<Permission> othersPermissions = roleService.findOthersPermission(roleId);

        mv.addObject("role",role);
        mv.addObject("permissionList",othersPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }


    /**
     * 给角色添加权限
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds){

        roleService.addPermissionToRole(roleId,permissionIds);

        return "redirect:findAll";
    }
}
