package utils;

import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.util.CollectionUtil;
import com.kangzz.mtool.util.SortUtil;
import com.sun.tools.jdi.LinkedHashMap;
import org.junit.Test;

import java.util.Map;

/**
 * Created by kangzz on 16/12/16.
 */
public class TestSort {
    @Test
    public void test(){
        Integer[] aa = new Integer[10];
        aa[0] = 1;
        aa[1] = 122222;
        aa[2] = 1234;
        aa[3] = 111;
        aa[4] = 19;
        aa[5] = 18;
        aa[6] = 11;
        aa[7] = 10;
        aa[8] = 10;
        aa[9] = 10;
        SortUtil.sortArray(aa);
        String[] bb = new String[4];
        bb[0] = "张三";
        bb[1] = "王五";
        bb[2] = "李四";
        bb[3] = "康";
        SortUtil.sortArray(bb);
        Console.log(CollectionUtil.toString(bb));
        Map<String,String> map1 = new LinkedHashMap();
        map1.put("1","aaa");
        map1.put("2","bbb");
        map1.put("4","ccc");
        map1.put("7","eee");
        map1.put("3","ddd");
        map1.put("8","bbb");
        Map map2 = SortUtil.sortMapByKeyAsc(map1);
        Map map3 = SortUtil.sortMapByKeyDes(map1);
        Map map4 = SortUtil.sortMapByValueAsc(map1);
        Map map5 = SortUtil.sortMapByValueDes(map1);
    }
}
