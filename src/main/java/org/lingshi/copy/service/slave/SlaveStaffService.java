package org.lingshi.copy.service.slave;

import org.lingshi.copy.bean.Staff;
import org.lingshi.copy.constant.CommonContant;
import org.lingshi.copy.constant.TargetDataSource;
import org.lingshi.copy.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: StaffService
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/19 11:27
 **/
@Service
public class SlaveStaffService {

    private static final Logger logger = LoggerFactory.getLogger(SlaveStaffService.class);

    @Autowired
    StaffRepository staffRepository;


    /**
     * @Transactional(rollbackFor = Exception.class)
     *   主要是因为开启了事务管理，所以可以直接用。 主从库一样
     * @param staff
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @TargetDataSource(value = CommonContant.SLAVE_DATASOURCE)
    public boolean addSlave(Staff staff){
        Staff save = staffRepository.save(staff);
        logger.warn("{}", save);
        if ("master".equals(save.getUsername())){
            throw new RuntimeException("username 值不能是 [master]");
        }
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
