package utils;

import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.util.BooleanUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Console.log("andNull_1:"+BooleanUtils.andNull(null,null,1));
        Console.log("andNull_2:"+BooleanUtils.andNull(null,null,null));

        Console.log("andNullOrEmpty_1:"+
                BooleanUtils.andNullOrEmpty(null,null,new ArrayList(),new HashMap()));
        List list = new ArrayList();
        Console.log("andNullOrEmpty_2:"+
                BooleanUtils.andNullOrEmpty(null,null,list,new HashMap()));
        list.add("123");
        Console.log("andNullOrEmpty_3:"+
                BooleanUtils.andNullOrEmpty(null,null,list,new HashMap()));
        Map map = new HashMap();
        Console.log("andNotNull_4:"+
                BooleanUtils.andNotNull(list,list,list,map));
        map.put("12",12);
        Console.log("andNotNull_5:"+
                BooleanUtils.andNotNull(list,list,list,map));
        Console.log("andNotNullOrEmpty_5:"+
                BooleanUtils.andNotNullOrEmpty(null,null,list,new HashMap()));
        Console.log("andNotNullOrEmpty_6:"+
                BooleanUtils.andNotNullOrEmpty(list,map));

        Console.log("orNull_1:"+
                BooleanUtils.orNull(list,map));
        Console.log("orNull_2:"+
                BooleanUtils.orNullOrEmpty(new HashMap(),map));

        Console.log("orEquals:"+BooleanUtils.orEquals("contract","123",12345,"contract"));
    }
}
