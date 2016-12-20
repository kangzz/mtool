package utils.proxy;

import com.kangzz.mtool.exception.UtilException;
import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.proxy.Aspect;

import java.lang.reflect.Method;

/**
 * Created by kangzz on 16/12/19.
 */
public class ExampleAspect extends Aspect {
    public ExampleAspect(B target) {
        super(target);
    }

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        Console.log("=== I am before target method [{}] invoke.", method.getName());
        //throw new UtilException("开始异常了");
        return true;
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
        Console.log("=== I am after target throws exception.");
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args) {
        Console.log("=== I am after target [{}] method [{}] invoke.", target.getClass().getName(), method.getName());
        return true;
    }
}
