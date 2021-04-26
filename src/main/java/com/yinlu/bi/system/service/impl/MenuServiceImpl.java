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
import java.util.concurrent.atomic.AtomicBoolean;
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
    List<SystemReportService> sysMenus = new ArrayList<>();
    List<SystemReportService> menus = findTreeByName(name);
    menus.forEach(
        menu -> {
          if (menu.getRPId() == Constants.LEVEL
              && Constants.NULL.equalsIgnoreCase(menu.getRPName())) {
            menu.setLevel(Constants.LEVEL);
            if (!exists(sysMenus, menu)) {
              sysMenus.add(menu);
            }
          }
        });
    sysMenus.sort((o1, o2) -> o1.getRSort().compareTo(o2.getRSort()));
    findChildren(sysMenus, menus);
    return sysMenus;
  }

  /**
   * 获取用户对应权限菜单
   * @param name 用户名
   * @return 权限菜单集合
   */
  private List<SystemReportService> findTreeByName(String name) {
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
          SystemReportService report =
              systemReportServiceService.getOne(
                  Wrappers.<SystemReportService>lambdaQuery()
                      .eq(SystemReportService::getRId, group.getReportId())
                      .eq(SystemReportService::getStatus, Constants.STATUS));
          if (report != null) reportList.add(report);
        });
    return reportList;
  }

  /**
   * 判断是否存在
   * @param sysMenus 对象集合
   * @param sysMenu  对象
   * @return 是否包含
   */
  private boolean exists(List<SystemReportService> sysMenus, SystemReportService sysMenu) {
    AtomicBoolean exist = new AtomicBoolean(false);
    sysMenus.forEach(
        menu -> {
          if (menu.getRId().equals(sysMenu.getRId())) {
            exist.set(true);
          }
        });

    return exist.get();
  }

  /**
   * 查询子菜单
   * @param SysMenus
   * @param menus
   */
  private void findChildren(List<SystemReportService> SysMenus, List<SystemReportService> menus) {
    SysMenus.forEach(
        sysMenu -> {
          List<SystemReportService> children = new ArrayList<>();
          menus.forEach(
              menu -> {
                if (sysMenu.getRName() != null && sysMenu.getRId().equals(menu.getRPId())) {
                  menu.setLevel(sysMenu.getLevel() + 1);
                  if (!exists(children, menu)) {
                    children.add(menu);
                  }
                }
              });
          sysMenu.setChildren(children);
          children.sort((o1, o2) -> o1.getRSort().compareTo(o2.getRSort()));
          findChildren(children, menus);
        });
  }
}
