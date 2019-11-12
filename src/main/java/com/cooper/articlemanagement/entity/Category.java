package com.cooper.articlemanagement.entity;

import java.util.Date;

public class Category {

    private Integer categoryId;// 分类编号
    private String categoryName;// 分类名字
    private Date categoryDate;// 分类创建日期
    private Integer categoryStatus;// 分类状态 0 正常 1 锁定

    public Category() {}

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate(Date categoryDate) {
        this.categoryDate = categoryDate;
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName='" + categoryName + '\'' + ", categoryDate="
            + categoryDate + ", categoryStatus=" + categoryStatus + '}';
    }
}
