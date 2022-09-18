package com.tangchaolin.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("bms_follow")
@NoArgsConstructor
public class BmsFollow {

    private static long serialVersionUID =1L;
    @TableId(type= IdType.AUTO)
    private  Integer id;

    @TableField("parent_id")
    private  String parentId;

    @TableField("follower_id")
    private  String followerId;




}
