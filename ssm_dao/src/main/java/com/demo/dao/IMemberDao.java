package com.demo.dao;

import com.demo.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("memberDao")
public interface IMemberDao {

    @Select(" select * from member where id = #{id}")
    public Member findById(String id);
}
