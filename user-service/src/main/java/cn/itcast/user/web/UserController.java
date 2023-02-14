package cn.itcast.user.web;

import cn.itcast.user.config.DateProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope //nacos配置热更新方式1 方式2见 DateProperties
public class UserController {

	@Autowired
	private UserService userService;

//	@Value("${pattern.dateformat}")
//	private String dateformat;

	@Autowired
	private DateProperties dateformat;

	/**
	 * 路径： /user/110
	 *
	 * @param id 用户id
	 * @return 用户
	 */
	@GetMapping("/{id}")
	public User queryById(@PathVariable("id") Long id) {
		return userService.queryById(id);
	}

	@GetMapping("/now")
	public String queryNow() {
		// application.yml获取配置
//	    System.out.println(dataformat);
//    	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dataformat));
		// nacos获取配置
//		System.out.println(dataformat);
//		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dataformat));
		// nacos配置动态刷新方式二
		System.out.println("获取的nacos实时配置" + dateformat.getDateformat());
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat.getDateformat()));
	}

	@GetMapping("/prop")
	public DateProperties queryProp(HttpServletRequest request) {
		String myHeader = request.getHeader("Truth");
		log.warn(myHeader);
		return dateformat;
	}
}
