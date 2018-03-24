package org.tylor.origin.common;

/**
 * 客户端异常类
 * 
 * @author dengyishi
 *
 *         2017年4月20日 下午7:17:27
 */
public class ClientException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String msg;
	int code;

	public ClientException(int i, String string) {
		super(string);
		this.msg = string;
		this.code = i;
	}

	public ClientException(int i, String string, Throwable cause) {
		super(string, cause);
		this.msg = string;
		this.code = i;
	}

	public ClientException() {
		this(ResponseCode.FAIL);

	}

	/**
	 * @param msg
	 */
	public ClientException(String msg) {
		this.msg = msg;
		this.code = ResponseCode.FAIL.getValue();
	}

	public ClientException(ResponseCode responseCode, Throwable cause) {
		this(responseCode.getValue(), responseCode.getMsg(), cause);
	}

	public ClientException(ResponseCode responseCode) {
		this(responseCode.getValue(), responseCode.getMsg());
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
