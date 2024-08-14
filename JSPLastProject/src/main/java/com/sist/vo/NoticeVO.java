package com.sist.vo;

import lombok.Data;

/*
 *  NO      NOT NULL NUMBER         
TYPE             NUMBER         
NAME             VARCHAR2(50)   
SUBJECT          VARCHAR2(2000) 
CONTENT          CLOB           
REGDATE          DATE           
HIT              NUMBER         

 */
import java.util.*;
@Data
public class NoticeVO {
  private int no,type,hit;
  private String name,subject,content,dbday,notice_type;
  private Date regdate;
}
