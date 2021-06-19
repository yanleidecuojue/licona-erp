package plj.licona.club.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author licona
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "plj.licona.club.user.mapper")
public class LebUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LebUserServiceApplication.class, args);
    }
}
