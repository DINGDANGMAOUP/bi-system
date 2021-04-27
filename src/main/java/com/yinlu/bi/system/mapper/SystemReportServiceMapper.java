package com.yinlu.bi.system.mapper;

import com.yinlu.bi.system.domain.SystemReportService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author dzhao1
 * @Entity com.yinlu.bi.system.domain.Systemreportservice
 */
public interface SystemReportServiceMapper extends BaseMapper<SystemReportService> {

  List<SystemReportService> listByName(@Param("name") String name);
}




