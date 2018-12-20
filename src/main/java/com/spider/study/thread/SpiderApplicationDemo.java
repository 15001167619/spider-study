package com.spider.study.thread;

/**
 * @author 武海升
 * @date 2018/12/20 11:33
 */
public class SpiderApplicationDemo {

    public static void main(String [] args) {
        Queue ps=new Queue(200, 20);
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"武海升"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"幕小谦"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"Lucky"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"小跟班"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"小老虎"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"小鸭子"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"小脑斧"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"大灰狼"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"喜洋洋"));
        ps.setThread(new Thread(new SpiderServiceImpl(ps),"光头强"));
        ps.run();
    }

}
