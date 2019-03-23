package site.wzhe.concurrent.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/22.
 */
public class Filter {

    private ThreadLocal<String> stringLocal = new TransmittableThreadLocal<String>();
    private ExecutorService executorServicePool;
    private final String executorServiceLock = new String();
    public void log(final Request request) {
        stringLocal.set(request.getString());
        getExecutorService().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(stringLocal.get() + " = " + request.getString());
                return null;
            }
        });
    }
    private ExecutorService getExecutorService() {
        if (this.executorServicePool == null) {
            synchronized (executorServiceLock) {
                if (this.executorServicePool != null) {
                    return this.executorServicePool;
                }
                ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                        .setNameFormat("csc-visit-filter-data-pool-%d").build();
                int availableProcessorSize = Runtime.getRuntime().availableProcessors();
                this.executorServicePool = new ThreadPoolExecutor(availableProcessorSize * 10, availableProcessorSize * 50,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(availableProcessorSize * 100), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                // ExecutorServiceTtlWrapper
                executorServicePool = TtlExecutors.getTtlExecutorService(executorServicePool);
            }
        }
        return this.executorServicePool;
    }
}
