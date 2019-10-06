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
        for (Integer i = 0; i < 49; i++) {
            all_num.add(i);
        }

        return all_num;
    }


}
