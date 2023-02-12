package cn.itcast.order;

import com.alibaba.nacos.api.annotation.NacosInjected;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
//@EnableDiscoveryClient
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 注册RestTemplate实现远程调用
     */
    @Bean
//    @LoadBalanced // 负载均衡
    @LoadBalanced
	public RestTemplate createRestTemplate() {
	    return new RestTemplate();
    }

}
