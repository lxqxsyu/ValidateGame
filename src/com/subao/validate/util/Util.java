package com.subao.validate.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	/**
	 * ��ȡ��ǰʱ��
	 * @return
	 */
	public static String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	/**
	 * ��windows�д��ļ�
	 * @throws IOException 
	 */
	public static void openFileInWindows(String filePath) throws IOException{
		String[] arg;
		Runtime run = Runtime.getRuntime(); 
	    arg = new String[3]; 
	    arg[0] = "cmd"; 
	    arg[1] = "/c"; 
	    arg[2] = "Explorer.exe /n , /select," + filePath; 
	    run.exec(arg); 
	}
}
