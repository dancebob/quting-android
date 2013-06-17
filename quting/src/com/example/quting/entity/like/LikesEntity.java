package com.example.quting.entity.like;

import java.io.Serializable;
import java.util.List;

import com.example.quting.entity.media.MediaBaseEntity;

public class LikesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MediaBaseEntity> medialist;

	public List<MediaBaseEntity> getMedialist() {
		return medialist;
	}

	public void setMedialist(List<MediaBaseEntity> medialist) {
		this.medialist = medialist;
	}
	
}
