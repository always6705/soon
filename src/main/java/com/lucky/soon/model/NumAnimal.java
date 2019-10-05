package com.lucky.soon.model;

import lombok.Data;

import java.util.Date;

/**
 * -Author: Sandy
 * -Date: 2019/10/4 15:15
 * -Description: 号码-生肖-日期-实体类
 **/
@Data
public class NumAnimal {

    // 主键id
    private Integer id;

    // 号码
    private Integer num;

    // 生肖名
    private String animalName;

    // 开始时间
    private Date startDate;

    // 结束时间
    private Date endDate;

}
