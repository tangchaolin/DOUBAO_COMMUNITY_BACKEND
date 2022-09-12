package com.tangchaolin.doubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.entity.BmsBillboard;
import com.tangchaolin.doubao.model.entity.BmsTip;
import com.tangchaolin.doubao.service.IBmsBillboardService;
import com.tangchaolin.doubao.service.IBmsTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController{
    @Autowired
    private IBmsTipService bmsTipService;
    @GetMapping("/today")
    public ApiResult<BmsTip> getNotices(){
        BmsTip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }



}


