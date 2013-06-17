package com.example.quting.entity.like;

import java.io.Serializable;

public class Like implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * {"created_at":"2013-06-06T11:07:48Z","guest_id":2,"id":3,
	 * "medium_id":1,"updated_at":"2013-06-06T11:07:48Z","user_id":null}
	 * */
	private String created_at;
	private String guest_id;
	private String medium_id;
	private String updated_at;
	private String user_id;
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}
	public String getMedium_id() {
		return medium_id;
	}
	public void setMedium_id(String medium_id) {
		this.medium_id = medium_id;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	

}
