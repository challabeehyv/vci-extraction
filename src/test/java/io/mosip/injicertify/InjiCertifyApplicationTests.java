package io.mosip.injicertify;

import io.mosip.injicertify.dto.vci.ParsedAccessToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootTest
class InjiCertifyApplicationTests {

	@Test
	public void test() {
		InjiCertifyApplication.main(new String[] {});
		Assertions.assertNotNull(InjiCertifyApplication.class);
	}
	@Bean
	@RequestScope
	public ParsedAccessToken parsedAccessToken() {
		return new ParsedAccessToken();
	}

}
