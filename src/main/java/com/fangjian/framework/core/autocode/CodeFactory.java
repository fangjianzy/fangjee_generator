package com.fangjian.framework.core.autocode;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangjian.framework.common.util.FileTools;
import com.fangjian.framework.common.util.Tools;
import com.fangjian.framework.core.cfg.Frameworkcfg;
import com.fangjian.framework.core.commom.CodeCfg;
import com.fangjian.framework.core.commom.FrameworkType;
import com.fangjian.framework.core.commom.GciTempData;
import com.fangjian.framework.core.commom.PropertyObj;
import com.fangjian.framework.core.commom.SpringCfg;
import com.fangjian.framework.core.table.GciPropertyTable;
import com.fangjian.framework.core.table.GciTable;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 代码生成工厂
 * @author fangjian
 *
 */
public class CodeFactory {
		//private final static Logger log= Logger.getLogger(CodeFactory.class);
		protected final static Log log = LogFactory.getLog(CodeFactory.class);
		static String schema = PropertyObj.getInstance().getPropertiesByKey("/config.properties", "schema");
	
		/**
		 * 生成器入口
		 * @param cfg
		 * @throws SQLException
		 * @throws IOException
		 * @throws TemplateException
		 */
		public static void codeGenerate(CodeCfg cfg) throws SQLException, IOException, TemplateException{
			
			//代码输出目录
			String output_path = cfg.getOutput_path();
			String output_package = cfg.getBasePackage();
				   output_package = output_package.replace(".", "/");
			String projectpath_temp = output_path.replace("\\", "/");
			//真实的代码生成路径，生成代码打开这个路径，复制文件即可
			String code_package_path  = projectpath_temp+"/"+output_package;
			//创建文件夹
			//FileTools.createDir(code_package_path);
			//实体名称
			String entityName = Tools.toUpperCaseFirstOne(Tools.repTableNameToJavaName(cfg.getTable_name()));
			if(Tools.isNotNull(cfg.getEntity_name())){
				entityName=Tools.toUpperCaseFirstOne(cfg.getEntity_name());
			}
			Frameworkcfg cg = new Frameworkcfg();
			//根据配置获取table信息
			List<GciTable> list = GciPropertyTable.getTableInfoByName(schema, cfg.getTable_name());
			List<GciTable> PKlist = GciPropertyTable.getTablesPKColumnByName(schema, cfg.getTable_name());
			System.out.println("数据库中是否有对应表信息:"+PKlist.size()+"--->schema:"+schema+"--->tablename:"+cfg.getTable_name());
			GciTable pkobj = PKlist.get(0);
			String pk_type = "Integer";
			String pk_obj_type = "VARCHAR";
			if(pkobj!=null){
				System.out.println("主键类型="+pkobj.getData_type());
				if("int".equals(pkobj.getData_type())){
					pk_type = "Integer";
					pk_obj_type = "Integer";
				}else if("bigint".equals(pkobj.getData_type())){
					pk_type = "Long";
					pk_obj_type = "bigint";
				}else if("varchar".equals(pkobj.getData_type())){
					pk_type = "String";
					pk_obj_type = "VARCHAR";
				}else{
					pk_type = "String";
					pk_obj_type = "VARCHAR";
				}
			}
			GciTable  table = null;
			//判断项目类型
			if(FrameworkType.ssm.equals(cfg.getFramework_type())){
				//System.out.println("项目类型"+cfg.getFramework_type());
				if(list.size()>0){
					//生成web先
					cg.setPath(code_package_path+"/controller"+"/"+cfg.getBizPackage());
					cg.setTemplete_path("web\\springmvc");
					String springMvcController_File= code_package_path+"/controller"+"/"+cfg.getBizPackage()+"/"+entityName+"Controller.java";
					String springMvcController_REST= code_package_path+"/controller"+"/"+cfg.getBizPackage()+"/"+entityName+"RestApiController.java";
					
					//设置数据
					Map<String, Object>  m = new HashMap<String, Object>();
					GciTempData temp  = new GciTempData();
					temp.setBasePackage(cfg.getBasePackage());
					//实体包
					temp.setEntityPackage(entityName.toLowerCase());
					//业务包
					temp.setBizPackage(cfg.getBizPackage());
					//类的名称，支持用户自定义，如果默认则系统生成
					temp.setClassName(entityName);
					//组装mvc的时候将首字母小写成为controller
					temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
					//模块/表/实体_crud.jsp
					temp.setTable_name(cfg.getTable_name());
					temp.setGci_columns(list);
					//获取表名称
					temp.setCode_name(cfg.getCode_name());
					m.put("p", temp);
					cg.setDatas(m);
					//如果只生成rest api
					if(cfg.isRestApiController()){
						cg.setFilename(springMvcController_REST);
						log.info("生成restapi");
						CodeFactory.genAutoCodeRESTAPIByTempleteName(cg, "RestControllerTemplate.ftl");
						log.info("生成dto:"+entityName);
						genAutoEntityDtoCode(cfg, code_package_path, entityName,list);
					}else{
						cg.setFilename(springMvcController_File);
						log.info("生成普通controller");
						CodeFactory.genAutoCodeByTempleteName(cg, "ControllerTemplate.ftl");
						
						log.info("生成jsp:"+entityName);
						genAutoSpringMvcListJspCode(cfg, code_package_path, entityName, list, pk_type,pkobj);
						genAutoSpringMvcListJspAddCode(cfg, code_package_path, entityName, list, pk_type,pkobj);
						genAutoSpringMvcJspeditCode(cfg, code_package_path, entityName, list, pk_type,pkobj);
						genAutoSpringMvcJspViewCode(cfg, code_package_path, entityName, list, pk_type,pkobj);
					}
					
					
					
					log.info("生成实体:"+entityName);
					genAutoEntityCode(cfg, code_package_path, entityName,list);
					genAutoEntityQueryCode(cfg, code_package_path, entityName,list);
					log.info("生成service:"+entityName);
					genAutoServiceCode(cfg, code_package_path, entityName,pk_type);
					log.info("生成serviceImpl:"+entityName);
					genAutoServiceImplCode(cfg, code_package_path, entityName, pk_type,pkobj);
					log.info("生成dao:"+entityName);
					genAutoDaoCode(cfg, code_package_path, entityName, pk_type);
					log.info("生成mapping:"+entityName);
					genAutoDaoImplCode(cfg, code_package_path, entityName, list,pk_type,pk_obj_type,pkobj);
					
					
					if(SpringCfg.SRPING_TEST_ENABLED.equals(cfg.getSpring_junit_test())){
						log.info("生成springtest:"+entityName+"Servicetest");
						genAutoSpringJuntiTestCode(cfg, code_package_path, entityName, list, pk_type);
					}else{
						log.info("不启用spring-junti-test的自动生成");
					}
					
				}else{
					log.error("检查/config.properties中的schema和JDBC连接是否正确，当前找不到表!");
				}
				
			}else if(FrameworkType.ssh2.equals(cfg.getFramework_type())){
				
			}
			//构建输出的基础路径
			
		}
		
		private static void genAutoEntityDtoCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/dto");
			cg.setTemplete_path("entity");
			String entity_File= code_package_path+"/dto/"+entityName+"Dto.java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "DtoTemplate.ftl");
			
		}

		private static void genAutoCodeRESTAPIByTempleteName(Frameworkcfg cg,String templeteName) throws IOException, TemplateException {
			log.info("生成器开始生产"+templeteName);
			//获取模板路径
			String temppath = System.getProperty("user.dir")+"\\src\\templete\\"+cg.getTemplete_path();
			
			Configuration cfg = new Configuration();
			File f = new File(temppath);
			cfg.setDirectoryForTemplateLoading(f);
			
			Writer writer  = new StringWriter();
			//加载模板
			Template temp = cfg.getTemplate(templeteName);
			//设置模板+数据
			temp.process(cg.getDatas(), writer);
			//生成
			String st = writer.toString();
		    FileTools.createDir(cg.getPath());
		    //写文件数据
		    FileTools.printFile(cg.getFilename(), st);
		    
			writer.close();
			log.info("生成器结束生成"+templeteName);
			
		}

		/**
		 * 生成实体方法
		 * @throws TemplateException 
		 * @throws IOException 
		 */
		public static void genAutoEntityCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/entity"+"/"+cfg.getBizPackage());
			cg.setTemplete_path("entity");
			String entity_File= code_package_path+"/entity"+"/"+cfg.getBizPackage()+"/"+entityName+".java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "EntityTemplate.ftl");
		}
		
		/**
		 * 生成查询实体方法
		 * @throws TemplateException 
		 * @throws IOException 
		 */
		public static void genAutoEntityQueryCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/query"+"/"+cfg.getBizPackage());
			cg.setTemplete_path("query");
			String entity_File= code_package_path+"/query"+"/"+cfg.getBizPackage()+"/"+entityName+"Query.java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "QueryTemplate.ftl");
		}
		
		/**
		 * 生成service方法
		 * @throws TemplateException 
		 * @throws IOException 
		 */
		public static void genAutoServiceCode(CodeCfg cfg,String code_package_path,String entityName,String pk_type) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/service"+"/"+cfg.getBizPackage());
			cg.setTemplete_path("service\\mybatis");
			String entity_File= code_package_path+"/service"+"/"+cfg.getBizPackage()+"/"+entityName+"Service.java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			//主键类型
			temp.setPk_type(pk_type);
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "ServiceTemplate.ftl");
		}
		
		/**
		 * 生成serviceImpl方法
		 * @throws TemplateException 
		 * @throws IOException 
		 */
		public static void genAutoServiceImplCode(CodeCfg cfg,String code_package_path,String entityName,String pk_type,GciTable pkobj) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/service"+"/"+cfg.getBizPackage()+"/impl");
			cg.setTemplete_path("service\\mybatis\\impl");
			String entity_File= code_package_path+"/service"+"/"+cfg.getBizPackage()+"/impl/"+entityName+"ServiceImpl.java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			//主键类型
			temp.setPk_type(pk_type);
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			//设置主键名称
			temp.setPk_name(Tools.toUpperCaseFirstOne(pkobj.getClumn_name()));
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "ServiceImplTemplate.ftl");
		}
		
		
		
		/**
		 * 生成Dao方法
		 * @throws TemplateException 
		 * @throws IOException 
		 */
		public static void genAutoDaoCode(CodeCfg cfg,String code_package_path,String entityName,String pk_type) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/dao"+"/"+cfg.getBizPackage());
			cg.setTemplete_path("dao\\mybatis");
			String entity_File= code_package_path+"/dao"+"/"+cfg.getBizPackage()+"/"+entityName+"Dao.java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			//主键类型
			temp.setPk_type(pk_type);
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "DaoTemplate.ftl");
		}
		/**
		 * 生成mybatis的实体Mapper.xml
		 * @throws TemplateException 
		 * @throws IOException 
		 */
		public static void genAutoDaoImplCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns,String pk_type,String pk_obj_type,GciTable pkobj) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/mapping"+"/"+cfg.getBizPackage());
			cg.setTemplete_path("mapping");
			String entity_File= code_package_path+"/mapping"+"/"+cfg.getBizPackage()+"/"+entityName+"Mapper.xml";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			temp.setPk_type(pk_type);
			temp.setPk_obj_type(pk_obj_type);
			//主键名称
			temp.setPk_name(pkobj.getClumn_name());
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "MapperTemplate.xml");
		}
		/**
		 * 生成junit测试文件
		 * @param cfg
		 * @param code_package_path
		 * @param entityName
		 * @param columns
		 * @param pk_type
		 * @throws IOException
		 * @throws TemplateException
		 */
		public static void genAutoSpringJuntiTestCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns,String pk_type) throws IOException, TemplateException{
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/service/"+cfg.getBizPackage());
			cg.setTemplete_path("test\\junit");
			String entity_File= code_package_path+"/service"+"/"+cfg.getBizPackage()+"/"+entityName+"ServiceTest.java";
			cg.setFilename(entity_File);
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			temp.setPk_type(pk_type);
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "ServiceJunitTemplate.ftl");
		}
		
		/**
		 * 生成list_jsp测试文件
		 * @param cfg
		 * @param code_package_path
		 * @param entityName
		 * @param columns
		 * @param pk_type
		 * @throws IOException
		 * @throws TemplateException
		 */
		public static void genAutoSpringMvcListJspCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns,String pk_type,GciTable pkobj) throws IOException, TemplateException{
			///${p.bizPackage}/${p.entityPackage}/${p.lowerName}_list
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase());
			if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV1)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui1");
			}else if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV3)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui3");
			}
			//增加对版本的控制
			//cg.setTemplete_path("web\\springmvc\\jsp");
			String entity_File=code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase()+"/"+entityName.toLowerCase()+"_list.jsp";
			cg.setFilename(entity_File.toLowerCase());
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			//表的所有字段
			for(GciTable col:columns){
				System.out.println("校验类型="+col.getDataType()+","+col.getClumn_name()+","+col.getData_type());
			}
			temp.setGci_columns(columns);
			temp.setPk_type(pk_type);
			//主键名称
			temp.setPk_name(pkobj.getClumn_name());
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "springmvcTemplate_list.ftl");
		}
		
		//新增页面
		public static void genAutoSpringMvcListJspAddCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns,String pk_type,GciTable pkobj) throws IOException, TemplateException{
			///${p.bizPackage}/${p.entityPackage}/${p.lowerName}_list
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase());
			if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV1)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui1");
			}else if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV3)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui3");
			}
			
			String entity_File=code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase()+"/"+entityName.toLowerCase()+"_add.jsp";
			cg.setFilename(entity_File.toLowerCase());
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			temp.setPk_type(pk_type);
			//主键名称
			temp.setPk_name(pkobj.getClumn_name());
			temp.setCode_name(cfg.getCode_name());
			//获取表名称
			temp.setCode_name(cfg.getCode_name());
			m.put("p", temp);
			
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "springmvcTemplate_add.ftl");
			
		}
		
		//修改页面
		public static void genAutoSpringMvcJspeditCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns,String pk_type,GciTable pkobj) throws IOException, TemplateException{
			///${p.bizPackage}/${p.entityPackage}/${p.lowerName}_list
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase());
			if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV1)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui1");
			}else if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV3)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui3");
			}
			String entity_File=code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase()+"/"+entityName.toLowerCase()+"_edit.jsp";
			cg.setFilename(entity_File.toLowerCase());
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			temp.setPk_type(pk_type);
			//主键名称
			temp.setPk_name(pkobj.getClumn_name());
			temp.setCode_name(cfg.getCode_name());
			//获取表名称
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "springmvcTemplate_edit.ftl");
		}
		
		//查看页面
		public static void genAutoSpringMvcJspViewCode(CodeCfg cfg,String code_package_path,String entityName,List<GciTable> columns,String pk_type,GciTable pkobj) throws IOException, TemplateException{
			///${p.bizPackage}/${p.entityPackage}/${p.lowerName}_list
			Frameworkcfg cg = new Frameworkcfg();
			cg.setPath(code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase());
			if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV1)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui1");
			}else if(cfg.getJspVsersion().equals(SpringCfg.JSP_VERSION_QUIV3)){
				cg.setTemplete_path("web\\springmvc\\jsp\\qui3");
			}
			String entity_File=code_package_path+"/jsp"+"/"+cfg.getBizPackage()+"/"+entityName.toLowerCase()+"/"+entityName.toLowerCase()+"_view.jsp";
			cg.setFilename(entity_File.toLowerCase());
			//设置数据
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage(cfg.getBasePackage());
			//实体包
			temp.setEntityPackage(entityName.toLowerCase());
			//业务包
			temp.setBizPackage(cfg.getBizPackage());
			//类的名称，支持用户自定义，如果默认则系统生成
			temp.setClassName(entityName);
			//组装mvc的时候将首字母小写成为controller
			temp.setLowerName(Tools.toLowerCaseFirstOne(entityName));
			//模块/表/实体_crud.jsp
			temp.setTable_name(cfg.getTable_name());
			temp.setGci_columns(columns);
			temp.setPk_type(pk_type);
			//主键名称
			temp.setPk_name(pkobj.getClumn_name());
			temp.setCode_name(cfg.getCode_name());
			
			m.put("p", temp);
			cg.setDatas(m);
			CodeFactory.genAutoCodeByTempleteName(cg, "springmvcTemplate_view.ftl");
		}
		/**
		 * 生成代码-工具方法
		 * @param cg
		 * @param templeteName
		 * @throws IOException
		 * @throws TemplateException
		 */
		public static void genAutoCodeByTempleteName(Frameworkcfg cg,String templeteName) throws IOException, TemplateException{
			log.info("生成器开始生产"+templeteName);
			//获取模板路径
			String temppath = System.getProperty("user.dir")+"\\src\\templete\\"+cg.getTemplete_path();
			
			Configuration cfg = new Configuration();
			File f = new File(temppath);
			cfg.setDirectoryForTemplateLoading(f);
			
			Writer writer  = new StringWriter();
			//加载模板
			Template temp = cfg.getTemplate(templeteName);
			//设置模板+数据
			temp.process(cg.getDatas(), writer);
			//生成
			String st = writer.toString();
		    FileTools.createDir(cg.getPath());
		    //写文件数据
		    FileTools.printFile(cg.getFilename(), st);
		    
			writer.close();
			log.info("生成器结束生成"+templeteName);
		}
		public static void main(String[] args) throws IOException, TemplateException {
			Frameworkcfg  cg  = new Frameworkcfg();
			cg.setPath("D:/generator-output/gci/com/jiesai/framework/");
			cg.setFilename("D:/generator-output/gci/com/jiesai/framework/"+"IemsuserController.java");
			cg.setTemplete_path("web\\springmvc");
			Map<String, Object>  m = new HashMap<String, Object>();
			GciTempData temp  = new GciTempData();
			temp.setBasePackage("com.jiesai.framework");
			temp.setEntityPackage("iemsuser");
			temp.setClassName("Iemsuser");
			temp.setLowerName("iemsuser");
			m.put("p", temp);
			cg.setDatas(m);
			//CodeFactory.genAutoCodeByTempleteName(cg, "ControllerTemplate.ftl");
			
			//cg.setTemplete_path("web\\springmvc");
			//GciCode.genAutoCode(cg);
//			try {
//				List<GciTable> tables = GciPropertyTable.getTablesColumsByName("test", "iems_user", "id");
//				GciTable t = tables.get(0);
//				System.out.println(t.getData_type());
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
}
