package com.tangchaolin.doubao.controller;

import com.tangchaolin.doubao.common.api.ApiResult;
import com.tangchaolin.doubao.model.entity.BmsComment;
import com.tangchaolin.doubao.model.vo.CommentVO;
import com.tangchaolin.doubao.service.IBmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/comment")
@RestController

public class BmsCommentController {
    @Autowired
    private IBmsCommentService iBmsCommentService;

    @GetMapping("/get_comments")
public ApiResult<List<CommentVO>> getCommentsByTopicId(@RequestParam(value = "topicid",defaultValue = "1") String topicId){
        List<CommentVO> lstBmsComment = iBmsCommentService.getCommentsByTopicId(topicId);
        return  ApiResult.success(lstBmsComment);

    }

}
