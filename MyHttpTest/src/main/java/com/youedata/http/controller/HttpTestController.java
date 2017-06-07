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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youedata.billingService.service.BillingService;
import com.youedata.domain.common.Const;
import com.youedata.domain.dto.AccountDetailDTO;
import com.youedata.domain.dto.AccountDetailResultDTO;
import com.youedata.domain.dto.ApiTimesFrozenDTO;
import com.youedata.domain.dto.ApiUseStatusDTO;
import com.youedata.domain.dto.BaseResult;
import com.youedata.domain.dto.BillDTO;
import com.youedata.domain.dto.GoodsDTO;
import com.youedata.domain.dto.OrderDTO;
import com.youedata.domain.dto.OrderResultDTO;
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
		ApiTimesFrozenDTO result=billingService.getValueForRedis("30_6831116430173779648_1");
		return result.toString();
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
//			BillDTO dto=new BillDTO();
//			dto.setAmount(1000L);
//			dto.setOrderId(1L);
//			dto.setOrderType(Const.ORDER_TYPE_CONSUME);
//			dto.setUserId(1L);
//			dto.setUserType(1);
			//预付费类型的交易
			//充值
//			BillDTO dto=new BillDTO();
//			dto.setAmount(10000000L);
//			dto.setChargingType(Const.CHARGING_TYPE_PRESTORE);
//			dto.setOrderType(Const.ORDER_TYPE_RECHARGE);
//			dto.setUserId(31L);
//			dto.setUserType(Const.USER_TYPE_ORDINARY);
//			dto.setPayModel(100);
			//消费
			BillDTO dto=new BillDTO();
			dto.setAmount(0L);
			dto.setChargingType(Const.CHARGING_TYPE_PRESTORE);
			dto.setOrderId(new Random().nextLong());
			dto.setOrderType(Const.ORDER_TYPE_CONSUME);
			dto.setUserId(31L);
			dto.setUserType(Const.USER_TYPE_ORDINARY);
			List<GoodsDTO> list=new ArrayList<GoodsDTO>();
			GoodsDTO goods=new GoodsDTO();
			goods.setAllowTimes(0);
			goods.setDataId("api1");
			goods.setDataType(Const.DATA_TYPE_API);
			goods.setGoodsId(1L);
			list.add(goods);
			dto.setGoodsList(list);
			BaseResult result=billingService.saveBill(dto);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
		
		@RequestMapping("test4")
		@ResponseBody
		public String httpTest4(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			ApiUseStatusDTO dto=new ApiUseStatusDTO();
			dto.setDataId("api1");
			dto.setToken("0A32C24D8F0E49E28D4F5D50089808FB");
			BaseResult result=billingService.apiUseFrozen(dto);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
		
		@RequestMapping("test5")
		@ResponseBody
		public String httpTest5(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			UserUseApiDTO dto=new UserUseApiDTO();
			dto.setDataType(Const.DATA_TYPE_API);
			dto.setUseResult(2);
			dto.setUseTag(Math.random()*100000+"");
			dto.setUseTime(System.currentTimeMillis());
			dto.setDataId("api1");
			dto.setToken("0A32C24D8F0E49E28D4F5D50089808FB");
			BaseResult result=proxyService.invoke(dto);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
		
		@RequestMapping("test6")
		@ResponseBody
		public String httpTest6(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			OrderDTO orderDTO=new OrderDTO();
			orderDTO.setBillType(200);
			orderDTO.setUserId(30L);
			Date date=new Date(117,04,06,11,54,16);
			
			orderDTO.setStartDate(new Date(117,04,06,11,54,16));
			orderDTO.setEndDate(new Date(117,04,06,11,54,18));
			OrderResultDTO result=billingService.searchOrderInfo(orderDTO);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
		
		@RequestMapping("test7")
		@ResponseBody
		public String httpTest7(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			AccountDetailDTO accountDetialDTO=new AccountDetailDTO();
//			orderDTO.setBillType(200);
//			orderDTO.setUserId(30L);
//			Date date=new Date(117,04,06,11,54,16);
//			orderDTO.setStartDate(new Date(117,04,06,11,54,16));
//			orderDTO.setEndDate(new Date(117,04,06,11,54,18));
//			accountDetialDTO.setBillType("");
			accountDetialDTO.setUserId(30L);
			AccountDetailResultDTO result=billingService.searchAccountDetial(accountDetialDTO);
			System.out.println(result.getMsg());
			return result.getMsg();
		
	}
}
