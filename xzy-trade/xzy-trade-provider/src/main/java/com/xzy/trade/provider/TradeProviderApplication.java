package com.xzy.trade.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@RestController
@SpringBootApplication(scanBasePackages = {
        "com.xzy.trade.provider",
        "com.xzy.common"
})
public class TradeProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeProviderApplication.class, args);
    }

    @RequestMapping("/")
    public String up() {
        return "xzy-trade-provider";
    }
}
