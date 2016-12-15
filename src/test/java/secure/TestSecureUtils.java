package secure;

import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.secure.SecureUtil;
import org.junit.Test;

import java.io.File;

/**
 * Created by kangzz on 16/12/15.
 */
public class TestSecureUtils {
    @Test
    public void test(){
        Console.log(SecureUtil.md5("123444ddd"));
        File file = new File("/Users/kangzz/统一透传增加数据.txt");
        File file2 = SecureUtil.md5(file);
        Console.log("12");
    }
}
