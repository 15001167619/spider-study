package com.spider.study;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 武海升
 * @date 2018/12/19 9:27
 */
public class A {

    private static void downloadData(String httpUrlPath, String filePath) {
        try {
            URL url;
            int responsecode;
            HttpURLConnection urlConnection=null;
            BufferedReader reader=null;
            String line;
            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
            url=new URL(httpUrlPath);
            //打开URL
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            //获取服务器响应代码
            responsecode=urlConnection.getResponseCode();
            System.out.println(responsecode);
            FileUtils.copyURLToFile(url, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        httpDownload("http://video.zhihuishu.com/zhs/ablecommons/demo/201806/dddee1c446314b84a26c74a8def3c3c7.mp4","G:\\whs\\files\\download\\123.mp4");
        httpDownload("https://img-hw.xvideos-cdn.com/videos/videopreview/5c/4e/74/5c4e74d2ef89c00581d00eb344fecf00_169.mp4","G:\\whs\\files\\download\\456.mp4");
    }

    public static boolean httpDownload(String httpUrl, String saveFile) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
