package cn.qidian.util;

import java.security.MessageDigest;
import java.util.Random;

public class WebUtil {


	/*	public static void main(String[] args) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		System.out.println(md);
	}*/
	//创建加密器
     public static String encodeByMD5(String password) throws Exception{
    	 MessageDigest md = MessageDigest.getInstance("MD5");
    	 byte[] result = md.digest(password.getBytes());
    	 String passwordMD5 = byteArrayToHexString(result);
    	 return passwordMD5;
    	 
     }
    
     //将byte[]转成16进制字符串
	private static String byteArrayToHexString(byte[] result) {
		// TODO Auto-generated method stub
		StringBuffer strb = new StringBuffer();
		for(byte bt:result){
			String hexString = byteToHexString(bt);
			strb.append(hexString);
		}
		return strb.toString();
	}
   
	//将byte转成16进制字符串
	private static String byteToHexString(byte bt) {
		// TODO Auto-generated method stub
		int n = bt;
		if(n<0){
			n = 256+n;
		}
		int d1 = n/16;
		int d2 = n%16;
		return hex[d1]+hex[d2];
	}
	private static String hex[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	
	//获取随机数
	public static String getRandom(){
		Random rd = new Random();
		int random = rd.nextInt(9000)+1000;
		return random+"";
	}   
}
