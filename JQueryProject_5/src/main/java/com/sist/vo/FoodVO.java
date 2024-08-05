package com.sist.vo;

import lombok.Data;

/*
 *   자바 / 오라클 / JSP / ***Spring / ***Spring-Boot 
 *          | JDBC / DBCP / ***MyBatis / JPA 
 *   ======================================== Back-End (추천 => 85%)
 *   HTML / CSS 
 *     => 화면 제작 , CSS 변경 
 *   JavaScript : JQuery (AJAX) , **VueJS , **ReactJS , NodeJS , NextJS , NuxtJS
 *   =========== TypeScript 
 *   
 *   FNO                                       NOT NULL NUMBER
	 NAME                                      NOT NULL VARCHAR2(200)
	 TYPE                                      NOT NULL VARCHAR2(200)
	 PHONE                                              VARCHAR2(30)
	 ADDRESS                                            VARCHAR2(700)
	 SCORE                                              NUMBER(2,1)
	 THEME                                              CLOB
	 POSTER                                    NOT NULL VARCHAR2(260)
	 CONTENT                                            CLOB
 *   
 */
@Data
public class FoodVO {
   private int fno;
   private String name,type,phone,address,theme,poster,content;
   private double score;
}
