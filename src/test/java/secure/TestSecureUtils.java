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
        String key = "8w091ql5l2aabbcc";
        Console.log(SecureUtil.md5("123444ddd"));
        File file = new File("/Users/kangzz/统一透传增加数据.txt");
        //File file2 = SecureUtil.md5(file);
        Console.log(SecureUtil.encryptAES("张三喊口号的发掘的死卡死机12121jkhjkhk",key));
        String bbbb = SecureUtil.encryptAES("张三喊口号的发掘的死卡死机12121jkhjkhk",key);
        Console.log(SecureUtil.decryptAES(bbbb,key));
        Console.log("encryptDES:"+SecureUtil.encryptDES("张三喊口号的发掘的死卡死机12121jkhjkhk",key));
        bbbb = SecureUtil.encryptDES("张三喊口号的发掘的死卡死机12121jkhjkhk",key);
        Console.log("decryptDES:"+SecureUtil.decryptDES(bbbb,key));

        File fileDes = SecureUtil.encryptDES(file,key);
        File fileDesbak = new File("/var/folders/fz/j3pt8pp91sn0b8lz1tlf303w0000gn/T/统一透传增加数据4576609949708267310.txt");
        File fileDesEn = SecureUtil.decryptDES(fileDesbak,key);


        File fileAES_en = SecureUtil.encryptAES(file,key);
        File fileAES_de = SecureUtil.decryptAES(fileAES_en,key);
        Console.log("12");
    }
}
