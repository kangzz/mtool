package utils;

import com.kangzz.mtool.convert.Convert;
import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.util.BooleanUtils;
import com.kangzz.mtool.util.NumberUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：
 * 作者 ：kangzz
 * 日期 ：2017-01-16 21:01:51
 */
public class TestNumberUtils {
    @Test
    public void test(){

        BigDecimal bi = BigDecimal.ZERO;
        BigDecimal bb = Convert.toBigDecimal(NumberUtil.getLargerNumber(bi,2));
        Console.log("getLargerNumber:"+bb);
        Console.log("getLargerNumber:"+NumberUtil.getLargerNumber(bi,2));
        Console.log("getSmallNumber:"+NumberUtil.getSmallNumber(bi,2));

        Console.log("getMaxNumber:"+NumberUtil.getMaxNumber(bi,2,1234,null,null,222));
        Console.log("getMinNumber:"+NumberUtil.getMinNumber(bi,2,-111,2233,null));


    }
}
