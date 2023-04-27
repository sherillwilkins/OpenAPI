package com.w83ll43.openapi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName interface_info
 */
@Data
@TableName(value ="interface_info")
public class InterfaceInfo implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求头
     */
    @TableField(value = "requestHeader")
    private String requestHeader;

    /**
     * 响应头
     */
    @TableField(value = "responseHeader")
    private String responseHeader;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 接口状态（0：关闭、1：开启）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "isDeleted")
    private Integer isDeleted;

    /**
     * 创建人
     */
    @TableField(value = "createUser", fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}