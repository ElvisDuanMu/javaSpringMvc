package com.dlu.service;

import com.dlu.dto.AppInfoDTO;
import com.dlu.pojo.AppInfo;
import com.dlu.pojo.DataDictionary;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppInfoService {
    PageInfo<AppInfo> queryByDevUserId(Long id,PageInfo pageInfo);

    List<DataDictionary> queryAllAppStatus();

    List<DataDictionary> queryAllAppFlatForm();

    PageInfo<AppInfo> query(AppInfoDTO appInfoDTO);

    boolean add(AppInfo appInfo, long userId);

    boolean delete(Long id);

    AppInfo queryById(Long id);
}
