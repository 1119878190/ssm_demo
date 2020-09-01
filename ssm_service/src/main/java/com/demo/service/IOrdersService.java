package com.demo.service;

import com.demo.domain.Orders;

import java.util.List;

/**
 * 订单service
 */
public interface IOrdersService {

    List<Orders> findAll(int page,int size);

    Orders findById(String ordersId);
}
