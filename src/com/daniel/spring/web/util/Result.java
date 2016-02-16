package com.daniel.spring.web.util;

public class Result {
	
	private ResultType type;
	private String message;
	
	private Result() {}
	
	private Result(ResultType type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return type == ResultType.SUCCESS;
	}
	
	public boolean isFailure() {
		return type == ResultType.FAILURE;
	}

	public String getMessage() {
		return message;
	}

	public static Result getInstance(ResultType type, String message) {
		return new Result(type, message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [type=");
		builder.append(type);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		
		Result res = Result.getInstance(ResultType.SUCCESS, "This was successful!");
		Result ano = Result.getInstance(ResultType.FAILURE, "Something was wrong with that.");
		
		System.out.println(res.isSuccess());
		System.out.println(ano.isSuccess());
		System.out.println(ano.getMessage());
		
		
	}
		
}
