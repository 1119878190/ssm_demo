package com.demo.service.impl;


import com.demo.dao.IProductDao;
import com.demo.domain.Orders;
import com.demo.domain.Product;
import com.demo.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteProduce(String[] ids) {
        for (String id : ids) {
            productDao.deleteProduct(id);
        }

    }

    @Override
    public List<Orders> findOrders(String productId) {
        return productDao.findOrders(productId);
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }
}
