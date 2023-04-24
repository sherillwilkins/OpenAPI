package com.w83ll43.openapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private Long id;

    private String username;

    private Integer status;

    private static final long serialVersionUID = 1L;
}
