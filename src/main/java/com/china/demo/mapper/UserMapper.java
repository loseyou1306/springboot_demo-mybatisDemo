package com.china.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.china.demo.domain.User;

@Mapper
public interface UserMapper {
	int create(User pt);
	 /**
     * 查所有
     *
     * @return
     */
    @Select("SELECT * FROM USER WHERE 1=1")
    List<User> findAll();
}
