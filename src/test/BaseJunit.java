import com.lucky.soon.SoonApplication;
import com.lucky.soon.dao.CalculateDao;
import com.lucky.soon.model.EachResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SoonApplication.class)
@Rollback
public class BaseJunit {


}
