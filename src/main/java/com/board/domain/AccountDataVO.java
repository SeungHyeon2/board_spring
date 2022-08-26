package com.board.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AccountDataVO {
	
	private int ta_idx;
	private String ta_id;
	private String ta_pw;
	private Date ta_create_date;
	private String ta_secret_key;
	
}
