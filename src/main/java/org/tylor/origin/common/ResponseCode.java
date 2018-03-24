package org.tylor.origin.common;

/**
 * 返回给调用方（客户端）返回码
 * 
 * @author dengyishi
 *
 *         2017年4月20日 下午7:19:06
 */
public enum ResponseCode {

	SUCCESS(0, "操作成功"),
	FAIL(1120005, "操作失败"),
	PARAM_ERROR(1120002, "请求参数错误"),
	ACCESS_DENY(1120001, "无权访问");
	private int value;
	private String msg;

	public int getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}

	ResponseCode(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}

}
