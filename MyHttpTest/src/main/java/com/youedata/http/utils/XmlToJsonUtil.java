package com.youedata.http.utils;

import org.json.XML;

public class XmlToJsonUtil {
	public static String xmlToJson(String xml){
		return XML.toJSONObject(xml).toString();
	}
}
