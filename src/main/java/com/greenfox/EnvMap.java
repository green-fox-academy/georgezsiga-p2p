package com.greenfox;

import java.util.Map;

/**
 * Created by georgezsiga on 5/17/17.
 */
public class EnvMap {


  public static void main (String[] args) {
//    ProcessBuilder pb =
//        new ProcessBuilder();
//    Map<String, String> env = pb.environment();
//    env.put("VAR1", "myValue");
//    System.getenv().putIfAbsent("CHAT_APP_LOGLEVEL", "INFO");
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.format("%s=%s%n",
          envName,
          env.get(envName));
    }
  }
}
