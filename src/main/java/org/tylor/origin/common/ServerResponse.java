package org.tylor.origin.common;

import lombok.Data;

/**
 * @author liubin10  2018/3/21
 */
@Data
public class ServerResponse<T> {

    String msg;
    Integer code;
    Boolean success;
    T data;

    public ServerResponse() {
        this(ResponseCode.SUCCESS);
    }
    public ServerResponse(Exception e) {
        this(ResponseCode.FAIL);
        this.msg = e.getMessage();
    }

    public ServerResponse(T data) {
        this(ResponseCode.SUCCESS);
        this.data = data;
    }

    public ServerResponse(ResponseCode code) {
        this.msg = code.getMsg();
        this.code=code.getValue();
        if (this.code.intValue()==0){
            success=true;
        }else {
            success=false;
        }

    }
}
