package com.spider.study.thread;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/12/20 11:27
 */
public class Queue {

    public int targetNum = 0;
    public int printOnce = 0;
    private int nowNum = 1;

    /**
     *
     * @param targetNum    总数量
     * @param printOnce 一次要执行的次数
     */
    public Queue(int targetNum, int printOnce) {
        super();
        this.targetNum = targetNum;
        this.printOnce = printOnce;
    }

    private int nextThreadNum=0;
    private int threadCount=0;

    private Map<Thread,Integer> threads=new LinkedHashMap<Thread,Integer>();

    public void setThread(Thread thread) {
        threads.put(thread, threadCount);
        threadCount++;
    }

    public void run() {
        for (Thread thread : threads.keySet()) {
            thread.start();
        }
    }

    public synchronized void spiderRun() throws InterruptedException {
        //获取当前线程
        Thread currentThread=Thread.currentThread();
        //获取当前线程坐标
        int currentNum=threads.get(currentThread);
        //判断是否为期望线程
        if(currentNum==nextThreadNum) {
            for(int i=0;i<printOnce;i++) {
                System.out.println("当前线程："+currentThread.getName()+":"+nowNum++);
                if(nowNum>targetNum) {
                    System.out.println("工作完成");
                    this.wait();
                }
            }
            //期望线程名+1
            nextThreadNum=(++nextThreadNum)%threadCount;
        }
    }

}
