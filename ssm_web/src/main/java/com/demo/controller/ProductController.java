package com.demo.controller;


import com.demo.domain.Orders;
import com.demo.domain.Product;
import com.demo.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")  //什么角色才可以访问  可以省略ROLE_前缀
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView();

        List<Product> products = productService.findAll(page,size);
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list1");
        return mv;
    }

    /**
     * 产品添加
     */
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }

    /**
     * 删除产品
     * @return
     */
    @RequestMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(name = "ids",required = true) String[] ids){
        productService.deleteProduce(ids);
        return "redirect:findAll";
    }


    /**
     * 订单查询
     */
    @RequestMapping("/findOrders")
    public ModelAndView findOrders(@RequestParam(name = "id",required = true)String productId){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = productService.findOrders(productId);
        mv.addObject("ordersList",ordersList);
        mv.setViewName("product-orders-show");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        ModelAndView mv = new ModelAndView();

        Product product = productService.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;

    }

    @RequestMapping("/update")
    public String update(Product product){
        System.out.println(product.getCityName());
        System.out.println(product.getId());
        productService.update(product);

        return "redirect:findAll";
    }
}
