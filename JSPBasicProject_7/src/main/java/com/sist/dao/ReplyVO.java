package com.sist.dao;
import java.util.*;

import lombok.Data;
@Data
public class ReplyVO {
   private int rno,fno,type;
   private String id,name,msg,dbday;
   private Date regdate;
}
