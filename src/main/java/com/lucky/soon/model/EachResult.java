package com.lucky.soon.model;

import lombok.Data;

/**
 * -Author: Sandy
 * -Date: 2019/10/4 15:19
 * -Description: 每期开奖结果-实体类
 **/
@Data
public class EachResult extends BaseModel {

    // 主键id
    private Integer id;

    // 平码1
    private Integer rs1;

    // 平码2
    private Integer rs2;

    // 平码3
    private Integer rs3;

    // 平码4
    private Integer rs4;

    // 平码5
    private Integer rs5;

    // 平码6
    private Integer rs6;

    // 特码
    private Integer rs7;

    // 日期
    private String date;

    // 期数
	private Integer orderNumber;
}
