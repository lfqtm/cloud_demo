package cn.itcast.order.web;

import cn.itcast.feign.pojo.Order;
import cn.itcast.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("{orderId}")
	public Order queryOrderByOrderrId(@PathVariable("orderId") Long orderId, HttpServletRequest request) {
		log.warn(request.getHeader("default-header"));
		// 根据id查询订单并返回
		return orderService.queryOrderById(orderId);
	}
}
