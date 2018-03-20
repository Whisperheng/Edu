package com.hank_01.edu.common.util;

import java.util.HashMap;
import java.util.Map;

public class CommonResult<T> {

	private int statusCode;
	private Map<String, Object> result = new HashMap<String, Object>();
	private String comments;
	private T Data;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result.putAll(result);
	}
	
	public void setResult(String key, Object value) {
		result.put(key, value);
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
    
	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}

	@Override
	public String toString() {
		return String.format(
				"CommonResult [statusCode=%s, result=%s, comments=%s]",
				statusCode, result, comments);
	}

}
