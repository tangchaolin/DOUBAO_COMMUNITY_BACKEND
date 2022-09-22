package com.tangchaolin.doubao.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.dto.CreateTopicDTO;
import com.tangchaolin.doubao.model.entity.BmsPost;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.PostVO;
import com.tangchaolin.doubao.service.IBmsPostService;
import com.tangchaolin.doubao.service.IUmsUserService;
import com.tangchaolin.doubao.service.Impl.BmsPostServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id){
        List<BmsPost> topics = iBmsPostService.getRecommend(id);
        System.out.println(topics.size());

        return ApiResult.success(topics);
    }

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody BmsPost post){
        UmsUser user = umsUserService.getUserByUsername(userName);
        Assert.isTrue(user.getId().equals(post.getUserId()),"非本人无法修改！");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iBmsPostService.updateById(post);

        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME) String userName,@PathVariable("id") String id){
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(user.getId()),"你为什么可以删除别人的话题？？？");
        iBmsPostService.removeById(id);
        return ApiResult.success(null, "删除成功！");
    }


}