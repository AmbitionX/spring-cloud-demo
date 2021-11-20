package cn.study.demo.service;

import java.math.BigDecimal;

import cn.study.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Service
public class AggregationService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id) {
        return Observable.create(observer -> {
            User user = restTemplate.getForObject("http://MICROSERVICE-PROVIDER-USER/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }
    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserById(Long id) {
        return Observable.create(observer -> {
            User user = restTemplate.getForObject("http://MICROSERVICE-CONSUMER-MOVIE/user/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    public User fallback(Long id) {
        User user = new User();
        user.setId(-1L);
        return user;
    }


}
