package com.fangjian.framework.core.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;





import com.fangjian.framework.common.util.FileTools;

import freemarker.template.Configuration;
import freemarker.template.Template; 
import freemarker.template.TemplateException;

/**
 * 生成器核心
 * @author 方坚
 */
public class FreemarkerTools {
	
	public static void main(String[] args) throws IOException, TemplateException {
		//获取模板路径
		String temppath = System.getProperty("user.dir")+"\\src\\templete";
		
		Configuration cfg = new Configuration();
		File f = new File(temppath);
		cfg.setDirectoryForTemplateLoading(f);
		//设置数据
		Map<String, Object>  m = new HashMap<String, Object>();
		Pvo p = new Pvo();
		p.setFname("asdasdas");
		p.setGname("sdsddd");
		p.setManager("fangjian");
		p.setEmail("yyjcf@163.com");
		m.put("p", p);
		Writer writer  = new StringWriter();
		//加载模板
		Template temp = cfg.getTemplate("test.xml");
		//设置模板+数据
		temp.process(m, writer);
		//生成
		String st = writer.toString();
		System.out.println(st);
		
		String path = "D:/generator-output/gci/com/jiesai/framework/";
		String file = "D:/generator-output/gci/com/jiesai/framework/test.xml";
	    FileTools.createDir(path);
	    FileTools.printFile(file, st);
	    
		writer.close();
		
	}
}
