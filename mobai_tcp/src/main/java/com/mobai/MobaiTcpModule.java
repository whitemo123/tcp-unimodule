package com.mobai;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.mobai.utils.OutJSON;
import com.mobai.utils.SocketTransceiver;
import com.mobai.utils.TcpClient;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import io.dcloud.feature.uniapp.common.UniModule;

public class MobaiTcpModule extends UniModule {

  private String ip;
  private String port;

  private JSCallback jsCallback;

  private TcpClient tcpClient = new TcpClient() {
    @Override
    public void onConnect(SocketTransceiver transceiver) {
      Log.i("Mobai", "连接成功");
      jsCallback.invokeAndKeepAlive(JSONObject.toJSONString(new OutJSON(0, "连接成功", "")));
    }

    @Override
    public void onConnectFailed() {
      jsCallback.invokeAndKeepAlive(JSONObject.toJSONString(new OutJSON(0, "连接失败", "")));
    }

    @Override
    public void onReceive(SocketTransceiver transceiver, String s) {
      jsCallback.invokeAndKeepAlive(JSONObject.toJSONString(new OutJSON(1, "收到消息", s)));
    }

    @Override
    public void onDisconnect(SocketTransceiver transceiver) {
      jsCallback.invokeAndKeepAlive(JSONObject.toJSONString(new OutJSON(0, "连接断开", "")));
    }
  };

  /**
   * 发送tcp 消息
   * @param jsonObject
   * @param jsCallback
   */
  @JSMethod(uiThread = false)
  public void connect(JSONObject jsonObject, JSCallback jsCallback) {
    String ip = jsonObject.getString("ip");
    int port = jsonObject.getIntValue("port");

    this.jsCallback = jsCallback;
    Log.i("Mobai", "执行这里");

    tcpClient.connect(ip, port);
  }

  @JSMethod(uiThread = false)
  public void sendStr(JSONObject jsonObject) {
    String msg = jsonObject.getString("message");
    tcpClient.getTransceiver().send(msg);
  }
}
