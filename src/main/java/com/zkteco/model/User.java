package com.zkteco.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	public Long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private int age;
}
