package com.baoke.admin.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Max
 * 分页辅助工具
 */
public class PageBean<T>  implements Serializable{
    
	private static final long serialVersionUID = 8264603761347852319L;
	private int currentPage = 1;// 当前页数
    private int totalPages = 0;// 总页数
    private int pageSize = 0;// 每页显示数
    private int totalRows = 0;// 总数据数
    private int startNum = 0;// 开始记录
    private int endNum = pageSize;//结束记录
    private int nextPage = 0;// 下一页
    private int previousPage = 0;// 上一页
    private boolean hasNextPage = false;// 是否有下一页
    private boolean hasPreviousPage = false;// 是否有前一页
    private List<T> list;
    private Map<String, Object> attribute = new HashMap<String, Object>();
    
    public void setAttribute(String key, Object value){
        attribute.put(key, value);
    }
    public Object getAttribute(String key){
        return attribute.get(key);
    }

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageBean(int pageSize, int currentPage, int totalRows) {

        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        
        //计算总页数
        if ((totalRows % pageSize) == 0) {
             totalPages = totalRows / pageSize;
         } else {
             totalPages = totalRows / pageSize + 1;
         }

        //是否有下一页
        if (currentPage >= totalPages) {
             hasNextPage = false;
             currentPage = totalPages;
         } else {
             hasNextPage = true;
         }
        
        //是否有上一页
        if (currentPage <= 1) {
             hasPreviousPage = false;
             currentPage = 1;
         } else {
             hasPreviousPage = true;
         }
        
        startNum = (currentPage - 1) * pageSize;//开始记录数
        
        if(totalPages-currentPage<1){
        	endNum = totalRows;
        }else{
        	endNum = currentPage*pageSize;
        }
         

         nextPage = currentPage + 1;//下一页页码

         previousPage = currentPage - 1;//上一页页码
        
        if (nextPage >= totalPages) {
             nextPage = totalPages;
         }

        if (previousPage <= 1) {
             previousPage = 1;
         }

     }
    
    public PageBean() {
        this.pageSize = 0;
        this.currentPage = 0;
        this.totalRows = 0;
        list = new ArrayList<T>();
    }

    public void refresh(int currentPage) {
        this.currentPage = currentPage;
        startNum = (currentPage - 1) * pageSize;
        if (totalPages - currentPage < 1) {
            endNum = totalRows;
        } else {
            endNum = currentPage * pageSize;
        }
    }

    public boolean isHasNextPage() {

        return hasNextPage;

     }

    public boolean isHasPreviousPage() {

        return hasPreviousPage;

     }

    /**
      * @return the nextPage
      */
    public int getNextPage() {
        return nextPage;
     }

    /**
      * @param nextPage
      *             the nextPage to set
      */
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
     }

    /**
      * @return the previousPage
      */
    public int getPreviousPage() {
        return previousPage;
     }

    /**
      * @param previousPage
      *             the previousPage to set
      */
    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
     }

    /**
      * @return the currentPage
      */
    public int getCurrentPage() {
        return currentPage;
     }

    /**
      * @param currentPage
      *             the currentPage to set
      */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
     }

    /**
      * @return the pageSize
      */
    public int getPageSize() {
        return pageSize;
     }

    /**
      * @param pageSize
      *             the pageSize to set
      */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
     }

    /**
      * @return the totalPages
      */
    public int getTotalPages() {
        return totalPages;
     }

    /**
      * @param totalPages
      *             the totalPages to set
      */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
     }

    /**
      * @return the totalRows
      */
    public int getTotalRows() {
        return totalRows;
     }

    /**
      * @param totalRows
      *             the totalRows to set
      */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
     }

    /**
      * @param hasNextPage
      *             the hasNextPage to set
      */
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
     }

    /**
      * @param hasPreviousPage
      *             the hasPreviousPage to set
      */
    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
     }

    /**
      * @return the startNum
      */
    public int getStartNum() {
        return startNum;
     }

    /**
      * @param startNum
      *             the startNum to set
      */
    public void setStartNum(int startNum) {
        this.startNum = startNum;
     }

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	} 
	
	/** 根据当前页码和每一页显示的数据 计算当前查询开始下标 */
	public static int getStart(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
