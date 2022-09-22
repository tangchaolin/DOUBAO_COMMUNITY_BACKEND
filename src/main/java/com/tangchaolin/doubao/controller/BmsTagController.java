package com.tangchaolin.doubao.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.entity.BmsPost;
import com.tangchaolin.doubao.model.entity.BmsTag;
import com.tangchaolin.doubao.service.IBmsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class BmsTagController {
    @Autowired
    private IBmsTagService iBmsTagService;

    @GetMapping("/{name}")
    public ApiResult<Map<String,Object>> getTopicsByTag(
            @PathVariable("name") String tagName,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "10") Integer size
    ){
        Map<String, Object> map = new HashMap<>(16);
        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsTag::getName, tagName);
        BmsTag one = iBmsTagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已经被管理员删除");
        Page<BmsPost> topics = iBmsTagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        Page<BmsTag> hotTags = iBmsTagService.page(new Page<>(1,10),new LambdaQueryWrapper<BmsTag>().notIn(BmsTag::getName,tagName).orderByDesc(BmsTag::getTopicCount));
        map.put("topics", topics);
        map.put("hotTags", hotTags);

        return ApiResult.success(map);
    }



}
