package com.lucky.soon.model;

import lombok.Data;

import java.io.Serializable;

/**
 * -Author: Sandy
 * -Date: 2019/10/4 15:34
 * -Description:
 **/
@Data
public class BaseModel implements Serializable {

	// 创建日期, 格式化显示日期
//    @JsonFormat(pattern = "yyyy-MM-dd")
	private String createDate;

	// 期数
	private Integer orderNumber;

	// 步骤
	private Integer step;

}
