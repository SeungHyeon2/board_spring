package com.board.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

public class UUIDgeneration {
	public String getUUID() {
		String uuid = UUID.randomUUID().toString();
		
		uuid = uuid.replace("-", "");
		return uuid;
	}
}