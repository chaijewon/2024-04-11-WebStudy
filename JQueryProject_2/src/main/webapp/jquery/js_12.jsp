<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    JSON : JavaScript Object Notaton
           자바스크립트 객체 표현법 
           => 자바 ==> 데이터를 자바스크립트로 전송 
                 JSON
                 ==== 1. 웹에서 자바스크립트 
                      2. 모바일 (kotlin)
           => key,value 구성 => key는 중복이 불가능하다 
              ========= map
              JSON => 라이브러리를 주로 사용 : json-simple (google) , jackson(boot에 자동 첨부)
               => 데이터를 JSON으로 받아서 출력 : ajax , vue , react , next
           => 목적 : 클라이언트와 서버의 데이터 교환 목적 (XML => JSON)
                                                | Annotation
              요청값 전송  
              ========
                => ajax : data:{id:'aaa',pwd:'1234'}
                => axios : params:{id:'aaa',pwd:'1234'} => react-query,vue3
              응답 받기
                => JSON => parser : 데이터를 HTML을 이용해서 출력 
                => 서버에서는 JSON을 문자열 형식 
                                 ======== 자바스크립트 : JSON.parse()
              형식) 
                  {"sabun":1,"name":"홍길동","detp":"개발부"}
                  {"sabun":1,"name":"홍길동"}
                  {"name":"홍길동","detp":"개발부"} => 동일하지 않을 수도 있다 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	let info={name:"홍길동",age:20,sex:"남자"}
	let data=JSON.stringify(info) // JSON => 문자열 변환 
	console.log(data)
	let change=JSON.parse(data) // 문자열 => JSON으로 변경
	console.log(change)
}
</script>
</head>
<body>

</body>
</html>