package com.tangchaolin.doubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.entity.BmsBillboard;
import com.tangchaolin.doubao.model.entity.BmsPromotion;
import com.tangchaolin.doubao.service.IBmsBillboardService;
import com.tangchaolin.doubao.service.IBmsPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController{
    @Autowired
    private IBmsPromotionService bmsPromotionService;
    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> getNotices(){
        List<BmsPromotion> list = bmsPromotionService.list();

        return ApiResult.success(list);
    }



}


