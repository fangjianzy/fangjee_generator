package com.fangjian.framework.common;

import java.io.Serializable;

/**
 * PageInfo分页实体</br>
 * 2016年1月18日 上午9:29:39
 * @version 1.0.0
 */
public class PageInfo implements java.io.Serializable {

    /**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 总条数
     */
    private int totalCount = 0;

    /**
     * 当前页
     */
    private int currentPage = 0;
    /**
     * 每页数据条数
     */
    private int pageSize = 10;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    
    public int getTotalCount() {
        return totalCount;
    }

    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return (totalCount - 1) / pageSize + 1;
    }
}

