package com.freestyle.mapper.core;

import com.freestyle.domain.entity.core.Mood;
import com.freestyle.domain.entity.core.MoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoodMapper {
	
    int countByExample(MoodExample example);

    int deleteByExample(MoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Mood record);

    int insertSelective(Mood record);

    List<Mood> selectByExample(MoodExample example);

    Mood selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Mood record, @Param("example") MoodExample example);

    int updateByExample(@Param("record") Mood record, @Param("example") MoodExample example);

    int updateByPrimaryKeySelective(Mood record);

    int updateByPrimaryKey(Mood record);
}