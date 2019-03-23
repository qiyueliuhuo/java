package site.wzhe.concurrent.test;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/22.
 */
public class Main {

    public static void main(String[] args) {
        final Filter filter = new Filter();
        for (int i = 0; i < 1000; i++) {
            final Request request = new Request();
            request.setString("hahah" + i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    filter.log(request);
                }
            }).start();
        }
    }
}
