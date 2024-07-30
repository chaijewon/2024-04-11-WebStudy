<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    변수 설정 => 식별자 
      1) 알파벳으로 시작한다 (대소문자 구분)
      2) 숫자 사용이 가능 (앞에 사용 금지) 
      3) 키워드는 사용할 수 없다 
         (if , var , let , const..)
      4) 특수문자 사용이 가능 (  _ , $ ) _를 주로 사용
      5) 변수명에 공백은 사용할 수 없다 
      <script>
         // 한줄 주석 
         /* */ 여러줄 주석 
      </script>
      let / const  ==> 자동 변수 지정 
      ============
       문장이 종료가 되면 ; => ;을 사용하지 않는 것을 권장 
       
       [] 배열 =========> List
       {} 객체 =========> VO
       
       자바스크립트에서 사용하는 데이터형 
       1. number : 정수, 실수 
       2. string : '' , ""
       3. boolean : true/false , 0이나 0.0이 아닌 수는 true 
       4. object : [] 배열 , {} 객체 => JSON
       5. null : 값이 없는 경우 => [] , {}
       6. undefined : 변수에 값이 없는 경우 
       *** 데이터형 확인 : typeof 변수명 
       
       *** 변수는 언제든 다른 데이터형으로 변경이 가능 
           let i=10
           i=""
           i={}
           i=[]
       배열은 object형이기 때문에 데이터형이 혼합이 될 수 있다 
       
       변수 설정 
       === 메모리 저장 공간 (한개만 저장이 가능) => let / var / const(상수)
       연산자 처리 
       제어문 처리 
       ======== + 함수 => 함수 여러개를 모아서 처리 => 객체 
       
       2. 연산자 처리 
          = 단항연산자 
            1) 증감 연산자 (++ , --) 
            2) 형변환연산자 
               => "10" => Number("10") 정수형 변경 => parseInt("10")
               => String(10) => "10"
               => Boolean(1) => true , Boolean(0) => false
          = 산술연산자 ( + , - , * , / , % ) 
                     === 문자열 결합
          = 비교연산자 
            === 같다 
            !== 같지않다 
            < , > , <= , >=
            
          = 논리연산자 
            && 
            ||
            !
          = 대입연산자 / 복합 대입연산자 
            =         += , -= ...
          = 삼항연산자 
            (조건)?값1:값2 ==> true => 값1
                            false => 값2 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	/*let a=10;
	console.log("a="+a)
	a++
	console.log("a="+a)
	
	let b=10;
	console.log("b="+b)
	b--
	console.log("b="+b)*/
	let c=10
	let d=c++
	// c는 d에 값을 대입후에 c는 한개를 증가 
	document.write("c="+c+",d="+d+"<br>") // 브라우저에 출력 
	/*
	        window (브라우저)
	          |
	      ==================
	      |        |       |
	    document  history location
	   브라우저의 출력되는 영역 
	      |
	     form 
	*/
	c=10;
	d=++c;
	document.write("c="+c+",d="+d+"<br>") 
	
	// 부정 연산자 : boolean => true=>false , false=>true
	let k=false
	document.write("변경전 k="+k+"<br>")
	k=!k
	document.write("변경후 k="+k+"<br>")
	
	// 형변환 연산자 
	let m=1  // m:number 
	document.write("변경전 m="+m+","+typeof m+ "<br>")
	m=!m // boolean으로 변환 (!=boolean)
	document.write("변경후 m="+m+","+typeof m+ "<br>")
	// 0 , 0.0이 아닌 모든 숫자는 true 
	// number , boolean는 언제든 변경이 가능 
	document.write(Boolean(1)+","+Boolean(0)+","+Boolean(1.0)+","+Boolean(0.0)+"<br>")
	let n="10"
	document.write("n="+typeof n+"<br>") // string
	document.write("n="+typeof Number(n)+"<br>") // number
	document.write("n="+typeof parseInt(n)+"<br>")//number
	document.write("n="+typeof Boolean(n)+"<br>")//boolean
	
	let p=10
	document.write("p="+typeof p+"<br>") // number
	document.write("p="+typeof String(p)+"<br>") // string
	document.write("p="+typeof Boolean(p)+"<br>")//boolean => true
	
	/*
	   형변환 연산자 : 브라우저에 값을 가지고 오는 경우 => 문자열로 읽어 온다 
	                                         ==============
	                                         필요시에는 숫자형으로 변환후에 사용
	   => 문자열 변환 : String()
	   => 문자열 결합 : +
	   => 숫자 => 0,0.0이외는 true 
	   => 문자열 => null을 제외하고 true 
	*/
	let name="홍길동"
    document.write("name="+typeof name+"<br>")
    document.write("name="+typeof Boolean(name)+","+Boolean(name)+"<br>")
    
    let addr=null // undefined => 배열 , JSON 
    document.write("addr="+typeof addr+"<br>")
}
</script>
</head>
<body>

</body>
</html>