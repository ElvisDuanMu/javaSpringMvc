package com.dlu.mapper;

import com.dlu.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {



    List<AppCategory> queryAllLevelOne();

    List<AppCategory> queryLevelTwoByLevelOne(@Param("id") Long id);


    List<AppCategory> queryLevelThreeByLevelTwo(@Param("id") Long id);
}