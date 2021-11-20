package cn.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@EnableTurbine
@SpringBootApplication
public class TurbineApplication
{

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main( String[] args )
    {
        System.setProperty("turbine.instanceUrlSuffix", "/actuator/hystrix.stream");
        System.out.println( "Hello World!" );
        SpringApplication.run(TurbineApplication.class, args);
    }
}
