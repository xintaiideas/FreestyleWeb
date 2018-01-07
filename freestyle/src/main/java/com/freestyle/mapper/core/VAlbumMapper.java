package com.freestyle.mapper.core;

import com.freestyle.domain.entity.core.VAlbum;
import com.freestyle.domain.entity.core.VAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VAlbumMapper {
    int countByExample(VAlbumExample example);

    int deleteByExample(VAlbumExample example);

    int insert(VAlbum record);

    int insertSelective(VAlbum record);

    List<VAlbum> selectByExample(VAlbumExample example);

    int updateByExampleSelective(@Param("record") VAlbum record, @Param("example") VAlbumExample example);

    int updateByExample(@Param("record") VAlbum record, @Param("example") VAlbumExample example);
}