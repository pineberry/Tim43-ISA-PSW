package isapsw.tim43.ISCC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"isapsw.tim43.ISCC"})
@EnableAsync
public class IsccApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsccApplication.class, args);
	}

}
