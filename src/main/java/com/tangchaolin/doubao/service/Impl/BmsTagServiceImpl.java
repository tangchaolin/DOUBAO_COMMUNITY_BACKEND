package com.tangchaolin.doubao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchaolin.doubao.mapper.BmsTagMapper;
import com.tangchaolin.doubao.model.entity.BmsTag;
import com.tangchaolin.doubao.service.IBmsPostService;
import com.tangchaolin.doubao.service.IBmsTagService;
import com.tangchaolin.doubao.service.IBmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BmsTagServiceImpl extends ServiceImpl<BmsTagMapper, BmsTag> implements IBmsTagService {

    @Autowired
    private com.tangchaolin.doubao.service.IBmsTopicTagService IBmsTopicTagService;

    @Autowired
    private com.tangchaolin.doubao.service.IBmsPostService IBmsPostService;
    @Override
    public List<BmsTag> insertTags(List<String> tags) {
        List<BmsTag> tagList = new ArrayList<>();
        for (String tagName : tags) {
            BmsTag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsTag>().eq(BmsTag::getName, tagName));
            if (tag == null) {
                tag = BmsTag.builder().name(tagName).build();
                this.baseMapper.insert(tag);
            } else {
                tag.setTopicCount(tag.getTopicCount() + 1);
                this.baseMapper.updateById(tag);
            }
            tagList.add(tag);
        }
        return tagList;
    }
}