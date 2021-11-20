package cn.study.demo.controller;


import cn.study.demo.entity.User;
import cn.study.demo.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
    }

    @GetMapping("/feign/{id}")
    public User findByFeignId(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }


    @HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
                    value = "5000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",
                    value = "10000")
    },threadPoolProperties = {
            @HystrixProperty(name="coreSize",value = "1"),
            @HystrixProperty(name="maxQueueSize",value = "10")
    })
    @GetMapping("/user/{id}")
    public User findByUserId(@PathVariable Long id) {
        return this.restTemplate.getForObject("microservice-provider-user/" + id, User.class);
    }

    public User findByIdFallback(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        return user;
    }

}
