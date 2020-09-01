package com.demo.service;

import com.demo.domain.Orders;
import com.demo.domain.Product;
import org.aspectj.weaver.ast.Or;

import java.util.List;


public interface IProductService {

    public List<Product> findAll(int page,int size);

    void save(Product product);

    void deleteProduce(String[] ids);

    List<Orders> findOrders(String productId);

    Product findById(String id);

    void update(Product product);
}
