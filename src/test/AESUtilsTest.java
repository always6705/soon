import com.lucky.soon.common.SysConstants;
import com.lucky.soon.util.AESUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName: AESUtils单元测试
 * @Description: TODO
 * @Author K1080068
 * @Date 2019/7/2/17:32
 * @Version 1.0
 */
public class AESUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(AESUtilsTest.class);

    public static void main(String[] args) {
        encryptStringTest();
    }
    /*
     * 字符串加密及解密UT
     * */
    public static void encryptStringTest() {
        // 密文
        String content = "3F1DC143A701EBECACEA94F184D32020";
        // 密钥
        String key = "9ef78b3699ac83aa59d1d7cd3afb9582";

//        String decryptString = AESUtils.decryptString(content, key);
//        logger.info("解密: {}", decryptString);

        // 加密content字符串, 密钥为dsp2019
        String encryptString = AESUtils.encryptString("dsp_user", key);
//        String encryptString = AESUtils.encryptString(decryptString, key);
        logger.info("加密: {}", encryptString);
    }

//    @Test
//    public void test() {
//        List<Integer> allNum = new ArrayList<>(SysConstants.ALL_NUM);
//        List<Integer> threeResultNumList = new ArrayList<>(allResult);
//
//        // 去除前3期已有的num
//        allNum.removeAll(threeResultNumList);
//    }
}
