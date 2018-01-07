package com.freestyle.mapper.core;

import com.freestyle.domain.entity.core.MoodFile;
import com.freestyle.domain.entity.core.MoodFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoodFileMapper {
    int countByExample(MoodFileExample example);

    int deleteByExample(MoodFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoodFile record);

    int insertSelective(MoodFile record);

    List<MoodFile> selectByExample(MoodFileExample example);

    MoodFile selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoodFile record, @Param("example") MoodFileExample example);

    int updateByExample(@Param("record") MoodFile record, @Param("example") MoodFileExample example);

    int updateByPrimaryKeySelective(MoodFile record);

    int updateByPrimaryKey(MoodFile record);
}