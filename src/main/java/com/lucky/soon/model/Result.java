package com.lucky.soon.model;

import lombok.Data;

/**
 * -Author: Sandy
 * -Date: 2019/10/4 15:33
 * -Description: 下注内容-实体类
 **/
@Data
public class Result extends BaseModel {

	// 每期结果
	private EachResult eachResult;

	// 主键id
	private Integer id;

	// 下注内容_系统
	private String contentSystem;

	// 下注内容_实际
	private String contentActual;

	// 个数_系统
	private Integer countSystem;

	// 个数_实际
	private Integer countActual;

	// 单价
	private Integer unitPrice;

	// 总价_系统
	private Integer totalSystem;

	// 总价_实际
	private Integer totalActual;

	// 结果_系统
	private Integer totalResultSystem;

	// 结果_实际
	private Integer totalResultActual;

	// 赔率
	private Integer odds;

	private Boolean realBuy;

	// 非表字段
	private String resultTotal;
	private String resultMoney;

}
