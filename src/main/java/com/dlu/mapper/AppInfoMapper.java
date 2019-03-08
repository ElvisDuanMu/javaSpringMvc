package com.dlu.mapper;

import com.dlu.dto.AppInfoDTO;
import com.dlu.pojo.AppInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AppInfoMapper {

    List<AppInfo> queryByDevUserId(AppInfoDTO appInfoDTO);

    List<AppInfo> query(AppInfoDTO appInfoDTO);


    int add(AppInfo appInfo);


    int deleteById(@Param("id") Long id);

    AppInfo queryById(@Param("id") Long id);
}