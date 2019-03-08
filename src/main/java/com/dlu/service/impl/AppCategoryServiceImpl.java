package com.dlu.service.impl;

import com.dlu.mapper.AppCategoryMapper;
import com.dlu.pojo.AppCategory;
import com.dlu.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {

    @Autowired
    private AppCategoryMapper appCategoryMapper;

    @Override
    public List<AppCategory> queryAllLevelOne() {
        return appCategoryMapper. queryAllLevelOne();
    }

    @Override
    public List<AppCategory> queryLevelTwoByLevelOne(Long id) {
        return appCategoryMapper.queryLevelTwoByLevelOne(id);
    }

    @Override
    public List<AppCategory> queryLevelThreeByLevelTwo(Long id) {
        return appCategoryMapper.queryLevelThreeByLevelTwo(id);
    }
}
