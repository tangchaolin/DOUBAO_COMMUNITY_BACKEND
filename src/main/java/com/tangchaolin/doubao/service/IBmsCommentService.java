package com.tangchaolin.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchaolin.doubao.model.dto.CommentDTO;
import com.tangchaolin.doubao.model.entity.BmsComment;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.CommentVO;

import java.util.List;

public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicId
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicId(String topicId);


    BmsComment creat(CommentDTO dto, UmsUser principal);

}
