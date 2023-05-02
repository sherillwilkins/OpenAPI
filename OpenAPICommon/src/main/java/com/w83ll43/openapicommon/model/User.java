package com.w83ll43.openapicommon.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private String password;

    @TableField(exist = false)
    private String checkPassword;

    /**
     * 开发者标识
     */
    private String accessKey;

    /**
     * 接口加密
     */
    private String secretKey;

    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 创建人
     */
    private Long createUser;

    private static final long serialVersionUID = 1L;
}