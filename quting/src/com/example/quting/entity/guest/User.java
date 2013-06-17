package com.example.quting.entity.guest;

import java.io.Serializable;

public class User  implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * {"guest":{"created_at":"2013-06-06T11:01:14Z",
		"device_token":"1111","id":2,
		"updated_at":"2013-06-06T11:01:14Z"}}
	 */
	private String created_at;
	private String device_token;
	private String id;
	private String updated_at;
	
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDevice_token() {
		return device_token;
	}
	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
}
