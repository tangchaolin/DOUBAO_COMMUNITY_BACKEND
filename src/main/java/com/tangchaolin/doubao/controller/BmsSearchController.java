package com.tangchaolin.doubao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.vo.PostVO;
import com.tangchaolin.doubao.service.IBmsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class BmsSearchController {
    @Autowired
    IBmsPostService iBmsPostService;

    @GetMapping
    public ApiResult<Page<PostVO>> searchList(@RequestParam("keyword") String keyword,@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNum") Integer pageNum){
        Page<PostVO> results = iBmsPostService.searchByKey(keyword, new Page<>(pageNum, pageSize));

        return ApiResult.success(results);
    }


}
