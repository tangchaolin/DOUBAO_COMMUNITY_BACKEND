package com.tangchaolin.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchaolin.doubao.model.entity.BmsComment;
import com.tangchaolin.doubao.model.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BmsCommentMapper extends BaseMapper<BmsComment> {
    /**
     * getCommentsByTopicID
     *
     * @param topicid
     * @return
     */
    List<CommentVO> getCommentsByTopicID(@Param("topicid") String topicid);

}
