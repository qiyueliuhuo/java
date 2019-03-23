package site.wzhe.aop.springAop.autoproxycreator;

/**
 * Created by wangzhe on 2018/1/28.
 */
public class Seller {
    public void greetTo(String name) {
        System.out.println(getClass().getName() + " seller greet to " + name);
    }
}
