package com.spider.study;

import org.jsoup.nodes.Document;

/**
 * @author 武海升
 * @date 2018/12/19 11:05
 */
public class B {

    private static int pageSize =  20;

    public static void main(String[] args) {
        int sum = 0;
        for (int i = pageSize; i <= 100; i++) {
            sum++;
            System.out.print("["+i+"]");
            if(sum==20){
                System.out.println("已经处理了20次了");
                sum = 0;
                pageSize = i;
                System.out.println();
            }
        }
    }
}
