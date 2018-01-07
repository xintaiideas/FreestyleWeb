package com.freestyle.mapper.admin;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.entity.admin.SysFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFileMapper {
    int countByExample(SysFileExample example);

    int deleteByExample(SysFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysFile record);

    int insertSelective(SysFile record);

    List<SysFile> selectByExample(SysFileExample example);

    SysFile selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysFile record, @Param("example") SysFileExample example);

    int updateByExample(@Param("record") SysFile record, @Param("example") SysFileExample example);

    int updateByPrimaryKeySelective(SysFile record);

    int updateByPrimaryKey(SysFile record);
}