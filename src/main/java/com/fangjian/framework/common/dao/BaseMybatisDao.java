package com.fangjian.framework.common.dao;

import java.io.Serializable;
import java.util.List;
/**
 * mybatis 通用DAO
 * @author fangjian
 * @param <T>  实体model
 * @param <PK> 主键
 */
public interface BaseMybatisDao<T,PK extends Serializable> {
	/**
	 * 根据ID获得对象
	 * @param id
	 * @return
	 */
	public T selectByPrimaryKey(PK id);
	
	/**
	 * 根据ID删除对象
	 * @param id
	 */
	public void deleteByPrimaryKey(PK id);
	
	/**
	 * 保存对象
	 * @param model
	 */
	public void insert(T model);
	
	/**
	 * 保存对象并且返回主键ID
	 * @param model
	 */
	public int insertReturnIdVlues(T model);
	/**
	 * 根据对象内容保存
	 * @param model
	 */
	public void insertSelective(T model);
	
	/**
	 * 根据对象要更新的值更新
	 * @param model
	 */
	public void updateByPrimaryKeySelective(T model);
	
	/**
	 * 更新对象
	 * @param model
	 */
	public void updateByPrimaryKey(T model);
	
	
	/**
	 * 获取该表所有数据
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 删除表所有数据
	 * @return
	 */
	public void deleteAll();
}
