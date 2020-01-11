package com.site.mountain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
	
	public static String create64UpperUUID(){
		String uuid = (UUID.randomUUID().toString()+UUID.randomUUID().toString()).replace("-", "");
		return uuid.toUpperCase();
	}
	
	public static String create32UpperUUID(){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid.toUpperCase();
	}
	
	public static String create64UUID(){
		return (UUID.randomUUID().toString()+UUID.randomUUID().toString()).replace("-", "");
	}
	
	public static String create32UUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String createFamilyCard(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hehe = dateFormat.format( new Date() ).replace("-", ""); 
		int ram = (int)((Math.random()*9+1)*10000);
		return hehe+String.valueOf(ram);
	}

	public static void main(String[] args){
		String uuid=create64UpperUUID();
		System.out.println("uuid:"+uuid+"的长度是:"+uuid.length());
	}

}
