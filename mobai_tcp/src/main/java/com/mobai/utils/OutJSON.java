package com.mobai.utils;

public class OutJSON {
  private int code;
  private String msg;
  private Object data;

  public OutJSON(int code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public Object getData() {
    return data;
  }
}
