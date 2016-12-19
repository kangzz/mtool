package utils;

import com.kangzz.mtool.date.DateUtil;
import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.util.IdCardUtils;
import org.junit.Test;

/**
 * Created by kangzz on 16/12/17.
 */
public class TestIdCardUtils {
    @Test
    public void test(){
        Boolean aa =  DateUtil.isValidBirthdayDate(2017,2,29);
        Console.log(aa+"");
        Console.log(IdCardUtils.validateCard("A5601463")+"");
        Console.log(IdCardUtils.validateCard("5215299(8)")+"");
    }
}
