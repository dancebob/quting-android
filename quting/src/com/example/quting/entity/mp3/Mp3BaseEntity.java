package com.example.quting.entity.mp3;

import java.io.Serializable;

public class Mp3BaseEntity implements Serializable{

	/**
	 * {"created_at":"2013-06-14T10:35:13Z","id":71,"medium_id":5,"name":"复活（上）",
	 * "time":"23:14","updated_at":"2013-06-14T10:35:43Z","url":"public/mp3/71.mp3"}
	 */
	private static final long serialVersionUID = 1L;
	
	private String created_at;
	private int medium_id;
	private int id;
	private String name;
	private String time;
	private String updated_at;
	private String url;
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getMedium_id() {
		return medium_id;
	}
	public void setMedium_id(int medium_id) {
		this.medium_id = medium_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
