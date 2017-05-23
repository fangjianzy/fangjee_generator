package ${p.basePackage}.controller.${p.bizPackage};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fangjian.framework.common.GlobalConstant;
import com.fangjian.framework.common.PageInfo;
import com.fangjian.framework.common.web.BaseMybatisController;
import ${p.basePackage}.query.${p.bizPackage}.${p.className}Query;
import ${p.basePackage}.entity.${p.bizPackage}.${p.className};
import ${p.basePackage}.service.${p.bizPackage}.${p.className}Service;
 
/**
 * 
 * 描述:${p.code_name}</br>
 * 功能：</b>${p.code_name}实体表mvc层相关功能</br>
 * 作者：</b>fangjian</br>
 * 时间:${.now}</br>
 */ 
@Controller
@RequestMapping("/${p.lowerName}Controller") 
public class ${p.className}Controller extends BaseMybatisController{
	
	private final static Logger log= Logger.getLogger(${p.className}Controller.class);
	
	// 基于注解注入${p.code_name}服务service
	@Autowired(required=false)
	private ${p.className}Service ${p.lowerName}Service; 
	
	
	/**
	 * 列表方法
	 * @param request
	 * @param response
	 * @param vo 查询对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(HttpServletRequest request,HttpServletResponse response, ${p.className}Query vo) throws Exception{
		log.info("获取${p.code_name}-分页数据");
		ModelAndView mv = new ModelAndView("/${p.bizPackage}/${p.entityPackage}/${p.lowerName}_list");
		int currentPage = 1;
		int pageSize = 10;
		if(request.getParameter(GlobalConstant.CURRENT_PAGE) != null){
			currentPage=Integer.parseInt(request.getParameter(GlobalConstant.CURRENT_PAGE));
		}
		if(request.getParameter(GlobalConstant.PAGE_SIZE) != null){
			pageSize=Integer.parseInt(request.getParameter(GlobalConstant.PAGE_SIZE));
		}		
		List<${p.className}> list = ${p.lowerName}Service.findPageListBySQL(currentPage, pageSize, vo);
		PageInfo pageInfo = ${p.lowerName}Service.findPageInfoBySQL(currentPage, pageSize,vo);
		
		
		request.setAttribute(GlobalConstant.ATTRIBUTE_LIST, list);
		request.setAttribute(GlobalConstant.PAGEINFO, pageInfo);
		request.setAttribute(GlobalConstant.ATTRIBUTE_VO, vo);
		
		return mv;
	}
	
	/**
	 * 跳转到新增页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAdd") 
	public ModelAndView  toAdd(HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.info("跳到新增页面-${p.code_name}");
		ModelAndView mv = new ModelAndView("/${p.bizPackage}/${p.entityPackage}/${p.lowerName}_add");
		
		return mv;
	}
	
	/**
	 * 新增方法
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add") 
	public ModelAndView  Add(HttpServletRequest request,HttpServletResponse response,@ModelAttribute ${p.className} po) throws Exception{
		log.info("新增方法-${p.code_name}");
		${p.className}Query vo = new ${p.className}Query();
		this.${p.lowerName}Service.insert(po);
		//return this.list(request, response, vo);
		return new ModelAndView("redirect:/${p.lowerName}Controller/list.do");
	}
	
	/**
	 * 根据ID删除对象
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deleteById")
	public ModelAndView deleteById(HttpServletRequest request,HttpServletResponse response,@ModelAttribute ${p.className}Query vo) throws Exception{
		log.info("根据ID删除数据-${p.code_name}");
		this.${p.lowerName}Service.deleteByPrimaryKey(vo.getId());
		${p.className}Query queryVo = new ${p.className}Query();
		//return this.list(request, response, queryVo);
		return new ModelAndView("redirect:/${p.lowerName}Controller/list.do");
	}
	
	/**
	 * 根据ID找到对象
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 */
	@RequestMapping("/findById")
	public ModelAndView findById(HttpServletRequest request,HttpServletResponse response,@ModelAttribute ${p.className}Query vo)throws Exception{
		log.info("根据ID查找数据-${p.code_name}");
		ModelAndView mv = new ModelAndView("/${p.bizPackage}/${p.entityPackage}/${p.lowerName}_view");
		${p.className} po = this.${p.lowerName}Service.selectByPrimaryKey(vo.getId());
		mv.addObject(GlobalConstant.ATTRIBUTE_VO, po);
		return mv;
	}
	
	/**
	 * 根据ID更新对象
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editById")
	public ModelAndView editById(HttpServletRequest request,HttpServletResponse response,@ModelAttribute ${p.className}Query vo)throws Exception{
		log.info("根据ID查找更新数据-${p.code_name}");
		ModelAndView mv = new ModelAndView("/${p.bizPackage}/${p.entityPackage}/${p.lowerName}_edit");
		${p.className} po = this.${p.lowerName}Service.selectByPrimaryKey(vo.getId());
		mv.addObject(GlobalConstant.ATTRIBUTE_VO, po);
		
		return mv;
	}
	
	
	/**
	 * 更新提交
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,@ModelAttribute ${p.className}Query vo)throws Exception{
		log.info("提交更新数据-${p.code_name}");
		${p.className} po = this.${p.lowerName}Service.selectByPrimaryKey(vo.getId());
		<#list p.gci_columns as u>
		<#if (u.clumn_name=='id')> 
		//po.set${u.column_getset}(vo.get${u.column_getset}());
		<#else>
		po.set${u.column_getset}(vo.get${u.column_getset}());
		</#if>
		</#list>
		this.${p.lowerName}Service.updateByPrimaryKeySelective(po);
		${p.className}Query queryVo = new ${p.className}Query();
		//return this.list(request, response, queryVo);
		return new ModelAndView("redirect:/${p.lowerName}Controller/list.do");
	}
	
	/**
	 * ResponseBody返回案例，具体业务实现根据实际情况而定，当前类作为模板
	 * AJAX 返会json乱码的解决办法 produces="text/plain;charset=UTF-8"
	 * @param request
	 * @param response
	 * @param 查询对象VO
	 * @return json对象
	 */
	@ResponseBody
	@RequestMapping(value="/AjaxRequest",produces="text/plain;charset=UTF-8")
	public Object AjaxRequest(HttpServletRequest request,HttpServletResponse response,@ModelAttribute ${p.className}Query vo)throws Exception {
		
		return vo;
	}
}
