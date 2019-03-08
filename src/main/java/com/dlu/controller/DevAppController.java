package com.dlu.controller;


import com.dlu.dto.AppInfoDTO;
import com.dlu.pojo.AppCategory;
import com.dlu.pojo.AppInfo;
import com.dlu.pojo.DataDictionary;
import com.dlu.service.AppCategoryService;
import com.dlu.service.AppInfoService;
import com.dlu.service.DataDictionaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/app")
public class DevAppController {

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private AppCategoryService appCategoryService;

    @RequestMapping("/index/devId/{id}")
    public  String index(@PathVariable("id")Long id, Model model, HttpSession httpSession){
        System.out.println(id);
        PageInfo<AppInfo> pageInfo =new PageInfo<>();
        pageInfo.setPageSize(5);
        pageInfo.setPageNum(1);
        PageInfo pageInfo1 = appInfoService.queryByDevUserId(id,pageInfo);
        model.addAttribute("page",pageInfo1);
        httpSession.setAttribute("USER_ID",id);
        //查询所有状态
        List<DataDictionary> appStatuses = appInfoService.queryAllAppStatus();
        model.addAttribute("appStatus",appStatuses);
        //查询所有平台
        List<DataDictionary> appFlatforms = appInfoService.queryAllAppFlatForm();
        model.addAttribute("appFlatform",appFlatforms);
        //查询所有的一级分类
        List<AppCategory> appCategoriesOne = appCategoryService.queryAllLevelOne();
        model.addAttribute("levelOne",appCategoriesOne);

        return "app/index";

    }

    @RequestMapping("/{id}/query")
    public String query(@PathVariable("id") Long userId, AppInfoDTO appInfoDTO,Model model){
       // Object user_id = httpSession.getAttribute("USER_ID");
        if (userId !=null){
            //Long userId = (Long) user_id;
            appInfoDTO.setDevUserId(userId);
            PageInfo<AppInfo> appInfoDTOPageInfo = appInfoService.query(appInfoDTO);
            model.addAttribute("page",appInfoDTOPageInfo);
            //查询所有状态
            List<DataDictionary> appStatuses = appInfoService.queryAllAppStatus();
            model.addAttribute("appStatus",appStatuses);
            //查询所有平台
            List<DataDictionary> appFlatforms = appInfoService.queryAllAppFlatForm();
            model.addAttribute("appFlatform",appFlatforms);
            //查询所有的一级分类
            List<AppCategory> appCategoriesOne = appCategoryService.queryAllLevelOne();
            model.addAttribute("levelOne",appCategoriesOne);
            model.addAttribute("appInfoDTO",appInfoDTO);
            return "app/index";
        }
        else
            return "redirect:/";

    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        //查询所有平台
        List<DataDictionary> appFlatforms = appInfoService.queryAllAppFlatForm();
        model.addAttribute("appFlatform",appFlatforms);
        //查询所有的一级分类
        List<AppCategory> appCategoriesOne = appCategoryService.queryAllLevelOne();
        model.addAttribute("levelOne",appCategoriesOne);
        return "app/add";
    }

    @RequestMapping("/add")
    public String add(AppInfo appInfo,HttpSession session){
        Object user_id = session.getAttribute("USER_ID");
        long userId = Long.parseLong(user_id.toString());
        boolean flag = appInfoService.add(appInfo,userId);
        return "redirect:/app/index/devId/"+userId;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id, HttpSession session){
        Object user_id = session.getAttribute("USER_ID");
        long userId = Long.parseLong(user_id.toString());
        boolean flag = appInfoService.delete(id);
        return "redirect:/app/index/devId/"+userId;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id")Long id, Model model,AppInfoDTO appInfoDTO){
        //查询所有状态
        List<DataDictionary> appStatuses = appInfoService.queryAllAppStatus();
        model.addAttribute("appStatus",appStatuses);
        //查询所有平台
        List<DataDictionary> appFlatforms = appInfoService.queryAllAppFlatForm();
        model.addAttribute("appFlatform",appFlatforms);
        //查询所有的一级分类
        List<AppCategory> appCategoriesOne = appCategoryService.queryAllLevelOne();
        model.addAttribute("levelOne",appCategoriesOne);
        AppInfo appInfo = appInfoService.queryById(id);
        model.addAttribute("app",appInfo);
        model.addAttribute("appInfoDTO",appInfoDTO);
        return "app/edit";
    }

    @RequestMapping("/edit/")
    public String edit(AppInfo appInfo, Model model){
        return null;
    }

}
