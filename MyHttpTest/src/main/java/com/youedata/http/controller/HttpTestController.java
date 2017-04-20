package com.youedata.http.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youedata.billingService.service.BillingService;
import com.youedata.domain.common.Const;
import com.youedata.domain.dto.ApiUseStatusDTO;
import com.youedata.domain.dto.BaseResult;
import com.youedata.domain.dto.BillDTO;
import com.youedata.domain.dto.UserUseApiDTO;
import com.youedata.http.utils.HttpUtil;
import com.youedata.http.utils.HttpsUtil;
import com.youedata.proxy.service.ProxyService;

import sun.net.www.http.HttpClient;
@Controller
public class HttpTestController {
	@Autowired
	private BillingService billingService;
	@Autowired
	private ProxyService proxyService;
	@RequestMapping("test")
	public void httpTest(HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		String result=HttpUtil.sendGet("http://localhost:8080/test1", "utf-8");
//		System.out.println(result);
		try {
		//String str=HttpsUtil.get("http://192.168.112.48:8080//test2",null,null);
	
		response.reset();
		OutputStream os=response.getOutputStream();
	    response.addHeader("Content-Disposition", "attachment;filename=" + new String("haha")+".zip");
		response.setContentType("application/octet-stream");
		String urlAdd="http://192.168.112.48:8080//test2";
		URL url=new URL(urlAdd);
		HttpURLConnection http=(HttpURLConnection) url.openConnection();
		InputStream is=http.getInputStream();
		System.out.println(is.available());
		byte[] b=new byte[is.available()];
		int a;
		int total = 0;
		while((a=is.read(b))!=-1){
			total += a;
			os.write(b, 0, a);
		}
		System.out.println("----------"+total);
		System.out.println(b.length);
		http.disconnect();
		is.close();
		response.addHeader("Content-Length", "" + b.length);
		os.flush();
		os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("test1")
	@ResponseBody
	public String httpTest1(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		System.out.println("成功");
		return "success";
	}
	
	@RequestMapping("test2")
	@ResponseBody
	public void httpTest2(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			File file=new File("F://txt//index1.zip");
			InputStream is=new FileInputStream(file);
			OutputStream os=response.getOutputStream();
		    response.addHeader("Content-Disposition", "attachment;filename=" + new String("haha")+".zip");
	        response.addHeader("Content-Length", "" + file.length());
			response.setContentType("text/plain");
		    byte[] b=new byte[is.available()];
		    is.read(b);
		    is.close();
			os.write(b);
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		@RequestMapping("test3")
		@ResponseBody
		public String httpTest3(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			BillDTO dto=new BillDTO();
			dto.setAllowTimes(10);
			dto.setAmount(1000L);
			dto.setDataId("api1,api2,api3");
			dto.setDataType(Const.DATA_TYPE_API);
			dto.setGoodsId(1L);
			dto.setOrderId(1L);
			dto.setOrderType(Const.ORDER_TYPE_CONSUME);
			dto.setUserId(1L);
			dto.setUserType(1);
			BaseResult result=billingService.saveBill1(dto);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
		
		@RequestMapping("test4")
		@ResponseBody
		public String httpTest4(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			ApiUseStatusDTO dto=new ApiUseStatusDTO();
			dto.setDataId("api");
			dto.setToken("12AA5955F55F4977A5FE91C0273FD5F3");
			BaseResult result=billingService.apiUseFrozen(dto);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
		
		@RequestMapping("test5")
		@ResponseBody
		public String httpTest5(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			UserUseApiDTO dto=new UserUseApiDTO();
			dto.setDataType(Const.DATA_TYPE_API);
			dto.setUseResult(1);
			dto.setUseTag(Math.random()*100000000+"");
			dto.setUseTime(System.currentTimeMillis());
			dto.setDataId("api");
			dto.setToken("12AA5955F55F4977A5FE91C0273FD5F3");
			BaseResult result=proxyService.invoke(dto);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
}
