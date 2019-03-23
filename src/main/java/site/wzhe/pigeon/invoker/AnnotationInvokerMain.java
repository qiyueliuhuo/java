package site.wzhe.pigeon.invoker;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/30.
 */
public class AnnotationInvokerMain {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"site/wzhe/pigeon/invoker/applicationContext.xml"});
        context.start();
        AnnotationInvoker annotationInvoker = (AnnotationInvoker)context.getBean("annotationInvoker");
        System.out.println(annotationInvoker.testEcho(" hello"));
    }
}
