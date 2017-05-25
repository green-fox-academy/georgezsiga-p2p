package com.greenfox.Model;

/**
 * Created by georgezsiga on 5/25/17.
 */
public enum WhoToSend {

  VIKTOR("viktor", "https://chat-p2p.herokuapp.com/api/message/receive"),
  PATRIK("patrik", "https://phorv1chatapp.herokuapp.com/api/message/receive"),
  RAMIN("ramin", "https://greenfox-chat-app.herokuapp.com/api/message/receive"),
  NORA("nora", "https://peertopeerchat.herokuapp.com/api/message/receive"),
  ZSOLT("zsolt", "https://p2p-by-nagyza.herokuapp.com/api/message/receive"),
  MILAN("milan", "https://p2pchat-garlyle.herokuapp.com/api/message/receive"),
  PETER("peter", "https://sleepy-harbor-14996.herokuapp.com/api/message/receive"),
  MARCI("marci", "https://p2p-chat-seed0forever.herokuapp.com/api/message/receive");

  public final String name;
  public final String url;

  WhoToSend(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return url;
  }
}
