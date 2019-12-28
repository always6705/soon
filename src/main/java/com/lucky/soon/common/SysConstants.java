package com.lucky.soon.common;

import java.util.ArrayList;
import java.util.List;

/**
 * -Author: Sandy
 * -Date: 2019/10/5 23:08
 * -Description:
 **/
public class SysConstants {

	public final static List<Integer> ALL_NUM = initInteger();

	/**
	 * -Author: Sandy
	 * -Date: 2019/10/5 23:21
	 * -param:
	 * -Description: 生成1-49
	 */
	private static List<Integer> initInteger() {
		List<Integer> all_num = new ArrayList<>();
		for (Integer i = 1; i <= 49; i++) {
			all_num.add(i);
		}

		return all_num;
	}

	/**
	 * 步骤1
	 */
	public final static String CONSTANT_1 = "1";
	/**
	 * 步骤2
	 */
	public final static String CONSTANT_2 = "2";
	/**
	 * 步骤3
	 */
	public final static String CONSTANT_3 = "3";


}
