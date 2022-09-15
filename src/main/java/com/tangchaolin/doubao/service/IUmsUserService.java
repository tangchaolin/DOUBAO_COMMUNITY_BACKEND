package com.tangchaolin.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangchaolin.doubao.model.dto.LoginDTO;
import com.tangchaolin.doubao.model.dto.RegisterDTO;
import com.tangchaolin.doubao.model.entity.BmsTip;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.ProfileVO;

public interface IUmsUserService extends IService<UmsUser> {


    UmsUser executeRegister(RegisterDTO dto);

    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    UmsUser getUserByUsername(String username);
    /**
     * 用户登录
     *
     * @param dto
     * @return 生成的JWT的token
     */
    String executeLogin(LoginDTO dto);
    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);
}
