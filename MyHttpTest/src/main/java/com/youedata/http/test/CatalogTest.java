package com.youedata.http.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.youedata.http.utils.XmlToJsonUtil;

import net.sf.json.JSONObject;

public class CatalogTest {
	public static void main(String[] args) {
		try {
			File file=new File("C:\\Users\\liyanlu\\Desktop\\catalog数据\\");
			File[] arr=file.listFiles();
			File out=new File("C:\\Users\\liyanlu\\Desktop\\catalog.txt");
			FileWriter fw = new FileWriter(out);
			BufferedWriter bw=new BufferedWriter(fw);
			for(File f:arr){
				InputStream is = new FileInputStream(f);
				System.out.println(is.available());
				byte[] read = new byte[is.available()];
				int n;
				String str="";
				while ((n = is.read(read)) != -1) {
					 str= new String(read,"UTF-8");
				}
			String json = XmlToJsonUtil.xmlToJson(str);
			System.out.println(json);
			JSONObject o=JSONObject.fromObject(json);
			JSONObject o1=JSONObject.fromObject(o.get("srvMetadata"));
			JSONObject o2=JSONObject.fromObject(o1.get("srvInfo"));
			JSONObject o3=JSONObject.fromObject(o1.get("chargeInfo"));
			String srvId=o2.getString("srvId");
			String srvName=o2.getString("srvName");
			if(!o3.containsKey("maxInvoke")){
				continue;
			}
			int	maxInvoke=o3.getInt("maxInvoke");
			int fee=o3.getInt("fee");
			System.out.println(srvId+srvName+maxInvoke+fee);
			bw.write(srvId+"    "+srvName+"    "+maxInvoke+"    "+fee);
			bw.newLine();
			is.close();
			
			}
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
