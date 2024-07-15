package com.sist.dao;
/*
 *   NO                                        NOT NULL NUMBER
	 NAME                                      NOT NULL VARCHAR2(50)
	 SUBJECT                                   NOT NULL VARCHAR2(2000)
	 CONTENT                                   NOT NULL CLOB
	 PWD                                       NOT NULL VARCHAR2(10)
	 REGDATE                                            DATE
	 HIT                                                NUMBER
	 FILENAME                                           VARCHAR2(260)
	 FILESIZE                                           NUMBER
 */
/*
 *    데이터베이스 연결 데이터값을 저장한후 한번에 브라우저로 전송할 목적 
 *    => 데이터를 보호 : 캡슐화 
 *       => 변수 : private 
 *    => 사용이 가능하게 만든다 
 *       변수 : 읽기/쓰기 => 기능을 만들에서 처리 
 *            ======== getter/setter 
 *    => JSP : Bean
 *    => 생성시에 사용하는 태그 
 *       <jsp:useBean> 
 *       <jsp:setProperty> => MVC(Spring) 사용빈도가 없다 
 *       
 *    => 매칭 : 데이터베이스 컬럼
 *             크롤링 데이터 매칭 
 *       데이터베이스 
 *         문자형 : CHAR , VARCHAR2, CLOB => String
 *         숫자형 : NUMBER => NUMBER(8,2) => 저장된 데이터 확인 => int / double
 *         날짜형 : Date => java.util.Date
 */
import java.util.*;

import lombok.Data;
@Data
public class DataBoardVO {
    private int no,filesize,hit;
    private String name,subject,content,pwd,filename,dbday;
    private Date regdate;
}
