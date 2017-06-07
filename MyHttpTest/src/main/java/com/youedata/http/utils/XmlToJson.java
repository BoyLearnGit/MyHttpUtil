package com.youedata.http.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.json.XML;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class XmlToJson {
public static void main(String[] args) throws IOException {
        
        //读取src下的xml文件
        File file=new File("C:/Users/liyanlu/Desktop/xml样列/ORP00002F00008S00003D00001.xml");
        
        //字符流输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String string=null;
        StringBuffer sb = new StringBuffer();
        while((string = reader.readLine())!=null){
            sb.append(string);
        }
        XMLSerializer xmlSerializer=new XMLSerializer();
        xmlSerializer.setForceTopLevelObject(true);
        xmlSerializer.setSkipNamespaces(true);
//        xmlSerializer.setTrimSpaces(true);
//        xmlSerializer.setSkipWhitespace(true);
        JSON json=xmlSerializer.read(sb.toString());
        String root=xmlSerializer.getRootName();
        System.out.println(root);
        
        //这一句的输出,也许你很快的就知道原理了,其实原理很简单的！
        System.out.println("重点处：\n"+json.toString(1)+"\n");
//        ElasticsearchTools.addDocument(JSONObject.fromObject(json), "test", "test", "123");
//        Map<String, String> str=ElasticsearchTools.getDocument("test", "test", "123");
//        System.out.println(str);
        org.json.JSONObject jsonObj = XML.toJSONObject(sb.toString());  
        System.out.println("org.json xml2json:"+jsonObj);  
        //截取掉[],转化为JSONObject
//        JSONObject jsonObject=JSONObject.fromObject((json.toString()).substring(1, json.toString().length()-1));    
//        System.out.println("截取后：\n"+jsonObject.toString(1)+"\n");
        
        
       
    }
}
