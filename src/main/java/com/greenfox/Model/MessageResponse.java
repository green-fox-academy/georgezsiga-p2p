package com.greenfox.Model;

import java.util.List;

/**
 * Created by georgezsiga on 5/27/17.
 */
public class MessageResponse {

  List<Message> messages;
  Client client;

  public MessageResponse() {
  }

  public MessageResponse(List<Message> messages, Client client) {
    this.messages = messages;
    this.client = client;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Client getClient() {
    return client;
  }
}

