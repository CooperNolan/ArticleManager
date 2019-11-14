package com.cooper.articlemanagement.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooper.articlemanagement.entity.Category;
import com.cooper.articlemanagement.service.CategoryService;
import com.cooper.articlemanagement.util.HttpUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;

@Controller("categoryController")
@RequestMapping(value = "Category")
public class CategoryController extends BaseModelAttribute {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    /**
     * 跳转分类管理页面
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String toCategoryMain(Map<String, Object> map) {
        String search = request.getParameter("search");
        List<Category> categoryList = null;
        if (search != null && !search.equals("")) {
            categoryList = categoryService.selectByCategoryName(search);
        } else {
            categoryList = categoryService.selectByCategoryStatus(null);
        }
        if (!categoryList.isEmpty() && categoryList.get(0).getCategoryId() == 1) {
            categoryList.remove(0);
        }
        map.put("categoryList", categoryList);
        return "category/category";
    }

    /**
     * 添加分类
     * 
     * @param category
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCategory(Category category) {
        category.setCategoryName(category.getCategoryName().trim());
        if (category.getCategoryName().equals("")) {
            return ResponseBodyUtil.responseBody(false, "Category Name is null!");
        }
        try {
            categoryService.insert(category);
        } catch (Exception e) {
            logger.error("{}({}) addCategory errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.responseBody(false, "Add error!");
        }
        return ResponseBodyUtil.responseBody(true, null);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modifyCategory(Category category) {
        if (category.getCategoryName().trim().equals("")) {
            return ResponseBodyUtil.responseBody(false, "Category Name is null!");
        }
        try {
            categoryService.update(category);
        } catch (Exception e) {
            logger.error("{}({}) modifyCategory errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.responseBody(false, "Modify error!");
        }
        return ResponseBodyUtil.responseBody(true, null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCategory(Integer categoryId) {
        try {
            categoryService.delete(categoryId);
        } catch (Exception e) {
            logger.error("{}({}) deleteCategory errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.responseBody(false, "Delete error!");
        }
        return ResponseBodyUtil.responseBody(true, null);
    }
}
