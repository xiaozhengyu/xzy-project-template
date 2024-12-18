package com.xzy.user.provider;

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
        "com.xzy.user.provider",
        "com.xzy.common"
})
public class UserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }

    @RequestMapping("/")
    public String up() {
        return "xzy-user-provider";
    }
}
