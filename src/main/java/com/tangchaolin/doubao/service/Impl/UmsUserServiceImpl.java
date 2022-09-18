package com.tangchaolin.doubao.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangchaolin.doubao.common.exception.ApiAsserts;
import com.tangchaolin.doubao.jwt.JwtUtil;
import com.tangchaolin.doubao.mapper.BmsFollowMapper;
import com.tangchaolin.doubao.mapper.BmsTopicMapper;
import com.tangchaolin.doubao.mapper.UmsUserMapper;
import com.tangchaolin.doubao.model.dto.LoginDTO;
import com.tangchaolin.doubao.model.dto.RegisterDTO;
import com.tangchaolin.doubao.model.entity.BmsFollow;
import com.tangchaolin.doubao.model.entity.BmsPost;
import com.tangchaolin.doubao.model.entity.UmsUser;
import com.tangchaolin.doubao.model.vo.ProfileVO;
import com.tangchaolin.doubao.service.IUmsUserService;
import com.tangchaolin.doubao.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {
    @Autowired
    private UmsUserMapper umsUserMapper;
    @Autowired
    private BmsTopicMapper bmsTopicMapper;
    @Autowired
    private BmsFollowMapper bmsFollowMapper;
    @Override
    public UmsUser executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsUser::getUsername, dto.getName()).or().eq(UmsUser::getEmail, dto.getEmail());
//        UmsUser umsUser = baseMapper.selectOne(wrapper);
        UmsUser umsUser = umsUserMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(umsUser)) {
            ApiAsserts.fail("账号或邮箱已存在！");
        }

        UmsUser addUser = UmsUser.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        return addUser;
    }


    @Override
    public UmsUser getUserByUsername(String username) {
        return umsUserMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username));
    }
    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            UmsUser user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if(!encodePwd.equals(user.getPassword()))
            {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
        } catch (Exception e) {
            log.warn("用户不存在or密码验证失败=======>{}", dto.getUsername());
        }
        return token;
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        UmsUser user = umsUserMapper.selectById(id);
        BeanUtils.copyProperties(user, profile);
        Integer followerCount=bmsFollowMapper.selectCount(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, user.getId()));
        profile.setFollowerCount(followerCount);
        Integer topicCount= bmsTopicMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, user.getId()));
        profile.setTopicCount(topicCount);

        return profile;
    }


}
