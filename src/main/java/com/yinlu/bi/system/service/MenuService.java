package com.yinlu.bi.system.service;

import com.yinlu.bi.system.domain.SystemReportService;
import java.util.List;

/**
 * @author dzhao1
 */
public interface MenuService {

  /**
   * 获取用户路由树
   * @param name 用户名
   * @return  对应路由树
   */
  List<SystemReportService> listByName(String name);
}
