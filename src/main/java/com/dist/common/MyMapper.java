package com.dist.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基础Mapper
 *
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
