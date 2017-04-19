package com.youedata.http.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youedata.http.utils.HttpUtil;
import com.youedata.http.utils.HttpsUtil;
@Controller
public class HttpTestController {
	@RequestMapping("test")
	public void httpTest(HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		String result=HttpUtil.sendGet("http://localhost:8080/test1", "utf-8");
//		System.out.println(result);
		try {
		System.out.println(HttpsUtil.get("http://192.168.112.48:8080//test1",null,null));
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
}
