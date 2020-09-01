package com.demo.dao;

import com.demo.domain.Orders;
import com.demo.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "productDao")
public interface IProductDao {

    /**
     * 查询所有
     * @return
     */
    @Select(" select * from product")
    public List<Product> findAll();

    /**
     * 添加产品
     * @param product
     */
    @Insert(" insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select(" select * from product where id = #{id}")
    public Product findById(String id);


    @Delete(" delete from product where id = #{id}")
    void deleteProduct(String id);

    @Select(" select * from orders where productId = #{productId} ")
    List<Orders> findOrders(String productId);

    @Update(" update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id}")
    void update(Product product);
}
