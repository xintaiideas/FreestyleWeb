package com.freestyle.mapper.core;

import com.freestyle.domain.entity.core.Photo;
import com.freestyle.domain.entity.core.PhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhotoMapper {
    int countByExample(PhotoExample example);

    int deleteByExample(PhotoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Photo record);

    int insertSelective(Photo record);

    List<Photo> selectByExample(PhotoExample example);

    Photo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Photo record, @Param("example") PhotoExample example);

    int updateByExample(@Param("record") Photo record, @Param("example") PhotoExample example);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
}