package hu.cubix.orderservice.patrik;

import hu.cubix.orderservice.patrik.service.InitDbService;
import hu.cubix.tokenlib.patrik.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackageClasses = {JwtService.class, OrderServiceApplication.class})
public class OrderServiceApplication implements CommandLineRunner {

	private final InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.deleteDb();
		initDbService.initDb();
	}
}
