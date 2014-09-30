package com.subao.validate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.subao.validate.entity.PackageNameInfo;
import com.subao.validate.http.HttpRequester;
import com.subao.validate.http.HttpRespons;
import com.subao.validate.util.FileUtil;
import com.subao.validate.util.Util;

public class ValidatePackageName{
	private static final String errorFilePath = "D:\\test\\error.txt";
	private File errorFile = new File(errorFilePath);
	private List<PackageNameInfo> packages = new ArrayList<PackageNameInfo>();
	private Window window;
	private List<String> errorList = new ArrayList<String>();
	private ValidateThread validateThread;
	private WriteFileThread writeFileTrhead;
	public ValidatePackageName(){
        this.window = new Window();
        this.window.setVisible(true);
		initWindow();
	}
	
	private void initWindow(){
        window.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String urlString = window.getUrlText().getText();
				if(urlString.trim().equals("")){
					JOptionPane.showMessageDialog(window, "请先填写匹配数据的访问地址");
				}else{
					try {
						HttpRequester request = new HttpRequester();
						HttpRespons hr = request.sendGet(urlString);
						//TODO 模拟的获取到的数据格式
						PackageNameInfo pInfo;
						List<String> ps;
						for(int i = 0; i < 1000; i++){
							ps = new ArrayList<String>();
							ps.add("com.subao.husubao");
							if(i == 10 || i == 30){
								ps.add("com.suba.huss");
							}else{
								ps.add("com.subao.game");
							}
							pInfo = new PackageNameInfo(i, "com.subao.*", ps);
							packages.add(pInfo);
						}
						startThread();
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(window, "访问该地址出现异常");
					}
				}
			}
		});
        
        window.getOpenFileButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openErrorFile();
			}
		});
	} 
	
	private void startThread(){
		validateThread = new ValidateThread();
		validateThread.start();
		writeFileTrhead = new WriteFileThread();
		writeFileTrhead.start();
	}
	
	class ValidateThread extends Thread{

		@Override
		public void run() {
			window.getTotalLabel().setText(String.valueOf(packages.size()));
			validate();
			if(writeFileTrhead != null){
				writeFileTrhead.setStop();
			}
		}	
	}
	
	class WriteFileThread extends Thread{
		private boolean isStop = false;
		@Override
		public void run() {
			while(!isStop){
				writeToFile();
			}
			try {
				copyAndWrite();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(window, "计算完毕");
			openErrorFile();
		}
		
		public void setStop(){
			isStop = true;
		}
	}
	
	/**
	 * 进行验证
	 */
	public void validate(){
		writeToError("\n\n\n开始时间：" + Util.getCurrentTime());
		for(int i = 0; i < packages.size(); i++){
			if(isValidateError(packages.get(i), packages.get(i).getId())){
				setNumberText(window.getErrorCountLabel());
			}
			setNumberText(window.getProgressLabel());
			synchronized (writeFileTrhead) {
				writeFileTrhead.notify();
			}
		}
	}
	
	private boolean isFull(){
		if(errorList.size() > 100) return true;
		return false;
	}
	
	
	/**
	 * 打开错误匹配记录文件
	 */
	private void openErrorFile(){
		try {
			Util.openFileInWindows(errorFilePath);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(window, "打开文件失败");
		}
	}
	


	/**
	 * 设置数字
	 * @param label
	 */
	private void setNumberText(JLabel label) {
		String error = label.getText();
		boolean isNum = error.matches("[0-9]+"); 
		if(isNum){
			label.setText(
					String.valueOf(Integer.parseInt(error) + 1));
		}
	}
	 
	/**
	 * 验证正则表达式
	 * @param info
	 * @return
	 */
	private boolean isValidateError(PackageNameInfo info, int id){
		boolean isError = false;
		Pattern pat = Pattern.compile(info.getMatchesString());
		List<String> packageNames = info.getPackageNames();
		Matcher matcher;
		//验证是否该应用的包名匹配次正则
		for(int i = 0; i < packageNames.size(); i++){
			matcher = pat.matcher(packageNames.get(i));
			if(!matcher.matches()){
				writeToError("*********************[ID:" + info.getId() + "]**********************");
				writeToError("正则：" + info.getMatchesString());
				writeToError("匹配错误的包名：" + packageNames.get(i));
			    isError = true;
			}
		}
		//验证是否其他的应用包名匹配此正则
		for(int j = 0; j < packages.size(); j++){
			List<String> ps = packages.get(j).getPackageNames();
			if(packages.get(j).getId() == id) continue;  //不去验证自己
			for(int k = 0; k < ps.size(); k++){
				matcher = pat.matcher(ps.get(k));
				if(matcher.matches()){
					writeToError("*********************[ID:" + info.getId() + "]**********************");
					writeToError("该应用的正则被ID是" + packages.get(j).getId() + "的应用所匹配");
					writeToError("匹配的包名是：" + ps.get(k));
					isError = true;
				}
			}
		}
		return isError;
	}

	/**
	 * 写入文件
	 * @param message
	 */
	private void writeToFile() {
		List<String> efile;
		try {
			if (isFull()) {
				synchronized (writeFileTrhead) {
					writeFileTrhead.wait();
					efile = new ArrayList<String>(errorList);		
					errorList.clear();
				}
				if(!efile.isEmpty()){
					FileUtil.writeListToFile(errorFile, efile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copyAndWrite() throws IOException {
		List<String> efile = new ArrayList<String>(errorList);		
		errorList.clear();
		if(!efile.isEmpty()){
			FileUtil.writeListToFile(errorFile, efile);
		}
	}
	
	private void writeToError(String message){
		errorList.add(message + "\n");
	}
	
	public static void main(String[] args) {  	
        ValidatePackageName validatePacakgeName = new ValidatePackageName();
    } 
}
