package plj.licona.club.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author licona
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = "plj.licona.club.acl.mapper")
public class LebAclServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LebAclServiceApplication.class, args);
    }
}
