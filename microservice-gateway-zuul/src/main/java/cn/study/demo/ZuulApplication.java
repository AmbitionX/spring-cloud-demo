package cn.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApplication
{

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main( String[] args )
    {
        System.setProperty("turbine.instanceUrlSuffix", "/actuator/hystrix.stream");
        System.out.println( "Hello World!" );
        SpringApplication.run(ZuulApplication.class, args);
    }
}
