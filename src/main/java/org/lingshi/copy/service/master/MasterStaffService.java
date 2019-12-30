package org.lingshi.copy.service.master;

import org.lingshi.copy.bean.Staff;
import org.lingshi.copy.constant.TargetDataSource;
import org.lingshi.copy.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: StaffService
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 10:50
 **/
@Service
public class MasterStaffService {

    private static final Logger logger = LoggerFactory.getLogger(MasterStaffService.class);

    @Autowired
    StaffRepository staffRepository;


    /**
     * @Transactional(rollbackFor = Exception.class)
     *   主要是因为开启了事务管理，所以可以直接用。 主从库一样
     * @param staff
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @TargetDataSource
    public boolean addMaster(Staff staff){
        Staff save = staffRepository.save(staff);
        logger.warn("{}", save);
        if ("slave".equals(save.getUsername())){
            throw new RuntimeException("username 值不能是 [slave]");
        }
        if (save != null){
            return true;
        }
        return false;
    }





}
