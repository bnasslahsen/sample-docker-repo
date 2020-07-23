package fr.training.samples.spring.shop.common;

public class ErrorModel {

	private String id;

	private String message;

	public ErrorModel(String id, String message2) {
		this.id = id;
		this.message = message2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
