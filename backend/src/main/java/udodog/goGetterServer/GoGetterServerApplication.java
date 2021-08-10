package udodog.goGetterServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class GoGetterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoGetterServerApplication.class, args);
	}

}
