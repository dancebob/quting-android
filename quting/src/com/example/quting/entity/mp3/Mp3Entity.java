package com.example.quting.entity.mp3;

import java.io.Serializable;
import java.util.List;

public class Mp3Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Mp3BaseEntity> mfiles;
	
	private String remark;//扩展字段

	public List<Mp3BaseEntity> getMfiles() {
		return mfiles;
	}

	public void setMfiles(List<Mp3BaseEntity> mfiles) {
		this.mfiles = mfiles;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
