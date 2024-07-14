package com.sist.dao;
import java.util.*;
/*
 *  NO                                        NOT NULL NUMBER
 NAME                                      NOT NULL VARCHAR2(50)
 SUBJECT                                   NOT NULL VARCHAR2(2000)
 CONTENT                                   NOT NULL CLOB
 PWD                                       NOT NULL VARCHAR2(10)
 REGDATE                                            DATE
 HIT                                                NUMBER
 GROUP_ID                                           NUMBER
 GROUP_STEP                                         NUMBER
 GROUP_TAB                                          NUMBER
 ========================= 출력 ===============================
 ROOT                                               NUMBER
 DEPTH                                              NUMBER
 ========================= 삭제 ===============================
    AAAAA
      =>관리자 삭제한 게시물입니다
       =>CCCCCC
 */
public class BoardVO {
   private int no,hit;
   private String name,subject,content,pwd;
   private Date regdate;
   private int group_id; // 같은 답변을 모아서 관리 
   private int group_step; // 답변 순서 지정 
   private int group_tab;// 공백 => 
   private int root; // 어떤 게시물에 대한 답변 확인
   private int depth; // 게시물 몇개의 답변이 있는지 확인 
   // 관리자 / 사용자 => 묻고 답하기 (사이트 필수) => 실시간 채팅
}
