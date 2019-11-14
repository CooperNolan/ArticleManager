package com.cooper.articlemanagement.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cooper.articlemanagement.dao.ArticleDao;
import com.cooper.articlemanagement.dao.CategoryDao;
import com.cooper.articlemanagement.entity.Category;
import com.cooper.articlemanagement.myexception.MyRuntimeException;
import com.cooper.articlemanagement.service.CategoryService;
import com.cooper.articlemanagement.util.ConfigUtil;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;

    @Autowired
    @Qualifier("articleDao")
    ArticleDao articleDao;

    /**
     * 根据ID查询分类
     * 
     * @param categoryId
     * @return
     */
    @Override
    public Category selectByCategoryId(Integer categoryId) {
        return categoryDao.selectById(categoryId);
    }

    /**
     * 根据分类状态查询分类
     * 
     * @return
     */
    @Override
    public List<Category> selectByCategoryStatus(Integer categoryStatus) {
        return categoryDao.selectByStatus(categoryStatus);
    }

    /**
     * 查询状态为0的分类存入内存
     */
    @Override
    public void selectByZeroStatusCache() {
        List<Category> categoryList = selectByCategoryStatus(0);
        Map<Integer, String> categoryIdAndNameMap = new LinkedHashMap<>();
        categoryList
            .forEach(category -> categoryIdAndNameMap.put(category.getCategoryId(), category.getCategoryName()));
        ConfigUtil.setCategoryIdAndNameMap(categoryIdAndNameMap);
    }

    /**
     * 根据分类名称搜索分类
     * 
     * @param categoryName
     * @return
     */
    @Override
    public List<Category> selectByCategoryName(String categoryName) {
        return categoryDao.selectByName(categoryName);
    }

    /**
     * 添加分类
     * 
     * @param category
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int insert(Category category) throws MyRuntimeException {
        category.setCategoryDate(new Date());
        if (category.getCategoryStatus() == null) {
            category.setCategoryStatus(0);
        }
        int influences = 0;
        try {
            influences = categoryDao.insert(category);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        if (category.getCategoryStatus() == 0) {
            ConfigUtil.getCategoryIdAndNameMap().put(category.getCategoryId(), category.getCategoryName());
        }
        return influences;
    }

    /**
     * 修改分类
     * 
     * @param category
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int update(Category category) throws MyRuntimeException {
        int influences = 0;
        try {
            influences = categoryDao.update(category);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        Map<Integer, String> categoryIdAndNameMap = ConfigUtil.getCategoryIdAndNameMap();
        if (categoryIdAndNameMap.containsKey(category.getCategoryId())) {
            categoryIdAndNameMap.remove(category.getCategoryId());
        }
        if (category.getCategoryStatus() == 0) {
            categoryIdAndNameMap.put(category.getCategoryId(), category.getCategoryName());
        }
        return influences;
    }

    /**
     * 删除分类
     * 
     * @param categoryId
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int delete(Integer categoryId) throws MyRuntimeException {
        int influences = 0;
        try {
            articleDao.updateCategoryByDelete(categoryId, 1);
            influences = categoryDao.delete(categoryId);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        if (ConfigUtil.getCategoryIdAndNameMap().containsKey(categoryId)) {
            ConfigUtil.getCategoryIdAndNameMap().remove(categoryId);
        }
        return influences;
    }
}
