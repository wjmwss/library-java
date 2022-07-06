package cn.srd.itcp.sugar.redisson;

import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SpringsUtil.class)
@SpringBootApplication
public class SugarRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SugarRedissonApplication.class, args);
    }

}
