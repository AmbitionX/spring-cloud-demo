package cn.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ProviderUserApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(ProviderUserApplication.class, args);
    }
}