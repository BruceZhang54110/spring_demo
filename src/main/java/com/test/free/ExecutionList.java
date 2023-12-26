package com.test.free;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutionList {

    private static final Logger log = Logger.getLogger(ExecutionList.class.getName());


    private boolean executed = false;

    private RunnableExecutorNode runnables;


    /**
     * 存放监听任务和执行器的单向链表
     */
    private static final class RunnableExecutorNode {
        final Runnable runnable;
        final Executor executor;
        RunnableExecutorNode next;

        RunnableExecutorNode(Runnable runnable, Executor executor, RunnableExecutorNode next) {
            this.runnable = runnable;
            this.executor = executor;
            this.next = next;
        }
    }

    /**
     * 执行存放的监听方法
     */
    public void execute() {
        RunnableExecutorNode node;
        synchronized (this) {
            // 如果已经执行过了，则直接return
            if(executed) {
                return;
            }
            executed = true;
            node = runnables;
            runnables = null;
        }
        // 反转单链表
        RunnableExecutorNode reversedNode = null;
        while (node != null) {
            RunnableExecutorNode tmp = node;
            node = node.next;
            tmp.next = reversedNode;
            reversedNode = tmp;
        }
        while (reversedNode != null) {
            executeListener(reversedNode.runnable, reversedNode.executor);
            reversedNode = reversedNode.next;
        }

    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            log.log(
                    Level.SEVERE,
                    "RuntimeException while executing runnable " + runnable + " with executor " + executor,
                    e);        }
    }

    public void add(Runnable runnable, Executor executor) {
        if (runnable == null) {
            throw new NullPointerException("runnable");
        }
        if (executor == null) {
            throw new NullPointerException("executor");
        }
        synchronized (this) {
            if (!executed) {
                runnables = new RunnableExecutorNode(runnable, executor, runnables);
                return;
            }
        }
        executeListener(runnable, executor);
    }
}
