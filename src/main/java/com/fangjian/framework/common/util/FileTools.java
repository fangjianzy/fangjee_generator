package com.fangjian.framework.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 文件、文件夹操作工具
 * @author 方坚操作工具类
 */
public class FileTools {
	
	/**
	 * 创建文件夹
	 * @param path
	 */
	 public static void createDir(String path){
		 File file = new File(path);
		   if(!file.exists()){
		    file.mkdirs();
		   }
     }
	/**
	 * 创建文件
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	 public static void createFile(String path,String filename) throws IOException{
		 File file=new File(path+"/"+filename);
         if(!file.exists())
             file.createNewFile();
     }
	 
	 /**
	  * 删除文件
	  * @param path
	  * @param filename
	  */
	 public static void delFile(String path,String filename){
		 File file=new File(path+"/"+filename);
         if(file.exists()&&file.isFile())
             file.delete();
     }
	 //
	 /**
	  * 删除文件夹
	  * @param path
	  */
	 public static void delDir(String path){
         File dir=new File(path);
         if(dir.exists()){
             File[] tmp=dir.listFiles();
             for(int i=0;i<tmp.length;i++){
                 if(tmp[i].isDirectory()){
                     delDir(path+"/"+tmp[i].getName());
                 }
                 else{
                     tmp[i].delete();
                 }
             }
             dir.delete();
         }
     }
	 
	 /**
	  * 复制文件
	  * @param src
	  * @param dest
	  * @throws IOException
	  */
	 public void copyFile(String src,String dest) throws IOException{
         FileInputStream in=new FileInputStream(src);
         File file=new File(dest);
         if(!file.exists())
             file.createNewFile();
         FileOutputStream out=new FileOutputStream(file);
         int c;
         byte buffer[]=new byte[1024];
         while((c=in.read(buffer))!=-1){
             for(int i=0;i<c;i++)
                 out.write(buffer[i]);        
         }
         in.close();
         out.close();
     }
	 /**
	  * 写文件
	  * @param filepath
	  * @param contenxt
	  */
	 public static void printFile(String filepath,String contenxt){
		 try {
             FileOutputStream out=new FileOutputStream(filepath);
             PrintStream p=new PrintStream(out);
             //for(int i=0;i<10;i++)
              p.println(contenxt);
         } catch (FileNotFoundException e){
             e.printStackTrace();
         }
	 }
	 //写文件
	 public void PrintStreamDemo(){
         try {
             FileOutputStream out=new FileOutputStream("D:/test.txt");
             PrintStream p=new PrintStream(out);
             for(int i=0;i<10;i++)
                 p.println("This is "+i+" line");
         } catch (FileNotFoundException e){
             e.printStackTrace();
         }
     }
	 
    /**
	 * 写txt里的单行内容
	 * @param filePath  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		PrintWriter pw;
		try {
			pw = new PrintWriter( new FileWriter(filePath));
			pw.print(content);
	        pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 public static void main(String[] args) {
		String path = "D:/generator-output/gci/com/jiesai/framework/";
		String file = "D:/generator-output/gci/com/jiesai/framework/test.txt";
	    FileTools.createDir(path);
	    FileTools.printFile(file, "test fangjian 中文测试 for hello word!");
		
	}
}
