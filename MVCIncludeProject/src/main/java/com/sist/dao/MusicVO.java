package com.sist.dao;

import lombok.Data;

/*
 *   MNO                                       NOT NULL NUMBER
	 TITLE                                     NOT NULL VARCHAR2(2000)
	 SINGER                                    NOT NULL VARCHAR2(2000)
	 ALBUM                                              VARCHAR2(2000)
	 POSTER                                    NOT NULL VARCHAR2(300)
	 STATE                                              CHAR(12)
	 IDCREMENT                                          NUMBER
	 KEY                                                VARCHAR2(200)
	 
	 => @RequestMapping() : GET/POST 동시 처리  => Spring 4
	     => GetMapping()  => Spring 5
	     => PostMapping() => Spring 5
	                         ========= XML => 순수자바
	 => <jsp:include> => tiles 
 */
@Data
public class MusicVO {
   private int mno,idcrement;
   private String title,singer,album,poster,state,key;
}
