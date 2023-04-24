package com.w83ll43.openapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.w83ll43.openapi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author w83ll43
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-24 21:06:04
* @Entity com.w83ll43.openapi.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




