package hu.cubix.catalogservice.patrik;

import hu.cubix.catalogservice.patrik.service.InitDbService;
import hu.cubix.tokenlib.patrik.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackageClasses = {JwtService.class, CatalogServiceApplication.class})
public class CatalogServiceApplication implements CommandLineRunner {

	private final InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.deleteDb();
		initDbService.deleteAudTables();
		initDbService.initDb();
	}
}
