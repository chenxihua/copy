package org.lingshi.copy.controller;

import org.lingshi.copy.bean.Staff;
import org.lingshi.copy.service.master.MasterStaffService;
import org.lingshi.copy.service.slave.SlaveStaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StaffController
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 10:55
 **/
@RestController
@RequestMapping(value = "/show")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    MasterStaffService masterStaffService;

    @Autowired
    SlaveStaffService slaveStaffService;

    /**
     * 主数据源添加
     * @param staff
     * @return
     */
    @PostMapping(value = "/add")
    public Map<String, Object> add(@RequestBody Staff staff){
        Map<String, Object> map = new HashMap<>(2);
        boolean add = masterStaffService.addMaster(staff);
        map.put("code", 0);
        map.put("data", add);
        return map;
    }


    /**
     * 从数据源添加
     * @param staff
     * @return
     */
    @PostMapping(value = "/addSlave")
    public Map<String, Object> addSlave(@RequestBody Staff staff){
        Map<String, Object> map = new HashMap<>(2);
        boolean add = slaveStaffService.addSlave(staff);
        map.put("code", 0);
        map.put("data", add);
        return map;
    }


    @GetMapping(value = "/findAll")
    public Map<String, Object> search(){
        Map<String, Object> map = new HashMap<>(2);
        List<Staff> staffList = slaveStaffService.search();
        map.put("code", 0);
        map.put("data", staffList);
        return map;
    }


}
