package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@MapperScan("com.mapper")
public class Project2Application {

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //建立新值
        jedis.set("service" , "启动");
        System.out.println("使用java新建的值service:  " + jedis.get("service"));
    }

}
