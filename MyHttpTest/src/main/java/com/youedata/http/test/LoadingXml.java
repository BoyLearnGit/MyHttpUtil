package com.youedata.http.test;

public class LoadingXml {
	private static LoadingXml loadingXml =new LoadingXml();
	private static String sss="ss";
	private LoadingXml(){
	loadingJDBC();
	}

	public static LoadingXml getLoadingXml(){
	return loadingXml;
	}

	public void loadingJDBC(){
	System.out.println(this.sss);

	}

	public static void main(String[] args){
	LoadingXml.getLoadingXml().loadingJDBC();	
	}
}
