package com.tangchaolin.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangchaolin.doubao.model.entity.BmsTip;
import com.tangchaolin.doubao.model.entity.UmsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//import org.springframework.stereotype.Repository;

@Mapper
public interface UmsUserMapper extends BaseMapper<UmsUser> {
    
}
