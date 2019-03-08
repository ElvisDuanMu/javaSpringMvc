package com.dlu.mapper;

import com.dlu.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryMapper {


    List<DataDictionary> queryAllAppStatus();

    List<DataDictionary> queryAllAppFlatForm();
}