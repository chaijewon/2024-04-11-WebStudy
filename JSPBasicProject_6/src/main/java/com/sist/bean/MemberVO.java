package com.sist.bean;

import lombok.Data;
// VO (Value Object), DTO(Data Transfor Object) , Bean => 같은 개념 
// Spring MyBatis JSP => 데이터를 모아서 한번에 전송할 목적 
// 변수를 제어 => 읽기(getter) / 쓰기(setter) 
// => 데이터를 해킹 방지 => 숨겨서 보내준다 => 캡슐화 => 데이터를 보호할 목적 
@Data
public class MemberVO {
  private String name,sex,address,phone;
}
