package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.log4j.Log4j;

@Log4j
@PropertySource("/auth.properties")
//@ExtendWith(SpringExtension.class)
class PropertiesTest {

//	@Value("${board.http.auth-token-header.name}")
//	private String principalRequestHeader;
	
	@Value("#{Prop['board.http.auth-token-header.name']}")
	private String principalRequestHeader;
	
	@Value("${board.http.auth-token}")
	private String principalRequestValue;
	
	@Test
	public void propertiesTest() {
		log.info("auth-token-RequestHeader : " + principalRequestHeader);
		log.info("auth-token-RequestValue : " + principalRequestValue);
	}

}
