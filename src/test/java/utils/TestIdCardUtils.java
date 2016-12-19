package utils;

import com.kangzz.mtool.date.DateUtil;
import com.kangzz.mtool.lang.Console;
import org.junit.Test;

/**
 * Created by kangzz on 16/12/17.
 */
public class TestIdCardUtils {
    @Test
    public void test(){
        Boolean aa =  DateUtil.isValidBirthdayDate(2017,2,29);
        Console.log(aa+"");
    }
}
