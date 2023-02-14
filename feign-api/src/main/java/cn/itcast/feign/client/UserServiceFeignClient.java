package cn.itcast.feign.client;

import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign客户端
 */
//@FeignClient(value = "userservice", configuration = DefaultFeignConfiguration.class)
@FeignClient("userservice")
public interface UserServiceFeignClient {

	@GetMapping("/user/{id}")
	public User queryById(@PathVariable("id") Long id);
}
