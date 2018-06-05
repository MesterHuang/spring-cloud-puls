package cn.figo.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHelloApplication.class, args);
	}

	static Logger LOGGER = LoggerFactory.getLogger(ServiceHelloApplication.class);

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


	@RequestMapping("/hello")
	public String home(@RequestParam String name) {
		return restTemplate().getForObject("http://service-hi/hi?name=" + name, String.class);
	}

}
