package com.spider.study;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author 武海升
 * @date 2018/12/18 10:01
 */
public class SpiderApplication {

    private static String baseUrl = "https://db.yaozh.com/hmap?p=page&pageSize=20";
    private static String hospitalUrl = "https://db.yaozh.com";
    private static Integer page = 10;
    private static Integer pageSize = 20;

    private static Document getDocumentInfo(String pageUrl){
        Document doc = null;
        try {
            doc = Jsoup.connect(pageUrl)
                    .timeout(30000)
                    .validateTLSCertificates(false)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch")
                    .header("Accept-Language", "zh-CN,zh;q=0.8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;

    }

    private static void getHospitalInfo() {
        try {

            String pageUrl = "https://db.yaozh.com/hmap?name=&grade=全部8&address=&type=全部&bedstr=&bedend=&province=北京市&p=1&pageSize=20";



            Document doc = getDocumentInfo(pageUrl);
            System.out.println(doc);
//            Element pageElement = doc.select("div.offset-top").addClass("tr").last();


            Elements table = doc.select("table");
            Elements trs = table.select("tr");
            for(int i = 0;i<trs.size();i++){
                if(i!=0){
                    String hospitalInfoUrl = trs.get(i).select("th").select("a").first().attr("href");
                    System.out.println(hospitalInfoUrl);

                    Document hospitalInfoDoc = getDocumentInfo(hospitalUrl+hospitalInfoUrl);

                    Elements hospitalInfoTable = hospitalInfoDoc.select("table");
                    Elements hospitalInfoTrs = hospitalInfoTable.select("tr");
                    for (Element hospitalInfoTr : hospitalInfoTrs) {
                        if(hospitalInfoTr.select("th").text().equals("电话")){
                            System.out.println(hospitalInfoTr.select("td").text());
                        }
                    }


                    String hospitalName = trs.get(i).select("th").text();
                    System.out.print(hospitalName);
                    System.out.print("---");
                }
                Elements tds = trs.get(i).select("td");
                for(int j = 0;j<tds.size();j++){
                    String text = tds.get(j).text();
                    System.out.print("角标：【"+j+"】--->"+text);
                    System.out.print("---");
                }
                System.out.println();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpiderApplication.getHospitalInfo();
    }

}
