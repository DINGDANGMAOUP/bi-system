package com.yinlu.bi.system;

import com.yinlu.bi.system.constants.Constants;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BiSystemApplicationTests {

    @Test
    void contextLoads() {

        boolean matches = Pattern.matches(Constants.URL,
            "https://bi.yinlu.com/reports/mobilereport/102%20Sales%20Performent%20Reports/01%20Monthly%20sales%20analysis?rs:Embed=true");
    System.out.println(matches);
    }

}
