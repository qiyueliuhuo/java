package site.wzhe.jdk;

/**
 * @author: wangzhe.
 * @description:  有些方法是用正则表达式入参的，有些不是，正则表达式字符形式和普通字符串形式不一样！！！！
 * @date: 2018/8/14.
 */
public class Test {

    public static final String REGEX_END = "}";
    public static final String REGEX_START = "${";
    public static final String ORDERID = "orderId";

    public static void main(String[] args) {
        String url = "http://www.aaa.da?query=${orderId}";
        if (url.indexOf(REGEX_START) < 0) {
            System.out.println(url);
            return ;
        }
        String[] segments = url.split(REGEX_START);
        StringBuilder sb = new StringBuilder();
        for (String segment : segments) {
            if (!segment.endsWith(REGEX_END)) {
                sb.append(segment);
            } else {
                segment.replace(ORDERID + REGEX_END, "10000");
                sb.append(segment);
            }
        }
        System.out.println(sb.toString());
    }
}
