package com.youedata.http.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {
	@RequestMapping("index")
	public ModelAndView test(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	public static void main(String[] args) {
		int a=1;
		float b=1.3f;
		a+=b;
		b+=a;
		for (int i = 0; i < args.length; i++)
			 a=5;
		//a=(int) (a+b);
		System.out.println(a+"--"+b);
		System.out.println(1.2%0.5);
		
		int arr[]={1,2,3};
		arrTest(arr);
		for (int i = 0; i < arr.length; i++) {
			
			System.out.println(arr[i]);
		}
	}
	
	public static String arrTest(int[] a){
		System.out.println(a);
		a[0]=4;
		return a.toString();
	}
}
