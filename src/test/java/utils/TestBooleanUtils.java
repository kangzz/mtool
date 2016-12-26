package utils;

import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.util.BooleanUtils;
import org.junit.Test;

/**
 * Created by kangzz on 16/12/26.
 */
public class TestBooleanUtils {
    @Test
    public void test(){
        Console.log("hasTrue:"+ BooleanUtils.or(false,1==2,"2".equals("2"))+"");
        Console.log("isAllTrue:"+BooleanUtils.and(false,1==2,"2".equals("2"))+"");

        Integer initValue = 2;
        initValue = BooleanUtils.toObjByBoolean(null,11,initValue,1111);
        Console.log("initValue:"+initValue);
        initValue = BooleanUtils.toObjByBoolean(false,11,234);
        Console.log("initValue:"+initValue);
        initValue = BooleanUtils.toObjByBoolean(true,11,567);
        Console.log("initValue:"+initValue);
    }
}
