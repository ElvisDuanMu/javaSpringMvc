package com.dlu.mapper;

import com.dlu.pojo.DevUser;

import java.util.List;

public interface DevUserMapper {

    List<DevUser> queryByNamePwd(DevUser devUser);
}