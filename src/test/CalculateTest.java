import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.EachResult;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class CalculateTest extends BaseJunit {
    @Resource
    private CalculateDao calculateDao;

    @Test
    public void getThreeResultByDescTest() {
        /**
         * @Description : 获取前4期数据
         * @Param []:
         * @Return void:
         * @Author K1080077
         * @Date 2019/12/13
         */
        List<EachResult> threeResultByDesc = calculateDao.getThreeResultByDesc("2019-12-12");
        Assert.assertFalse(threeResultByDesc.isEmpty());
    }

    @Test
    public void test() {
        String string = "hello";
        test1(string);
        System.out.println(string);
    }

    public void test1(String str) {
        str = str + " 111";
//        return str;
    }
}
