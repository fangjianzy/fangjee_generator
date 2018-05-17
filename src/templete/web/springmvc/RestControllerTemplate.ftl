package ${p.basePackage}.web;

import java.util.List;
import com.anfachina.common.dto.HttpResultVo;
import com.anfachina.uaa.SpringSecurityUtil;
import com.fangjian.framework.common.web.BaseMybatisController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${p.basePackage}.dto.${p.className}Dto;
import ${p.basePackage}.entity.${p.bizPackage}.${p.className};
import ${p.basePackage}.service.${p.bizPackage}.${p.className}Service;

import com.github.pagehelper.PageInfo;
/**
 * 
 * 描述:${p.code_name}代码生成器</br>
 * 功能：</b>${p.code_name}REST api相关功能</br>
 * 作者：</b>jimmy.fang</br>
 * 时间:${.now}</br>
 */
@RestController
@RequestMapping("/${p.bizPackage}/${p.entityPackage}/v1")
@Api(value = "/${p.bizPackage}/${p.entityPackage}/v1", tags = "${p.code_name}")
public class ${p.className}RestApiController extends BaseMybatisController{
	
	private final static Logger log= Logger.getLogger(${p.className}RestApiController.class);
	
	// 基于注解注入${p.code_name}服务service
	@Autowired(required=false)
	private ${p.className}Service ${p.lowerName}Service; 
	
	
	/**
	 * 获取，可以传分页参数
	 * @param pageNum
	 * @param pageSize
	 * @return HttpResultVo
	 */
	@ApiOperation(value = "查询${p.code_name}分页信息", notes = "查询${p.code_name}分页信息，pageNum表示第几页，pageSize表示每页显示多少条数据")
	@GetMapping(value = "/")
	public HttpResultVo getLists(
			@RequestParam(defaultValue = "1", required = true) int pageNum,
			@RequestParam(defaultValue = "10", required = true) int pageSize) {
		// 还可以通过@RequestParam传递参数来进行查询条件或者翻页信息的传递
		log.info("list init...");
		HttpResultVo vo = new HttpResultVo();
		PageInfo<${p.className}> pageData = this.${p.lowerName}Service.getLists(pageNum, pageSize);
		vo.setData(pageData);
		vo = success();
		return vo;
	}

	/**
	 * 新增
	 * 
	 * @param ${p.className}Dto
	 * @return HttpResultVo
	 */
	@ApiOperation(value = "插入${p.code_name}信息", notes = "插入${p.code_name}信息")
	@PostMapping(value = "/")
	public HttpResultVo createDto(@RequestBody ${p.className}Dto dto) {
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		HttpResultVo vo = new HttpResultVo();
		${p.className} po = new ${p.className}();
		BeanUtils.copyProperties(dto, po);
		int pkid = this.${p.lowerName}Service.insertReturnIdVlues(po);
		if(pkid >0){
			vo = success();
		}else{
			vo = fail("插入失败");
		}
		return vo;
	}

	/**
	 * 根据ID获取
	 * 
	 * @param id
	 * @return ${p.className}Dto
	 */
	@ApiOperation(value = "根据ID查询${p.code_name}信息", notes = "根据ID查询${p.code_name}信息")
	@GetMapping(value = "/{id}")
	public HttpResultVo getDtoById(@PathVariable("id") Integer id) {
		log.info("进入到getDtoById");
		HttpResultVo vo = new HttpResultVo();
		${p.className}Dto dto = new ${p.className}Dto();
		${p.className} po = this.${p.lowerName}Service.selectByPrimaryKey(id);
		if(po != null){
			BeanUtils.copyProperties(po, dto);
			vo.setData(dto);
		}else{
			vo = success();
		}
		return vo;
	}

	/**
	 * 根据ID和对象更新ID对应对象
	 * 
	 * @param id
	 * @param ${p.className}Dto
	 * @return
	 */
	@ApiOperation(value = "根据ID修改${p.code_name}信息", notes = "根据ID修改${p.code_name}信息")
	@PutMapping(value = "/{id}")
	public HttpResultVo putDtoById(@PathVariable("id") Integer id,@RequestBody ${p.className}Dto dto) {
	    log.info("进入到putDtoById");
		HttpResultVo vo = new HttpResultVo();
		${p.className} po = this.${p.lowerName}Service.selectByPrimaryKey(id);
		if(po != null){
			<#list p.gci_columns as u>
			<#if (u.clumn_name=='id')> 
			//po.set${u.javaColumnGetSetFileNameCode}(dto.get${u.javaColumnGetSetFileNameCode}());
			<#else>
			po.set${u.javaColumnGetSetFileNameCode}(dto.get${u.javaColumnGetSetFileNameCode}());
			</#if>
			</#list>
			int rownum = this.${p.lowerName}Service.updateEntryByPrimaryKey(po);
			if(rownum >0){
				vo = success();
			}else{
				vo = fail("对象更新过程中失败");
			}
		}else{
			vo = fail("未找到该ID对象，请核对更新对象ID是否存在");
		}
		return vo;
	}

	/**
	 * 根据ID删除
	 * @param id
	 * @return HttpResultVo
	 */
	@ApiOperation(value = "根据ID删除${p.code_name}信息", notes = "根据ID删除${p.code_name}信息")
	@DeleteMapping(value = "/{id}")
	public HttpResultVo deleteDto(@PathVariable("id") Integer id) {
		HttpResultVo vo = new HttpResultVo();
		int delrownum = this.${p.lowerName}Service.deleteEntryByPrimaryKey(id);
		if(delrownum >0){
			vo = success();
		}else{
			vo = fail("删除失败或者id数据库无对应记录");
		}
		return vo;
	}
}
