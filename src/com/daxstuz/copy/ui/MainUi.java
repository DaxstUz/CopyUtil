package com.daxstuz.copy.ui;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.daxstuz.copy.util.CopyFileMySql;
import com.daxstuz.copy.util.CopyFileOracle;
import com.daxstuz.copy.util.CopyFilePg;
import com.daxstuz.copy.util.CopyFileSqlServer;
import com.daxstuz.copy.util.MySQLSqlUtil;
import com.daxstuz.copy.util.OracleSqlUtil;
import com.daxstuz.copy.util.PgSqlUtil;
import com.daxstuz.copy.util.SQLServerUtil;

public class MainUi extends JFrame {
	
	private JPanel jPanel=new JPanel();
	
	public MainUi() {
		initUi();
	}

	public void initUi(){
		this.setTitle("数据拷贝工具");
		this.setBounds(100, 100, 800, 300);
		
		jPanel.setLayout(null);
		
		JLabel labelIp=new JLabel("IP：");
		labelIp.setBounds(20, 20, 40, 20);
		
		TextField tFieldIp=new TextField();
		tFieldIp.setBounds(80, 20, 220, 20);
		
		JLabel labelPort=new JLabel("端口：");
		labelPort.setBounds(330, 20, 60, 20);
		
		TextField tFieldPort=new TextField();
		tFieldPort.setBounds(390, 20, 80, 20);
		
		JLabel labelDb=new JLabel("数据源：");
		labelDb.setBounds(500, 20, 60, 20);
		
		TextField tFieldDb=new TextField();
		tFieldDb.setBounds(580, 20, 100, 20);
		
		JLabel dbNme=new JLabel("用户名：");
		dbNme.setBounds(10, 60, 60, 20);
		
		TextField tFieldUserName=new TextField();
		tFieldUserName.setBounds(80, 60, 120, 20);
		
		JLabel dbPsd=new JLabel("用户密码：");
		dbPsd.setBounds(240, 60, 90, 20);
		
		TextField tFieldUserPsd=new TextField();
		tFieldUserPsd.setBounds(340, 60, 130, 20);
		
		
		
		jPanel.add(labelIp);
		jPanel.add(tFieldIp);
		jPanel.add(labelPort);
		jPanel.add(tFieldPort);
		jPanel.add(labelDb);
		jPanel.add(tFieldDb);
		jPanel.add(dbNme);
		jPanel.add(tFieldUserName);
		jPanel.add(dbPsd);
		jPanel.add(tFieldUserPsd);
		
		
		JLabel labelDbName=new JLabel("数据库：");
		labelDbName.setBounds(10, 90, 60, 20);
		jPanel.add(labelDbName);
		
		JPanel jPanelDb=new JPanel();
		jPanelDb.setLayout(new FlowLayout());
		jPanelDb.setBounds(80, 85,600,40);
		
		jPanelDb.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));// 设置内容面板为流式布局
        JRadioButton radioButtonOracle = new JRadioButton("Oracle");// 创建单选按钮
        radioButtonOracle.setActionCommand("Oracle");
        radioButtonOracle.setSelected(true);
        jPanelDb.add(radioButtonOracle);// 应用单选按钮
        
        JRadioButton radioButtonMySql = new JRadioButton("MySql");// 创建单选按钮
        radioButtonMySql.setActionCommand("MySql");
        jPanelDb.add(radioButtonMySql);// 应用单选按钮
        
        JRadioButton radioButtonSqlServer = new JRadioButton("SqlServer");// 创建单选按钮
        radioButtonSqlServer.setActionCommand("SqlServer");
        jPanelDb.add(radioButtonSqlServer);// 应用单选按钮
        
        JRadioButton radioButtonPgSql = new JRadioButton("PgSql");// 创建单选按钮
        radioButtonPgSql.setActionCommand("PgSql");
        jPanelDb.add(radioButtonPgSql);// 应用单选按钮
        
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonOracle);
        group.add(radioButtonMySql);
        group.add(radioButtonSqlServer);
        group.add(radioButtonPgSql);
        
        jPanel.add(jPanelDb);
        
        JLabel labelsql=new JLabel("执行语句：");
        labelsql.setBounds(10, 130, 80, 20);
		jPanel.add(labelsql);
		
		TextField tFieldSql=new TextField();
		tFieldSql.setBounds(130, 130, 620, 20);
		jPanel.add(tFieldSql);
        
        JLabel labelFilePath=new JLabel("选择文件路径：");
        labelFilePath.setBounds(10, 160, 100, 20);
		jPanel.add(labelFilePath);
		
		TextField tFieldFilePath=new TextField();
		tFieldFilePath.setBounds(130, 160, 220, 20);
		jPanel.add(tFieldFilePath);
		
		JButton jButtonFileChoose=new JButton("选择");
		jButtonFileChoose.setBounds(360, 155, 60, 30);
		jPanel.add(jButtonFileChoose);
		jButtonFileChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFileChooser jfc=new JFileChooser();  
			        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
			        jfc.showDialog(new JLabel(), "选择");  
			        File file=jfc.getSelectedFile();  
			        if(file.isDirectory()){  
			            System.out.println("文件夹:"+file.getAbsolutePath());  
			        }else if(file.isFile()){  
			            System.out.println("文件:"+file.getAbsolutePath());  
			            tFieldFilePath.setText(file.getAbsolutePath().replaceAll("\\\\", "/"));
			        } 
			        
			        System.out.println(jfc.getSelectedFile().getName()); 
			}
		});
		
        
        JButton jButtonOut=new JButton("导出");
        jButtonOut.setBounds(10, 210, 90, 30);
        jButtonOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag=isCanExcute(tFieldIp.getText(),tFieldPort.getText(),tFieldDb.getText(),tFieldUserName.getText(),tFieldUserPsd.getText(),group.getSelection().getActionCommand(),tFieldSql.getText(),tFieldFilePath.getText());
				if(flag){
					try {
						copyDatasOut(group.getSelection().getActionCommand(),tFieldIp.getText(),tFieldPort.getText(),tFieldDb.getText(),tFieldUserName.getText(),tFieldUserPsd.getText(),tFieldSql.getText(),tFieldFilePath.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
        
        JButton jButtonIn=new JButton("导入");
        jButtonIn.setBounds(120, 210, 90, 30);
        jButtonIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag=isCanExcute(tFieldIp.getText(),tFieldPort.getText(),tFieldDb.getText(),tFieldUserName.getText(),tFieldUserPsd.getText(),group.getSelection().getActionCommand(),tFieldSql.getText(),tFieldFilePath.getText());
				if(flag){
					try {
						copyDatasIn(group.getSelection().getActionCommand(),tFieldIp.getText(),tFieldPort.getText(),tFieldDb.getText(),tFieldUserName.getText(),tFieldUserPsd.getText(),tFieldSql.getText(),tFieldFilePath.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
        
        jPanel.add(jButtonOut);
        jPanel.add(jButtonIn);
		
        this.getContentPane().add(jPanel);
		
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
	}
	
	
	private void copyDatasOut(String type,String ip,String port,String db,String name,String psd ,String sqlExcute,String filePath) throws SQLException, IOException{
		Connection con = null;
		if("PgSql".equals(type)){
			con=PgSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFilePg.copyToFile(con, filePath, sqlExcute);
		}
		if("Oracle".equals(type)){
			con=PgSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFileOracle.copyToFile(con, sqlExcute);
		}
		if("MySql".equals(type)){
			con=PgSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFileMySql.copyToFile(con, filePath, sqlExcute);
		}
		if("SqlServer".equals(type)){
			con=PgSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFileSqlServer.copyToFile(con, filePath, sqlExcute);
		}
	}
	
	private void copyDatasIn(String type,String ip,String port,String db,String name,String psd ,String sqlExcute,String filePath) throws SQLException, IOException{
		Connection con = null;
		if("PgSql".equals(type)){
			con=PgSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFilePg.copyFromFile(con, filePath, sqlExcute); 
		}
		if("Oracle".equals(type)){
			con=OracleSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFileOracle.copyFromFile(con, sqlExcute); 
		}
		if("MySql".equals(type)){
			con=MySQLSqlUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFileMySql.copyFromFile(con, filePath, sqlExcute); 
		}
		if("SqlServer".equals(type)){
			con=SQLServerUtil.connectPgJdbc(ip,port,db,name,psd);
			CopyFileSqlServer.copyFromFile(con, filePath, sqlExcute); 
		}
	}
	
	private boolean isCanExcute(String ip,String port,String db,String name,String psd,String type ,String sqlExcute,String path){
		if(ip==null||ip.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入ip");
			return false;
		}
		if(port==null||port.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入端口");
			return false;
		}
		if(db==null||db.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入数据源");
			return false;
		}
		if(name==null||name.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入用户名");
			return false;
		}
		if(psd==null||psd.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入用户密码");
			return false;
		}
		if(sqlExcute==null||sqlExcute.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入执行语句");
			return false;
		}
		if(path==null||path.length()<=0){
			JOptionPane.showMessageDialog(jPanel, "请输入文件路径");
			return false;
		}
		return true;
	}
}
