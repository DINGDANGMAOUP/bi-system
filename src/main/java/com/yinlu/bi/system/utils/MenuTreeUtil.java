package com.yinlu.bi.system.utils;

import com.yinlu.bi.system.constants.Constants;
import com.yinlu.bi.system.domain.SystemReportService;
import com.yinlu.bi.system.service.SystemgroupreportService;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/** @author dzhao1 */
public class MenuTreeUtil {
  public static List construct(List<SystemReportService> menuList) {
    // 获取所有菜单栏，过滤跳转链接
    List<SystemReportService> menuBar =
        menuList.stream().filter(menu -> "#".equals(menu.getRUrl())).collect(Collectors.toList());
    //获取一级菜单
    List<SystemReportService> menuBarLevelFirstList = menuBar.stream().filter(menu -> menu.getRName() == null)
        .collect(Collectors.toList());
    //获取二级菜单
    List<SystemReportService> menuBarLevelSecondList = menuBar.stream().filter(menu -> menu.getRUrl() != null)
        .collect(Collectors.toList());
    //获取跳转链接
    List<SystemReportService> menuUrlList = menuBar.stream().filter(menu ->
        Pattern.matches(Constants.URL, menu.getRUrl())
    ).collect(Collectors.toList());
    return null;
  }
}
