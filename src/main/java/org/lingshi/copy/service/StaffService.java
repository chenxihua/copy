package org.lingshi.copy.service;

import org.lingshi.copy.bean.Staff;
import org.lingshi.copy.constant.CommonContant;
import org.lingshi.copy.constant.TargetDataSource;
import org.lingshi.copy.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: StaffService
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 10:50
 **/
@Service
public class StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Autowired
    StaffRepository staffRepository;

    @TargetDataSource
    public boolean add(Staff staff){
        Staff save = staffRepository.save(staff);
        logger.warn("save: {}", save);
        if (save != null){
            return true;
        }
        return false;
    }

    @TargetDataSource(value = CommonContant.SLAVE_DATASOURCE)
    public List<Staff> search(){
        return staffRepository.findAll();
    }


}
