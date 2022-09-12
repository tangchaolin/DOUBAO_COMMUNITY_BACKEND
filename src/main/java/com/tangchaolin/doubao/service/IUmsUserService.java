package com.tangchaolin.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchaolin.doubao.model.dto.RegisterDTO;
import com.tangchaolin.doubao.model.entity.BmsTip;
import com.tangchaolin.doubao.model.entity.UmsUser;

public interface IUmsUserService extends IService<UmsUser> {


    UmsUser executeRegister(RegisterDTO dto);
}
