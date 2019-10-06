package com.lucky.soon.service.impl;

import com.lucky.soon.common.SysConstants;
import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.EachResult;
import com.lucky.soon.service.CalculateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {

    @Resource
    private CalculateDao calculateDao;

    @Override
    public List<EachResult> test() {
//		return null;
        return calculateDao.test();
    }

    @Override
    public void calculate(Integer orderNumber) {
        // date, 期数


        List<EachResult> threeResultByDescList = calculateDao.getThreeResultByDesc();

        HashMap<String, Object> hashMap = getThreeResultNum(threeResultByDescList);

        // 根据tm获取num
        calculateDao.getNumBy7Result(new ArrayList<Integer>((HashSet)hashMap.get("_7ResultSet")));


    }

    /**
     * -Author: Sandy
     * -Date: 2019/10/5 23:29
     * -param:
     * -Description: 获取三期全部的num, 以及近三期tm生肖
     */
    private HashMap<String, Object> getThreeResultNum(List<EachResult> threeResultByDescList) {

        // 常量: 1-49
        List<Integer> allNum = SysConstants.ALL_NUM;

        // 返回buyNum, 以及tm
        HashMap<String, Object> hashMap = new HashMap<>();

        // 三期结果num
        List<Integer> threeResultNumList = new ArrayList<>();

        // tm
        HashSet<Integer> _7Result = new HashSet<>();

        for (EachResult eachResult : threeResultByDescList) {
            threeResultNumList.add(eachResult.getRs1());
            threeResultNumList.add(eachResult.getRs2());
            threeResultNumList.add(eachResult.getRs3());
            threeResultNumList.add(eachResult.getRs4());
            threeResultNumList.add(eachResult.getRs5());
            threeResultNumList.add(eachResult.getRs6());
            threeResultNumList.add(eachResult.getRs7());

            // 记录tm, 以便获取生肖, 之后去除该生肖所有num
            _7Result.add(eachResult.getRs7());
        }

        hashMap.put("allBuyNum", allNum.removeAll(threeResultNumList));
        hashMap.put("_7ResultSet", _7Result);

        return hashMap;
    }
}
