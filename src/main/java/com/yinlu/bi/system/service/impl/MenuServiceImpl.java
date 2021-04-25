package com.yinlu.bi.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yinlu.bi.system.constants.Constants;
import com.yinlu.bi.system.domain.SystemReportService;
import com.yinlu.bi.system.domain.SystemUser;
import com.yinlu.bi.system.domain.Systemgroupreport;
import com.yinlu.bi.system.service.MenuService;
import com.yinlu.bi.system.service.SystemReportServiceService;
import com.yinlu.bi.system.service.SystemUserGroupService;
import com.yinlu.bi.system.service.SystemUserService;
import com.yinlu.bi.system.service.SystemgroupreportService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
  /** 用户表 */
  @Resource SystemUserService systemUserService;
  /** 角色表 */
  @Resource SystemUserGroupService systemUserGroupService;
  /** 角色菜单关联表 */
  @Resource SystemgroupreportService systemgroupreportService;
  /** 菜单表 */
  @Resource SystemReportServiceService systemReportServiceService;

  @Override
  public List<SystemReportService> listByName(String name) {
    List<SystemReportService> reportList = new ArrayList<>();
    // 获取用户信息
    SystemUser user =
        systemUserService.getOne(
            Wrappers.<SystemUser>lambdaQuery()
                .select(SystemUser::getGroupId)
                .eq(SystemUser::getDomainAccount, name)
                .eq(SystemUser::getStatus, Constants.STATUS)
                .groupBy(SystemUser::getGroupId));
    //    获取角色菜单关联信息
    List<Systemgroupreport> groupAndReportList =
        systemgroupreportService.list(
            Wrappers.<Systemgroupreport>lambdaQuery()
                .select(Systemgroupreport::getReportId)
                .eq(Systemgroupreport::getGroupId, user.getGroupId())
                .eq(Systemgroupreport::getStatus, Constants.STATUS)
                .groupBy(Systemgroupreport::getReportId));

    //    获取用户菜单集合
    groupAndReportList.forEach(
        group -> {
          SystemReportService Report =
              systemReportServiceService.getOne(
                  Wrappers.<SystemReportService>lambdaQuery()
                      .eq(SystemReportService::getRId, group.getReportId())
                      .eq(SystemReportService::getStatus, Constants.STATUS));
          if (Report != null) reportList.add(Report);
        });

    return reportList;
  }
}
