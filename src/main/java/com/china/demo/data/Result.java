package com.china.demo.data;


public class Result<T> {

	private int resultCode = 200;

	private String message = "SUCCESS";

	private T data;

    /**
     * 只返回错误码
     *
     * @param resultCode resultCode
     */
    public Result(int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 只有返回数据的(验证成功)
     *
     * @param data data
     */
    public Result(T data) {
        this.data = data;
    }

    /**
     * 只有错误码和错误信息的
     *
     * @param resultCode resultCode
     * @param message    message
     */
    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }


    /**
     * 全部参数
     *
     * @param resultCode resultCode
     * @param message    message
     * @param data       data
     */
    public Result(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
    
}
