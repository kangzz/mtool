package utils.proxy;

import com.kangzz.mtool.lang.Console;

/**
 * Created by kangzz on 16/12/19.
 */
public class B extends Object implements A {
    @Override
    public String doSomeThing() {
        Console.log("Do Some Things");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
