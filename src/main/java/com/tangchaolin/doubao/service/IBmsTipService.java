package com.tangchaolin.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchaolin.doubao.model.entity.BmsBillboard;
import com.tangchaolin.doubao.model.entity.BmsTip;

import java.util.function.BiConsumer;

public interface IBmsTipService extends IService<BmsTip> {

    BmsTip getRandomTip();
}
