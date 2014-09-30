package com.subao.validate.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class FileUtil {
	
	/**
	 * ����һ�����ļ�
	 * @param filename �ļ�·��
	 * @return  �Ƿ񴴽����ļ�
	 * @throws IOException
	 */
	private static boolean createFile(File filename) throws IOException{
		if(!filename.exists()){
			filename.createNewFile();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * �� ָ���ļ���д��һ�У����û���򴴽�
	 * @param filename
	 * @param lineContent
	 * @return  �Ƿ�д��ɹ�
	 * @throws IOException 
	 */
	public static void writeLineToFile(File filename, String lineContent) throws IOException{
		createFile(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
		writer.write(lineContent + "\n");
		writer.close();
	}
	
	public static void writeListToFile(File filename, List<String> list) throws IOException{
		createFile(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
		for(int i = 0; i < list.size(); i++){
			writer.write(list.get(i) + "\n");
		}
		writer.close();
	}
}
