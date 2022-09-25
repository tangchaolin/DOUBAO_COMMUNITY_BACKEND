package com.tangchaolin.doubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.dto.LoginDTO;
import com.tangchaolin.doubao.model.dto.RegisterDTO;
import com.tangchaolin.doubao.model.entity.BmsPost;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.service.IBmsPostService;
import com.tangchaolin.doubao.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import static com.tangchaolin.doubao.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController {
    @Resource
    private IUmsUserService iUmsUserService;
    @Autowired
    private IBmsPostService iBmsPostService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        UmsUser user = iUmsUserService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }
    @PostMapping("/login")
    public ApiResult<Map<String,String>> login(@RequestBody @Valid LoginDTO dto){

        String token = iUmsUserService.executeLogin(dto);

        if (ObjectUtils.isEmpty(token)){
            return ApiResult.failed("密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map,"登陆成功");


    }
    @GetMapping("/info")
    public ApiResult<UmsUser> getUser(@RequestHeader("userName") String userName){
        System.out.println(userName);
//        String userName = "zhangwei";
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }
    @GetMapping("/logout")
    public ApiResult<Object> logOut(){
        return ApiResult.success(null, "注销成功");
    }


    @GetMapping("/{username}")
    public ApiResult<Map<String, Object>> getUserByName(@PathVariable("username") String username,
                                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        UmsUser user = iUmsUserService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");
        Page<BmsPost> page = iBmsPostService.page(new Page<>(pageNo, size),
                new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, user.getId()));
        map.put("user", user);
        map.put("topics", page);
        return ApiResult.success(map);
    }

    @PostMapping("/update")
    public ApiResult<UmsUser> updateUser(@RequestBody UmsUser user){

        iUmsUserService.updateById(user);
        return ApiResult.success(user);

    }

}


