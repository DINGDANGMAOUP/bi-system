package com.yinlu.bi.system.service;

import com.yinlu.bi.system.domain.SystemReportService;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *
 */
public interface SystemReportServiceService extends IService<SystemReportService> {


   List<SystemReportService> listByName(String name) ;
}
