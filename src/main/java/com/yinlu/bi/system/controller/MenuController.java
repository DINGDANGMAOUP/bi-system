package com.yinlu.bi.system.controller;

import com.yinlu.bi.system.common.result.Result;
import com.yinlu.bi.system.domain.LoginBean;
import com.yinlu.bi.system.domain.SystemReportService;
import com.yinlu.bi.system.service.MenuService;

import com.yinlu.bi.system.utils.MenuTreeUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/tree")
  public Result getMenuTree(){
    List<SystemReportService> menuTree = menuService.listByName("yinlu\\biadmin");
    return Result.success(menuTree);
  }

}
