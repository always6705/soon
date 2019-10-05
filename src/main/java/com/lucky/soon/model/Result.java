package com.lucky.soon.model;

import lombok.Data;

/**
 * -Author: Sandy
 * -Date: 2019/10/4 15:33
 * -Description: 下注内容-实体类
 **/
@Data
public class Result extends BaseModel {

    // 主键id
    private Integer id;

    // 下注内容(1.系统产生, 2.自己购买)
    private String content;

    // 类型: 系统产生-0, 自己购买-1
    private Character type;

    // 下注金额
    private Integer price;

}
