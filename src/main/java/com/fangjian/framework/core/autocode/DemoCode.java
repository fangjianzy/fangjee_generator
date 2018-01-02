package com.fangjian.framework.core.autocode;

import java.io.IOException;
import java.sql.SQLException;

import com.fangjian.framework.common.util.FileTools;
import com.fangjian.framework.core.commom.CodeCfg;
import com.fangjian.framework.core.commom.SpringCfg;

import freemarker.template.TemplateException;

/**   
 * 类名称：DemoCode   </br>
 * 类描述：SSM生成器入口程序   </br>
 * 创建人：fangjian </br>
 * 创建时间：2016年1月18日 上午9:49:13   </br>
 * @version  0.1  </br>
 */
public class DemoCode {

	public static void main(String[] args) throws IOException, SQLException, TemplateException {
		CodeCfg cfg = new CodeCfg();
		//基础包
		/**
		 * com.jiesai.framework
		 * 					--controller.iemsuser
		 * 					--dao.iemsuser
		 * 					--service.iemsuser
		 * 					--servicei.iemsuser.impl
		 * 					--model.iemsuser
		 * 					--mapping.iemsuser
		 * 					--jsp/iemsuser/tablename/iemsuser_CRUD.JSP
		 */
		cfg.setBasePackage("com.fangjian.framework");
		
		//模块包,同一模块这个地方不需要修改,，比如系统管理模块，设备管理模块
		cfg.setBizPackage("youngy");
		//mvc:com.jiesai.framework.controller.iemsuser;
		//要生成的表名称
		cfg.setTable_name("advance_order");
		//表中文名称
		cfg.setCode_name("测试信息");
		//在硬盘路径下输出
		cfg.setOutput_path("D:\\generator-output\\gci_test");
		
		//1、可选
		//支持自定义实体,不配置则按照数据库名去掉下划线，全部转小写然后首字母大写，比如IMES_USER==>Iemsuser
		//cfg.setEntity_name("TproParme");
		//2、可选，生成其他框架组合，当前默认只支持springmvc+mybatis
		//cfg.setFramework_type(FrameworkType.ssh2);
		//3、可选，生成前可以选择清空一下目录
		FileTools.delDir("D:\\generator-output\\gci_test");
		//4、可选，是否生成spring单元测试文件，默认不生成
		cfg.setSpring_junit_test(SpringCfg.SRPING_TEST_DISABLED);
		
		cfg.setJspVsersion(SpringCfg.JSP_VERSION_QUIV3);
		
		//代码生成核心类
		CodeFactory.codeGenerate(cfg);
		
		//打开文件
		Runtime.getRuntime().exec("explorer "+cfg.getOutput_path()); 
	}

}

