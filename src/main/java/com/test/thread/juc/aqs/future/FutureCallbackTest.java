package com.test.thread.juc.aqs.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FutureCallbackTest {


    public static final int SLEEP_GAP = 500;


    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }


    /**
     * 热水任务
     */
    static class HotWarterJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {

            try {
                System.out.println("洗好水壶"+ Thread.currentThread().getName());
                System.out.println("灌上凉水"+ Thread.currentThread().getName());
                System.out.println("放在火上"+ Thread.currentThread().getName());
                //TimeUnit.SECONDS.sleep(1);
                //线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                System.out.println("水开了");

            } catch (InterruptedException e) {
                System.out.println(" 发生异常被中断.");
                return false;
            }
            System.out.println(" 烧水工作，运行结束."+ Thread.currentThread().getName());

            return true;
        }
    }

    /**
     * 洗茶壶
     */
    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗茶壶" + Thread.currentThread().getName());
                System.out.println("洗茶杯" + Thread.currentThread().getName());
                System.out.println("拿茶叶" + Thread.currentThread().getName());
                //线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                //TimeUnit.SECONDS.sleep(1);
                System.out.println("洗完了" + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                System.out.println(" 清洗工作 发生异常被中断.");
                return false;
            }
            System.out.println(" 清洗工作  运行结束." + Thread.currentThread().getName());
            return true;
        }

    }

    //泡茶线程
    static class MainJob implements Runnable {

        volatile boolean warterOk = false;
        volatile boolean cupOk = false;
        int gap = SLEEP_GAP / 10;

        @Override
        public void run() {
            boolean flg =true;
            while (flg) {
                try {
                    //TimeUnit.SECONDS.sleep(10);
                    Thread.sleep(gap);
                    System.out.println("读书中......" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    System.out.println(getCurThreadName() + "发生异常被中断.");
                }

                if (warterOk && cupOk) {
                    drinkTea(warterOk, cupOk);
                    flg=false;
                }
            }
        }


        /**
         * 喝茶
         * @param wOk
         * @param cOK
         */
        public void drinkTea(Boolean wOk, Boolean cOK) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (wOk && cOK) {
                System.out.println("泡茶喝，茶喝完" + Thread.currentThread().getName());
                this.warterOk = false;
                this.gap = SLEEP_GAP * 100;
            } else if (!wOk) {
                System.out.println("烧水失败，没有茶喝了");
            } else if (!cOK) {
                System.out.println("杯子洗不了，没有茶喝了");
            }

        }
    }

    public static void main(String args[]) {

        //新起一个线程，作为泡茶主线程
        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob);
        mainThread.setName("主线程");
        mainThread.start();

        //烧水的业务逻辑
        Callable<Boolean> hotJob = new HotWarterJob();
        //清洗的业务逻辑
        Callable<Boolean> washJob = new WashJob();

        //创建java 线程池
        ExecutorService jPool = Executors.newFixedThreadPool(10);
        ExecutorService callbackExecutor = Executors.newFixedThreadPool(10, new CustomizableThreadFactory("custom-Callback"));
        //包装java线程池，构造guava 线程池
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);
        //提交烧水的业务逻辑，取到异步任务
        ListenableFutureTask<String> stringListenableFutureTask = ListenableFutureTask.create(() -> {
            System.out.println("dd");
            return "dd";
        });

        stringListenableFutureTask.addListener(() -> {
            System.out.println("a addListener execute");
        }, callbackExecutor);

        ListenableFuture<Boolean> hotFuture = gPool.submit(hotJob);
        hotFuture.addListener(() -> {
            System.out.println("a addListener execute");
        }, callbackExecutor);
        //绑定任务执行完成后的回调，到异步任务，水烧开了，将 warterOk 置为 true
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    System.out.println("hotFuture 回调，通知水好了 current thread: " + Thread.currentThread().getName());
                    mainJob.warterOk = true;
                }
            }

            public void onFailure(Throwable t) {
                System.out.println("washFuture 回调， current thread: " + Thread.currentThread().getName());
                System.out.println("烧水失败，没有茶喝了");
            }
        }, callbackExecutor);
        //提交清洗的业务逻辑，取到异步任务

        ListenableFuture<Boolean> washFuture = gPool.submit(washJob);
        //绑定任务执行完成后的回调，到异步任务
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    System.out.println("washFuture 回调，杯子洗好了 current thread: " + Thread.currentThread().getName());
                    mainJob.cupOk = true;
                }
            }
            public void onFailure(Throwable t) {
                System.out.println("washFuture 回调， current thread: " + Thread.currentThread().getName());
                System.out.println("杯子洗不了，没有茶喝了");
            }
        }, callbackExecutor);
    }


}
