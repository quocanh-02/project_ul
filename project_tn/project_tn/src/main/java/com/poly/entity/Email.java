package com.poly.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
	private String from;
	private String to;
	private String fromPassword;
	private String content;
	private String subject;

}
