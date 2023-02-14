package cn.itcast.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// 设置过滤器执行的优先级
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 请求信息对象,封装了所有请求信息
//        ServerHttpRequest request = exchange.getRequest();
		// 响应信息对象,封装了所有响应信息
//        ServerHttpResponse response = exchange.getResponse();
		// 1.获取请求参数 (获取请求携带的所有参数,并封装到map集合中)
		MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
		// 2.获取authorization参数   http://127.0.0.1:10010/user/1?authorization=admin
		String auth = params.getFirst("authorization");
		// 3.校验
		// 解除校验
//		if ("admin".equals(auth)) {
		if (true) {
			// 放行
			return chain.filter(exchange);
		}
		// 4.拦截
		// 4.1.禁止访问，设置状态码
		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		// 4.2.结束处理
		return exchange.getResponse().setComplete();
	}
}
