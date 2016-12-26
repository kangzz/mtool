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
        Console.log("hasTrue:"+ BooleanUtils.hasTrue(false,1==2,"2".equals("2"))+"");
        Console.log("isAllTrue:"+BooleanUtils.isAllTrue(false,1==2,"2".equals("2"))+"");
    }
}
