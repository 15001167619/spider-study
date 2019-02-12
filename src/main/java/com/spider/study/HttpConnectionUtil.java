package com.spider.study;

import java.net.MalformedURLException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 武海升
 * @date 2019/2/11 17:19
 */
public class HttpConnectionUtil {

    /**
     *
     * @param urlPath
     *            下载路径
     * @param downloadDir
     *            下载存放目录
     * @return 返回下载文件
     */
    public static File downloadFile(String urlPath, String downloadDir) {
        File file = null;
        try {
            URLConnection urlConnection = new URL(urlPath).openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
            bin.close();
            out.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return file;
        }

    }

    public static void main(String[] args) {
        // 下载文件测试
        downloadFile("https://201712mp4.89soso.com/20171208/15/1/xml/91_95d9047eefc941efaef7c54fb2899e41.mp4",
                "G:\\whs\\files");

    }

}
