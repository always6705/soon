package com.lucky.soon.dao;

import com.lucky.soon.model.EachResult;
import com.lucky.soon.model.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface CalculateDao {
	List<EachResult> test();

	/**
	 * -Author: Sandy
	 * -Date: 2019/10/5 22:59
	 * -param:
	 * -Description: 获取最后三期的num
	 */
	List<EachResult> getThreeResultByDesc();

	/**
	 * -Author: Sandy
	 * -Date: 2019/10/5 23:58
	 * -param:
	 * -Description: 根据tm获取num
	 */
	List<Integer> getNumBy7Result(@Param("date") String date,
								  @Param("_7ResultList") ArrayList<Integer> _7ResultList);

	Result getResultByDateAndOrderNumber(@Param("date") String date,
										 @Param("orderNumber") Integer orderNumber);

	/**
	 * @Description : 插入result
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/6
	 */
	void insertResult(@Param("date") String date,
					  @Param("orderNumber") Integer orderNumber,
					  @Param("content_system") String content_system,
					  @Param("count_system") int count_system);

	/**
	 * @Description : 更新result
	 * @Param :
	 * @Return : int
	 * @Author K1080077
	 * @Date 2019/10/6
	 */
	void updateResult(@Param("date") String date,
					  @Param("orderNumber") Integer orderNumber,
					  @Param("content_system") String content_system,
					  @Param("count_system") int count_system);

	/**
	 * @Description : buy num
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/7
	 */
	void updateResultForBuyNum(@Param("date") String date,
							   @Param("orderNumber") Integer orderNumber,
							   @Param("content_actual") String content_actual,
							   @Param("count_actual") int count_actual,
							   @Param("price") Integer price);

	/**
	 * @Description : insert each result
	 * @Param :
	 * @Return :
	 * @Author K1080077
	 * @Date 2019/10/7
	*/
	void insertEachResult(@Param("date") String date,
						  @Param("orderNumber") Integer orderNumber,
						  @Param("eachResult") EachResult eachResult);

	void updateResultForCalculate(@Param("result") Result result);

	void deleteEachResultByDateAndOrderNumber(@Param("date") String date,
											  @Param("orderNumber") Integer orderNumber);
}
