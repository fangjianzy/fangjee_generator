package com.fangjian.framework.common.service;
import com.github.pagehelper.PageInfo;
/**
 * 分页
 * @author fangjian
 * @date  2017-8-1
 * @param <T>新版分页
 */
public abstract class BasePagehelperService <T>{
	/**
	 * 根据分页查询实体查询分页对象
	 * @param t 查询实体
	 * @param pageNo 分页数
	 * @param pageSize 分页大小
	 * @return 分页对象
	 */
	public abstract PageInfo<T> getPagehelPageList(T t, Integer pageNum, Integer pageSize);
}
