package com.tangchaolin.doubao.controller;

import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.dto.CommentDTO;
import com.tangchaolin.doubao.model.entity.BmsComment;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.CommentVO;
import com.tangchaolin.doubao.service.IBmsCommentService;
import com.tangchaolin.doubao.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tangchaolin.doubao.jwt.JwtUtil.USER_NAME;

@RequestMapping("/comment")
@RestController

public class BmsCommentController {
    @Autowired
    private IBmsCommentService iBmsCommentService;
    @Autowired
    private IUmsUserService iUmsUserService;
    @GetMapping("/get_comments")
public ApiResult<List<CommentVO>> getCommentsByTopicId(@RequestParam(value = "topicid",defaultValue = "1") String topicId){
        List<CommentVO> lstBmsComment = iBmsCommentService.getCommentsByTopicId(topicId);
        return  ApiResult.success(lstBmsComment);

    }

    @PostMapping("/add_comment")
    public ApiResult<BmsComment> add_comment(@RequestHeader(value = USER_NAME) String userName, @RequestBody CommentDTO dto){
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        BmsComment comment = iBmsCommentService.creat(dto, user);


        return ApiResult.success(comment);

    }

}
