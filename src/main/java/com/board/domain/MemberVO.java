package com.board.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userId;
	private String userPass;
	private String userName;
	private Date regDate;
	

}
