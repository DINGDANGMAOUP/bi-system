package com.yinlu.bi.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yinlu.bi.system.common.result.Result;
import com.yinlu.bi.system.domain.SystemUser;
import com.yinlu.bi.system.service.SystemUserService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Resource
  SystemUserService systemUserService;

  @PostMapping("test")
  public Result test(@RequestBody SystemUser name){
    SystemUser one =
        systemUserService.getOne(
            Wrappers.<SystemUser>lambdaQuery()
                .select(SystemUser::getGroupId)
                .eq(SystemUser::getDomainAccount, name.getDomainAccount()).eq(SystemUser::getStatus,0).groupBy(SystemUser::getGroupId));
    return Result.success(one);
  }

}
