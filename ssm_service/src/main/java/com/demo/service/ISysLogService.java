package com.demo.service;

import com.demo.domain.SysLog;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ISysLogService  {

    public void save(SysLog sysLog);

    List<SysLog> findAll(int page,int size);
}
