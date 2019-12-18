package org.lingshi.copy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @ClassName: Staff
 * @Create By: chenxihua
 * @Author: Administrator
 * @Date: 2019/12/18 10:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String pass;

    private Integer age;

    private String desction;


}
