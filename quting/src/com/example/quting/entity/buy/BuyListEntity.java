package com.example.quting.entity.buy;

import java.io.Serializable;
import java.util.List;

import com.example.quting.entity.media.MediaBaseEntity;

public class BuyListEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MediaBaseEntity> buylist;

	public List<MediaBaseEntity> getList() {
		return buylist;
	}

	public void setList(List<MediaBaseEntity> buylist) {
		this.buylist = buylist;
	}
	
	
}
