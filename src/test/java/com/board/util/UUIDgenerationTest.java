package com.board.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class UUIDgenerationTest {

	@Test
	public void test() {
		
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		
		System.out.println(uuid);
		
		
	}

}
