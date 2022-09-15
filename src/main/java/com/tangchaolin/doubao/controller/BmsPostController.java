package com.tangchaolin.doubao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.dto.CreateTopicDTO;
import com.tangchaolin.doubao.model.entity.BmsPost;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.PostVO;
import com.tangchaolin.doubao.service.IBmsPostService;
import com.tangchaolin.doubao.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import static com.tangchaolin.doubao.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;
    @Resource
    private IUmsUserService umsUserService;
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }


}