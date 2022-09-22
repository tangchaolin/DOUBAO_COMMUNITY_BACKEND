package com.tangchaolin.doubao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchaolin.doubao.mapper.BmsCommentMapper;
import com.tangchaolin.doubao.model.dto.CommentDTO;
import com.tangchaolin.doubao.model.entity.BmsComment;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.CommentVO;
import com.tangchaolin.doubao.service.IBmsCommentService;
import org.springframework.stereotype.Service;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BmsCommentServiceImpl extends ServiceImpl<BmsCommentMapper, BmsComment> implements IBmsCommentService {


    @Override
    public List<CommentVO> getCommentsByTopicId(String topicId) {
        List<CommentVO> commentVOList = new ArrayList<>();
        try {
            commentVOList = baseMapper.getCommentsByTopicID(topicId);
        } catch (Exception e) {
            System.out.println("BmsCommentList失败了！");
        }


        return commentVOList;
    }

    @Override
    public BmsComment creat(CommentDTO dto, UmsUser principal) {
        BmsComment bmsComment= BmsComment.builder().userId(principal.getId()).
                content(dto.getContent()).topicId(dto.getTopic_id())
                .createTime(new Date()).build();
        this.baseMapper.insert(bmsComment);
        return  bmsComment;
    }

}
