package com.yinlu.bi.system.controller;

import com.yinlu.bi.system.common.result.Result;
import com.yinlu.bi.system.domain.SystemReportService;
import com.yinlu.bi.system.service.MenuService;
import com.yinlu.bi.system.service.SystemReportServiceService;
import com.yinlu.bi.system.service.SystemUserGroupService;
import com.yinlu.bi.system.service.SystemUserService;
import com.yinlu.bi.system.service.SystemgroupreportService;
import com.yinlu.bi.system.utils.MenuTreeUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzhao1
 */
@RestController
@RequestMapping("menu")
public class MenuController {

  @Resource
  MenuService menuService;

  @GetMapping("/{name}")
  public Result getMenuTree(@PathVariable(value = "name",required = false) String name){
    List<SystemReportService> menuTree = menuService.listByName("yinlu\\zsdan");
    MenuTreeUtil.construct(menuTree)
    return Result.success(menuTree);
  }

}
