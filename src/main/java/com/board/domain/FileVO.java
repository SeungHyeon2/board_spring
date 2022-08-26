package com.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FileVO {
	
	private int fno;
	private int bno;
	private String fileName;
	private String orgName;
	private String filePath;
	private Date regDate;
	private String delYN;
	private Long fileSize;

}
