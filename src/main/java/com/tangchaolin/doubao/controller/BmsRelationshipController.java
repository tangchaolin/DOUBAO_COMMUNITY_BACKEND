package com.tangchaolin.doubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.common.exception.ApiAsserts;
import com.tangchaolin.doubao.mapper.UmsUserMapper;
import com.tangchaolin.doubao.model.entity.BmsFollow;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.service.IBmsFollowService;
import com.tangchaolin.doubao.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.tangchaolin.doubao.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/relationship")
public class BmsRelationshipController {

    @Autowired
    private IUmsUserService iUmsUserService;
    @Autowired
    private IBmsFollowService iBmsFollowService;

    @GetMapping("/subscribe/{userId}")
    public ApiResult<Object> handleFollow(@RequestHeader(value = USER_NAME) String userName, @PathVariable("userId") String parentId) {

        UmsUser user = iUmsUserService.getUserByUsername(userName);
        if (parentId.equals(user.getId())) {
            ApiAsserts.fail("你的脸皮太厚了，怎么可以关注自己呢？\uD83D\uDE2E");

        }
        BmsFollow one = iBmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId).eq(BmsFollow::getFollowerId, user.getId()));
        if (!ObjectUtils.isEmpty(one)) {

            ApiAsserts.fail("已关注");
        }
        BmsFollow bmsFollow= new BmsFollow();
        bmsFollow.setParentId(parentId);
        bmsFollow.setFollowerId(user.getId());
        iBmsFollowService.save(bmsFollow);
        return ApiResult.success(null, "关注成功");

    }
    @GetMapping("/validate/{topicUserId}")
    public ApiResult<Map<String,Object>> isFollow(@RequestHeader(value = USER_NAME) String userName,@PathVariable("topicUserId") String topicUserId){
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(user)) {
            BmsFollow bmsFollow=iBmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, topicUserId).eq(BmsFollow::getFollowerId, user.getId()));
            if (!ObjectUtils.isEmpty(bmsFollow)){

                map.put("hasFollow", true);
            }
        }


        return  ApiResult.success(map);
    }

    @GetMapping("/unsubscribe/{userId}")
    public ApiResult<Object> handleUnFollow(@RequestHeader(value = USER_NAME) String userName,@PathVariable("userId") String parentId){
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        BmsFollow follow = iBmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId).eq(BmsFollow::getFollowerId, user.getId()));
        if (ObjectUtils.isEmpty(follow)){
            ApiAsserts.fail("未关注！");
        }
        iBmsFollowService.remove(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId).eq(BmsFollow::getFollowerId, user.getId()));

        return ApiResult.success(null,"取关成功");
    }




}