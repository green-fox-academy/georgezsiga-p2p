package com.greenfox.Model;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by georgezsiga on 5/24/17.
 */
public class LogEntry {

  String path;
  String method;
  Map<String, String[]> requestData;

  public String toString() {
    String data = "";
    for (Entry<String, String[]> entry: this.requestData.entrySet()) {
      data += entry.getKey() + "=" + Arrays.toString(entry.getValue());
    }

    return " Request " + this.path + " " + this.method + " " + data;
  }

  public LogEntry(String path, String method, Map<String, String[]> requestData) {
    this.path = path;
    this.method = method;
    this.requestData = requestData;
  }
}
