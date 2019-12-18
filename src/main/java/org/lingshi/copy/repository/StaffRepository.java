package org.lingshi.copy.repository;

import org.lingshi.copy.bean.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: StaffRepository
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 10:49
 **/
public interface StaffRepository extends JpaRepository<Staff, Integer>, JpaSpecificationExecutor<Staff> {
}
