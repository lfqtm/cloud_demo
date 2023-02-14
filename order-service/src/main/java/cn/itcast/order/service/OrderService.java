package cn.itcast.order.service;

import cn.itcast.feign.client.UserServiceFeignClient;
import cn.itcast.feign.pojo.Order;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderService {

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private UserServiceFeignClient userServiceFeignClient;

	public Order queryOrderById(Long orderId) {
		// 1.查询订单
		Order order = orderMapper.findById(orderId);
		// 修改服务地址（127.0.0.1:8081）为 userservice
//		String url = "http://userservice/user/" + order.getUserId();
//		User user = restTemplate.getForObject(url, User.class);

		//使用feign替换请求
		User user = userServiceFeignClient.queryById(order.getUserId());
		order.setUser(user);

//		ResponseEntity<User> user = restTemplate
//			.getForEntity("http://userservive/user/" + order.getUserId(), User.class);
//		order.setUser(user.getBody());
		return order;


	}
}
