package com.demo.dao;

import com.demo.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("travellerDao")
public interface ITravellerDao {

    @Select(" select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    public Traveller findById(String ordersId);
}
