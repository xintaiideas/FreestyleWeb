package com.freestyle.mapper.core;

import com.freestyle.domain.entity.core.VRecentlyPhoto;
import com.freestyle.domain.entity.core.VRecentlyPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VRecentlyPhotoMapper {
    int countByExample(VRecentlyPhotoExample example);

    int deleteByExample(VRecentlyPhotoExample example);

    int insert(VRecentlyPhoto record);

    int insertSelective(VRecentlyPhoto record);

    List<VRecentlyPhoto> selectByExample(VRecentlyPhotoExample example);

    int updateByExampleSelective(@Param("record") VRecentlyPhoto record, @Param("example") VRecentlyPhotoExample example);

    int updateByExample(@Param("record") VRecentlyPhoto record, @Param("example") VRecentlyPhotoExample example);
}