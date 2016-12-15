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
        //File file2 = SecureUtil.md5(file);
        Console.log(SecureUtil.encryptAES("张三喊口号的发掘的死卡死机12121jkhjkhk","8w091ql5l2tt6qxj"));
        String bbbb = SecureUtil.encryptAES("张三喊口号的发掘的死卡死机12121jkhjkhk","8w091ql5l2tt6qxj");
        Console.log(SecureUtil.decryptAES(bbbb,"8w091ql5l2tt6qxj"));
        Console.log("encryptDES:"+SecureUtil.encryptDES("张三喊口号的发掘的死卡死机12121jkhjkhk","8w091ql5l2tt6qxj"));
        bbbb = SecureUtil.encryptDES("张三喊口号的发掘的死卡死机12121jkhjkhk","8w091ql5l2tt6qxj");
        Console.log("decryptDES:"+SecureUtil.decryptDES(bbbb,"8w091ql5l2tt6qxj"));
        Console.log("12");
    }
}
