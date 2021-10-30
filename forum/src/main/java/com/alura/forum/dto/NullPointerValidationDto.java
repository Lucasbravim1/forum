package com.alura.forum.dto;

public class NullPointerValidationDto {

	private String field;

	private String message;

	public NullPointerValidationDto(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NullPointerValidationDto [field=" + field + ", message=" + message + "]";
	}

}
