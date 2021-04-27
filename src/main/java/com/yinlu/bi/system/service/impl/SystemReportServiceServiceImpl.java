package com.yinlu.bi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinlu.bi.system.domain.SystemReportService;
import com.yinlu.bi.system.service.SystemReportServiceService;
import com.yinlu.bi.system.mapper.SystemReportServiceMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SystemReportServiceServiceImpl extends ServiceImpl<SystemReportServiceMapper, SystemReportService>
implements SystemReportServiceService {
  @Resource
  SystemReportServiceMapper systemReportServiceMapper;

  @Override
  public List<SystemReportService> listByName(String name) {
    return systemReportServiceMapper.listByName(name);
  }
}




