package com.adnan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

	private String message;
	
	private String dataId; // optional
	
	private String data; // optional

	public MessageResponse(String message, String dataId, String data) {
		super();
		this.message = message;
		this.dataId = dataId;
		this.data = data;
	}
	
	
}
