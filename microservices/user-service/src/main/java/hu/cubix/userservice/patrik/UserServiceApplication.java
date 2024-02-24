package hu.cubix.userservice.patrik;

import hu.cubix.tokenlib.patrik.JwtService;
import hu.cubix.userservice.patrik.service.InitDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackageClasses = {JwtService.class, UserServiceApplication.class})
public class UserServiceApplication implements CommandLineRunner {

	private final InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.deleteDb();
		initDbService.createUsersIfNeeded();
	}
}
