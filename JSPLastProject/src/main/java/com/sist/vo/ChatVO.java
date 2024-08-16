package com.sist.vo;

import javax.websocket.Session;

import lombok.Data;

@Data
public class ChatVO {
   private String id;
   private String name;
   private Session session;
}
