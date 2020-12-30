package com.ezra.mapper;

import com.ezra.dto.IdDTO;
import com.ezra.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper {


    User getById(@Param("id") Long id);

    User getByIdDTO(@Param("dto") IdDTO idDTO);
}
