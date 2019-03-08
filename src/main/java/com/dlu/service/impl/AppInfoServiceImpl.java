package com.dlu.service.impl;

import com.dlu.constant.CommonCodeConstant;
import com.dlu.dto.AppInfoDTO;
import com.dlu.mapper.AppInfoMapper;
import com.dlu.mapper.DataDictionaryMapper;
import com.dlu.pojo.AppInfo;
import com.dlu.pojo.DataDictionary;
import com.dlu.pojo.DevUser;
import com.dlu.service.AppInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("AppInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public PageInfo<AppInfo> queryByDevUserId(Long id,PageInfo pageInfo) {
        //去第几页，页码大小
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        AppInfoDTO appInfoDTO = new AppInfoDTO();
        appInfoDTO.setDevUserId(id);
        List<AppInfo> appInfos = appInfoMapper.queryByDevUserId(appInfoDTO);
        PageInfo<AppInfo> pageInfo1 = new PageInfo<AppInfo>(appInfos);
        return pageInfo1;
    }

    @Override
    public List<DataDictionary> queryAllAppStatus() {

        return dataDictionaryMapper.queryAllAppStatus();
    }

    @Override
    public List<DataDictionary> queryAllAppFlatForm() {
        return dataDictionaryMapper.queryAllAppFlatForm();
    }

    @Override
    public PageInfo<AppInfo> query(AppInfoDTO appInfoDTO) {

        PageHelper.startPage(appInfoDTO.getPageNum(), CommonCodeConstant.PAGE_SIZE);

        List<AppInfo> appInfoPageInfo = appInfoMapper.query(appInfoDTO);
        PageInfo<AppInfo> page = new PageInfo<AppInfo>(appInfoPageInfo);
        return page;
    }

    @Override
    public boolean add(AppInfo appInfo, long userId) {
        //1 处理信息
        appInfo.setCreationDate(new Date());
        DevUser devUser = new DevUser();
        devUser.setId(userId);
        appInfo.setDevUser(devUser);
        appInfo.setCreatedBy(userId);
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setValueId(CommonCodeConstant.APP_STATUS_AUDITED);
        appInfo.setAppStatus(dataDictionary);
        appInfo.setStatus(CommonCodeConstant.APP_STATUS_AUDITED);
        appInfo.setFlatformId(appInfo.getAppFlatform().getValueId());
        appInfo.setCategoryLevel1Id(appInfo.getCategoryLevel1().getId());
        appInfo.setCategoryLevel2Id(appInfo.getCategoryLevel2().getId());
        appInfo.setCategoryLevel3Id(appInfo.getCategoryLevel3().getId());
        int row = appInfoMapper.add(appInfo);
        return false ;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null){
            int row = appInfoMapper.deleteById(id);
            return row == 1? true:false;
        }
        return false;
    }

    @Override
    public AppInfo queryById(Long id) {
        return appInfoMapper.queryById(id);
    }


}
