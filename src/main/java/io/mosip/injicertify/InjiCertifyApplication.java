package io.mosip.injicertify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@SpringBootApplication(scanBasePackages = "io.mosip.injicertify")
public class InjiCertifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(InjiCertifyApplication.class, args);
	}

}
