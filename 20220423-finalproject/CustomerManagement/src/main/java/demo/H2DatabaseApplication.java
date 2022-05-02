package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class H2DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2DatabaseApplication.class, args);
	}

	//@Bean
	//@LoadBalanced
	//public RestTemplate getRestTemplate()
	//{
	//	return new RestTemplate();
	//}
}