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
        String idCard = "211421198811242355";
        Console.log("验证日期是否是生日:"+DateUtil.isValidBirthdayDate(2017,2,29));
        Console.log("验证身份证(香港):"+IdCardUtils.validateCard("A5601463")+"");
        Console.log("验证身份证(澳门):"+IdCardUtils.validateCard("5215299(8)")+"");
        Console.log("获取年龄:"+IdCardUtils.getAgeByIdCard(idCard));
        Console.log("获取生日:"+IdCardUtils.getBirthByIdCard(idCard));
        Console.log("获取性别:"+IdCardUtils.getGenderByIdCard(idCard));
        Console.log("获取户籍:"+IdCardUtils.getProvinceByIdCard(idCard));
    }
}
