package cn.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulAggreApplication
{

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main( String[] args )
    {
        System.setProperty("turbine.instanceUrlSuffix", "/actuator/hystrix.stream");
        System.out.println( "Hello World!" );
        SpringApplication.run(ZuulAggreApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
