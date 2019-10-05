package com.lucky.soon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * -Author: Sandy
 * -Date: 2019/10/4 15:34
 * -Description:
 **/
@Data
public class BaseModel implements Serializable {

    // 创建日期, 格式化显示日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    // 期数
    private Integer orderNumber;

}
