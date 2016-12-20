package utils.proxy;

import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.proxy.ProxyUtil;
import com.kangzz.mtool.proxy.aspects.SimpleAspect;
import com.kangzz.mtool.proxy.aspects.TimeIntervalAspect;

/**
 * Created by kangzz on 16/12/19.
 */
public class TestProxy {
    public static void main(String[] args) {
        B b = new B();
        Console.log("--------------------------------------- 切面代理使用方式1");
        A a = ProxyUtil.proxy(new ExampleAspect(b));
        a.doSomeThing();

        Console.log("--------------------------------------- 切面代理使用方式2");
        a = ProxyUtil.proxy(b, ExampleAspect.class);
        a.doSomeThing();

        //SimpleAspect是一个简单切面类，无任何操作，可以继承此类实现需要的方法
        Console.log("--------------------------------------- SimpleAspect");
        a = ProxyUtil.proxy(new SimpleAspect(b));
        a.doSomeThing();

        //TimeIntervalAspect用于打印方法执行时间的日志的切面
        Console.log("--------------------------------------- TimeIntervalAspect");
        a = ProxyUtil.proxy(new TimeIntervalAspect(b));
        a.doSomeThing();
    }

}
