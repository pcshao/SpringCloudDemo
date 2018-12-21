package cn.pcshao.springcloud.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author pcshao.cn
 * @date 2018/12/20
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;
    @RequestMapping("/")
    public String home() {
        return "hello world from port " + port;
    }

    @RequestMapping("/album")
    public String like(){
        return this.restTemplate.getForObject("http://localhost:80/album/square", String.class);
    }

}
