package com.tangchaolin.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchaolin.doubao.model.entity.BmsBillboard;
import com.tangchaolin.doubao.model.entity.BmsTip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//import org.springframework.stereotype.Repository;

@Mapper
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    @Select("select * from bms_tip order by rand() limit 1")
    BmsTip getRandomTip();
    
}
