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
	 * 创建一个新文件
	 * @param filename 文件路径
	 * @return  是否创建新文件
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
	 * 向 指定文件中写入一行，如果没有则创建
	 * @param filename
	 * @param lineContent
	 * @return  是否写入成功
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
