package plj.licona.club.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author licona
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LebGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LebGatewayApplication.class, args);
    }
}
