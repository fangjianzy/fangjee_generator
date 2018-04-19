package ${p.basePackage}.service.${p.bizPackage}.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${p.basePackage}.service.${p.bizPackage}.${p.className}Service;
import ${p.basePackage}.dao.${p.bizPackage}.${p.className}Dao;
import ${p.basePackage}.entity.${p.bizPackage}.${p.className};
import ${p.basePackage}.query.${p.bizPackage}.${p.className}Query;
import com.fangjian.framework.common.PageInfo;
import com.fangjian.framework.common.util.UuidUtil;
/**
 * 
 * 描述:${p.code_name}接口具体实现</br>
 * 功能：${p.code_name}实体表接口实现</br>
 * 作者：方坚</br>
 * 时间:${.now}</br>
 */
@Transactional
@Service("${p.lowerName}Service")
public class ${p.className}ServiceImpl implements ${p.className}Service {
	
	private final static Logger log= Logger.getLogger(${p.className}ServiceImpl.class);
	
	@Autowired
	private ${p.className}Dao ${p.lowerName}dao;

	public ${p.className} selectByPrimaryKey(${p.pk_type} id) {
		
		return this.${p.lowerName}dao.selectByPrimaryKey(id);
	}

	public void deleteByPrimaryKey(${p.pk_type} id) {
		this.${p.lowerName}dao.deleteByPrimaryKey(id);
	}

	public void insert(${p.className} model) {
		<#if (p.pk_type=='int')> 
		this.${p.lowerName}dao.insert(model);
		<#elseif (p.pk_type=='Integer')>
		this.${p.lowerName}dao.insert(model);
		<#elseif (p.pk_type=='Long')>
		this.${p.lowerName}dao.insert(model);
		<#else>
		//主键为string类型，基于uuid工具类生成主键
		String id = UuidUtil.get32UUID();
		model.set${p.pk_name}(id);
		this.${p.lowerName}dao.insert(model);
		</#if>
	}

	public void insertSelective(${p.className} model) {
		<#if (p.pk_type=='int')> 
		this.${p.lowerName}dao.insertSelective(model);
		<#elseif (p.pk_type=='Integer')>
		this.${p.lowerName}dao.insertSelective(model);
		<#elseif (p.pk_type=='Long')>
		this.${p.lowerName}dao.insertSelective(model);
		<#else>
		//主键为string类型，基于uuid工具类生成主键
		String id = UuidUtil.get32UUID();
		model.set${p.pk_name}(id);
		this.${p.lowerName}dao.insertSelective(model);
		</#if>
	}
	
	/**
	* 2017-2-13 更新主键非int类型自动生成问题bug
	*/
	public ${p.pk_type} insertReturnIdVlues(${p.className} model) {
		<#if (p.pk_type=='int')> 
		//主键为int类型，按照mybais方式返回主键
		int id =0;
		log.info("数据保存之前ID"+ model.getId());
		this.${p.lowerName}dao.insertReturnIdVlues(model);
		id= model.getId();
		return id;
		<#elseif (p.pk_type=='Integer')>
		//主键为int类型，按照mybais方式返回主键
		int id =0;
		log.info("数据保存之前ID"+ model.getId());
		this.${p.lowerName}dao.insertReturnIdVlues(model);
		id= model.getId();
		return id;
		<#elseif (p.pk_type=='Long')>
		//主键为int类型，按照mybais方式返回主键
		long id =0;
		log.info("数据保存之前ID"+ model.getId());
		this.${p.lowerName}dao.insertReturnIdVlues(model);
		id= model.getId();
		return id;
		<#else>
		//主键为string类型，基于uuid工具类生成主键
		String id = UuidUtil.get32UUID();
		model.set${p.pk_name}(id);
		this.${p.lowerName}dao.insertSelective(model);
		return id;
		</#if>
	}
	
	public void updateByPrimaryKeySelective(${p.className} model) {
		this.${p.lowerName}dao.updateByPrimaryKeySelective(model);
	}

	public void updateByPrimaryKey(${p.className} model) {
		this.${p.lowerName}dao.updateByPrimaryKey(model);
	}
	
	public List<${p.className}> findAll() {
		return this.${p.lowerName}dao.findAll();
	}
	
	public void deleteAll() {
		this.${p.lowerName}dao.deleteAll();
	}
	
	public List<${p.className}> findPageListBySQL(int currentPage, int pageSize,${p.className}Query vo) {
		return this.${p.lowerName}dao.findPageListBySQL(currentPage, pageSize, vo);
	}

	public PageInfo findPageInfoBySQL(int currentPage, int pageSize,${p.className}Query vo) {
		return this.${p.lowerName}dao.findPageInfoBySQL(currentPage, pageSize, vo);
	}
	
	public Integer deleteEntryByPrimaryKey(Integer id) {
		return this.${p.lowerName}dao.deleteEntryByPrimaryKey(id);
	}

	public Integer updateEntryByPrimaryKey(${p.className} model) {
		return this.${p.lowerName}dao.updateEntryByPrimaryKey(model);
	}
}
