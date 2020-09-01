package com.demo.service.impl;

import com.demo.dao.ISysLogDao;
import com.demo.domain.SysLog;
import com.demo.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("/sysLogService")
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
