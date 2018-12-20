package com.spider.study.thread;

/**
 * @author 武海升
 * @date 2018/12/20 11:31
 */
public class SpiderServiceImpl implements Runnable {

    Queue ps;

    public SpiderServiceImpl(Queue ps ) {
        this.ps=ps;
    }

    public void run() {
        try {
            while(true) {
                ps.spiderRun();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
