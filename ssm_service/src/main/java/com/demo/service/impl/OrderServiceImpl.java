package com.demo.service.impl;

import com.demo.dao.IOrdersDao;
import com.demo.domain.Orders;
import com.demo.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ordersService")
public class OrderServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) {

        //参数pageNum是页码值,  pageSize代表是每页显示条数  (必须要写在查询方法之前,中间不能有其它代码)
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
