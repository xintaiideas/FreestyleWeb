package com.freestyle.mapper.core;

import com.freestyle.domain.entity.core.VPhoto;
import com.freestyle.domain.entity.core.VPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VPhotoMapper {
    int countByExample(VPhotoExample example);

    int deleteByExample(VPhotoExample example);

    int insert(VPhoto record);

    int insertSelective(VPhoto record);

    List<VPhoto> selectByExample(VPhotoExample example);

    int updateByExampleSelective(@Param("record") VPhoto record, @Param("example") VPhotoExample example);

    int updateByExample(@Param("record") VPhoto record, @Param("example") VPhotoExample example);
}