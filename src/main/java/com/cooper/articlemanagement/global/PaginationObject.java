package com.cooper.articlemanagement.global;

import java.util.List;

import com.cooper.articlemanagement.util.ConfigUtil;

public class PaginationObject<T> {
    private Integer page;
    private Integer totalPage;
    private List<T> pageList;

    public PaginationObject() {}

    public PaginationObject(Integer page, Integer totalPage, List<T> pageList) {
        this.page = page;
        this.totalPage = totalPage;
        this.pageList = pageList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer tatolPage) {
        this.totalPage = tatolPage;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public static Integer getTotalPage(Integer totalNum) {
        if (totalNum % ConfigUtil.PAGE_NUM != 0) {
            return totalNum / ConfigUtil.PAGE_NUM + 1;
        } else {
            return totalNum / ConfigUtil.PAGE_NUM;
        }
    }
}
