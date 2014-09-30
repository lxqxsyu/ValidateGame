package com.subao.validate;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {
	private static final long serialVersionUID = -818246936479142069L;
	private JPanel contentPane;
	private JTextField txtHttpwwwbaiducom;  //url
	private JButton button;    //开始按钮
	private JButton button_2;  //错误文件
	private JLabel label_3;  //总数
	private JLabel label_6;  //当前进度
	private JLabel label_7;  //错误数量
	
	public JTextField getUrlText(){
		return txtHttpwwwbaiducom;
	}
	
	public JLabel getTotalLabel(){
		return label_3;
	}
	
	public JLabel getProgressLabel(){
		return label_6;
	}
	
	public JLabel getErrorCountLabel(){
		return label_7;
	}
	
	public JButton getStartButton(){
		return button;
	}
	
	public JButton getOpenFileButton(){
		return button_2;
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u9A8C\u8BC1\u5305\u540D\u5339\u914D\u89C4\u5219");
		label.setForeground(Color.RED);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(149, 10, 143, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6570\u636E\u5730\u5740\uFF1A");
		label_1.setBounds(27, 48, 78, 15);
		contentPane.add(label_1);
		
		txtHttpwwwbaiducom = new JTextField();
		txtHttpwwwbaiducom.setText("http://www.baidu.com");
		txtHttpwwwbaiducom.setBounds(104, 45, 298, 21);
		contentPane.add(txtHttpwwwbaiducom);
		txtHttpwwwbaiducom.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5339\u914D\u603B\u6570\uFF1A");
		label_2.setBounds(27, 87, 78, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("0");
		label_3.setBounds(112, 87, 157, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u5F53\u524D\u8FDB\u5EA6\uFF1A");
		label_4.setBounds(27, 126, 78, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u9519\u8BEF\u6570\u91CF\uFF1A");
		label_5.setBounds(27, 168, 78, 15);
		contentPane.add(label_5);
		
		button = new JButton("\u5F00\u59CB\u5339\u914D");
		button.setBounds(112, 213, 93, 23);
		contentPane.add(button);
		
		label_6 = new JLabel("0");
		label_6.setBounds(112, 126, 157, 15);
		contentPane.add(label_6);
		
		label_7 = new JLabel("0");
		label_7.setBounds(112, 168, 157, 15);
		contentPane.add(label_7);
		
		button_2 = new JButton("\u9519\u8BEF\u5339\u914D");
		button_2.setBounds(241, 213, 93, 23);
		contentPane.add(button_2);
	}
}
