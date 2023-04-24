package com.w83ll43.openapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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

    private Integer status;

    private static final long serialVersionUID = 1L;
}