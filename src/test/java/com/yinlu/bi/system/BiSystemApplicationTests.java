package com.yinlu.bi.system;

import com.yinlu.bi.system.constants.Constants;
import com.yinlu.bi.system.domain.SystemReportService;
import com.yinlu.bi.system.service.MenuService;
import com.yinlu.bi.system.service.SystemReportServiceService;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BiSystemApplicationTests {
@Resource
    SystemReportServiceService systemReportServiceService;
    @Resource
    MenuService menuService;
    @Test
    void contextLoads() {
//        List<SystemReportService> systemReportServices = systemReportServiceService
//            .listByName("yinlu\\biadmin");
        List<SystemReportService> systemReportServices = menuService.listByName("yinlu\\biadmin");
        System.out.println(systemReportServices.toString());

//            "https://bi.yinlu.com/reports/mobilereport/102%20Sales%20Performent%20Reports/01%20Monthly%20sales%20analysis?rs:Embed=true");
//    System.out.println(matches);
    }

}
