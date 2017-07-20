package utils;

import com.alibaba.fastjson.JSONObject;
import com.kangzz.mtool.date.DateUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by kangzz on 16/12/16.
 */
public class TestDateUtil {
    @Test
    public void testGetDiffDays(){
        List list = DateUtil.getDiffDays(DateUtil.parseDate("2017-01-05"),DateUtil.parseDate("2017-01-02"));
        System.out.println("days1:"+JSONObject.toJSONString(list));
        list = DateUtil.getDiffDays(DateUtil.parseDate("2017-01-02"),DateUtil.parseDate("2017-01-05"));
        System.out.println("days2:"+JSONObject.toJSONString(list));
    }
}
