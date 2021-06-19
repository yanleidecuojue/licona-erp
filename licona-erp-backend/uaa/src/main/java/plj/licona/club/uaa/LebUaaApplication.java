package plj.licona.club.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author licona
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(value = "plj.licona")
@MapperScan(value = "plj.licona.club.uaa.mapper")
public class LebUaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LebUaaApplication.class, args);
    }
}
