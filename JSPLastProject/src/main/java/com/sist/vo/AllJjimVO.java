package com.sist.vo;
/*
 *   JNO     NOT NULL NUMBER       
CNO              NUMBER       
TYPE             NUMBER       
ID               VARCHAR2(20) 
REGDATE          DATE 
 */
import java.util.*;

import lombok.Data;
@Data
public class AllJjimVO {
  private int jno,cno,type;
  private String id,dbday;
  private Date regdate;
  private FoodVO fvo=new FoodVO();
}
