package com.tangchaolin.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchaolin.doubao.model.entity.BmsPromotion;
import com.tangchaolin.doubao.model.entity.BmsTip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//import org.springframework.stereotype.Repository;

@Mapper
public interface BmsPromotionMapper extends BaseMapper<BmsPromotion> {
}
