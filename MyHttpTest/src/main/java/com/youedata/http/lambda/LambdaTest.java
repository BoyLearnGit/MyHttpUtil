package com.youedata.http.lambda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

import org.apache.commons.beanutils.DynaBeanMapDecorator;

public interface LambdaTest {
	public static void main(String[] args) {
		//1用lambda表达式实现Runnable
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("start");
			}
		});
		try {
			for (int i = 0; i < 1; i++) {
				Thread t=new Thread(() ->test(1));
				t.start();
				Thread.sleep(4000);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//2使用Java 8 lambda表达式进行事件处理
		JButton show =  new JButton("Show");
		show.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    System.out.println("Event handling without lambda expression is boring");
		    }
		});
		// Java 8方式：
		show.addActionListener((e) -> {
		    System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
		});
		
		//3使用lambda表达式对列表进行迭代
		// Java 8之前：
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//		for (String feature : features) {
//		    System.out.println(feature);
//		}
//		features.forEach(f->System.out.println(f));
		//方法的引用，已经存在的方法可以用::引用
		features.forEach(System.out::println);
		
		//4使用lambda表达式和函数式接口Predicate
		 List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		 
//		    System.out.println("Languages which starts with J :");
//		    filter(languages, (str)->((String) str).startsWith("J"));
//		 
//		    System.out.println("Languages which ends with a ");
//		    filter(languages, (str)->((String) str).endsWith("a"));
//		 
//		    System.out.println("Print all languages :");
//		    filter(languages, (str)->true);
//		 
//		    System.out.println("Print no language : ");
//		    filter(languages, (str)->false);
//		 
//		    System.out.println("Print language whose length greater than 4:");
//		    filter(languages, (str)->((String) str).length() > 4);
		    
		    
		   //5如何在lambda表达式中加入Predicate
		 // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
		 // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
		 Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		 Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		 languages.stream()
		     .filter(startsWithJ.and(fourLetterLong))
		    // .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
		     .forEach(System.out::println);
		 
		 //6.Java 8中使用lambda表达式的Map和Reduce示例
		// 不使用lambda表达式为每个订单加上12%的税
		 List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		 for (Integer cost : costBeforeTax) {
		     double price = cost + .12*cost;
		     System.out.println(price);
		 }
		  
		 // 使用lambda表达式
		 List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
		 costBeforeTax1.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
		 
		// 为每个订单加上12%的税
		// 老方法：
		List costBeforeTax3 = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		for (Integer cost : costBeforeTax) {
		    double price = cost + .12*cost;
		    total = total + price;
		}
		System.out.println("Total : " + total);
		 
		// 新方法：
		List costBeforeTax4 = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
		
		//7通过过滤创建一个String列表
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> strList=Arrays.asList("abc","bcd","defg","jk");
		List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
		//8.对列表的每个元素应用函数
		// 将字符串换成大写并用逗号链接起来
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
		//9.复制不同的值，创建一个子列表
		// 用所有不同的数字创建一个正方形列表
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
		//10.计算集合元素的最大值、最小值、总和以及平均值
		//获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}
	
	static void test(int a){
	  System.out.println("test"+a);
	}
	
	public static void filter(List<String> names, Predicate condition) {
	    for(String name: names)  {
	        if(condition.test(name)) {
	            System.out.println(name + " ");
	        }
	    }
	}
	
	
}
