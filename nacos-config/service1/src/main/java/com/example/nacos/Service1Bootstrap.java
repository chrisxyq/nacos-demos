package com.example.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author chrisxu
 * @create 2021-10-31 15:01
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
@SpringBootApplication
@RestController
public class Service1Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Service1Bootstrap.class, args);
    }
    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;
    @Value("${common.name}")
    private String config1;

    /**
     * 获取nacos实时配置
     * @return
     */
    @GetMapping(value = "/configs")
    public String getConfigs() {
        System.out.println(config1);
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        String property = environment.getProperty("common.name");
        return property;
    }

    @Test
    public void test() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", "127.0.0.1:8848");
        //不配置默认是public命名空间
        properties.put("namespace", "d9c60b7c-9553-4c31-82e6-383509839cfa");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig("service1.yaml", "TEST_GROUP", 5000);
        System.out.println(config);
    }
}
