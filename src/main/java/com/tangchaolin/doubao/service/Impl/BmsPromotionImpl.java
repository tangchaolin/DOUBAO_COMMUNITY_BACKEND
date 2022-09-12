package com.tangchaolin.doubao.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchaolin.doubao.mapper.BmsBillboardMapper;
import com.tangchaolin.doubao.mapper.BmsPromotionMapper;
import com.tangchaolin.doubao.model.entity.BmsBillboard;
import com.tangchaolin.doubao.model.entity.BmsPromotion;
import com.tangchaolin.doubao.service.IBmsBillboardService;
import com.tangchaolin.doubao.service.IBmsPromotionService;
import org.springframework.stereotype.Service;

@Service
public class BmsPromotionImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion>
implements IBmsPromotionService
{ }
