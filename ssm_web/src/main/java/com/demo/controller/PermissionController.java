package com.demo.controller;

import com.demo.domain.Permission;
import com.demo.service.IPermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {


     @Autowired
     private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView();

        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissionList);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);

        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deletePermission")
    public String deletePermission(String id){

        permissionService.deletePermission(id);
        return "redirect:findAll";
    }
}
