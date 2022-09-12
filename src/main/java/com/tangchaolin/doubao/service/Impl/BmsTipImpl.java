package com.tangchaolin.doubao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchaolin.doubao.mapper.BmsBillboardMapper;
import com.tangchaolin.doubao.mapper.BmsTipMapper;
import com.tangchaolin.doubao.model.entity.BmsBillboard;
import com.tangchaolin.doubao.model.entity.BmsTip;
import com.tangchaolin.doubao.service.IBmsBillboardService;
import com.tangchaolin.doubao.service.IBmsTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BmsTipImpl extends ServiceImpl<BmsTipMapper, BmsTip> implements IBmsTipService
{
    @Autowired
    private BmsTipMapper bmsTipMapper;

    @Override
    public BmsTip getRandomTip() {
        return bmsTipMapper.getRandomTip();
    }
}
