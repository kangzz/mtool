package utils;


import com.kangzz.mtool.getter.BasicTypeGetter;
import com.kangzz.mtool.getter.OptNullBasicTypeFromObjectGetter;
import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.util.MapUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;


/**
 * 描述：map相关操作工具类
 * 作者 ：kangzz
 * 日期 ：2016-10-31 20:11:30
 */
public class TestMapUtil {


    @Test
    public void testParseObjectToMap() {
        Person p = new Person();
        p.setAbc(new BigDecimal(12));
        p.setAge(26);
        p.setMoney(23.34);
        p.setName("张三");
        try {
            Map<String,Object> map = MapUtil.parseObjectToMap(p);
            Person p2 = MapUtil.parseObject(map,Person.class);
            Console.log("123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
