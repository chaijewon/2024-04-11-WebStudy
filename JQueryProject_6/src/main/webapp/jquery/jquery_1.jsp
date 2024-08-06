<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
       자바스크립트 : 태그를 제어 , 이벤트 처리 , 사용자 입력 제어 , 동적페이지 , 애니메이션 
       ========= 브라우저에서 제어 (클라이언트)
       
       자바스크립트 사용방법 
       1) 내부 스크립트 : 한개의 파일에서만 제어
          <head>
           <script type="text/javascript">
             함수 
             window.onload=function()
             {
             }
           </script>
          </head>
       2) 외부 스크립트 => <body> : 전체 파일에 적용 => 라이브러리 => cdn 
          <head> 
           <script type="" src="파일경로/URL">
          </head>
       3) 인라인 스크립트 => 태그만 제어
          <a href="javascript:함수호출">
          
          => jquery / vue / react / next => 자바스크립트 라이브러리 (자바스크립트로 처리)
          => mybatis / jpa / spring => 자바라이브러리 => 자바적용
       => 자바스크립트의 변수선언 
          let , var , const => 값적용에 따라 => 데이터형을 자동으로 설정
                      ===== 상수형 변수 => 자바에서 데이터 전송 (JSON)
                ===  scope를 명확하지 않다 ES5 => 메모리에 남아있다 (메모리 누수 현상)
          === scope를 명확하게 만들어 준다 ES6
              ===== 사용범위가 명확하다 
       => 자바스크립트에서 사용하는 데이터형 
          number : 숫자형 => int,double 
          string : 문자형 => char , string => '',""
          boolean : true / false => 숫자도 가능 => 0,0.0를 제외한 모든 숫자는 true
          array : 배열 => [] => object단위 => 데이터가 섞어서 사용이 가능 
          object : {} => {키:값,키:값...} => JSON
          undefined : 변수에 값이 없는 경우(초기화가 없는 상태)
          function : 함수 => 매개변수로 사용이 가능 => Callback 
                                               ========
                                               시스템에 의해 자동 호출 
                     map(function(){}),forEach(function())
                     $().click(function(){})
          =================== 확인시에는 typeof 변수명 
         => 연산자 
            산술연산자 : +,-,*,/,% 
                      +는 문자열 결합이 가능 
                      / 정수/정수=실수 
            단항연산자 : ++ , --
            =======================================
            비교연산자 : === , !== , < , > , <= , >=
            논리연산자 : && , || , !
            ======================================= 숫자 , 문자 , 날짜 
            대입연산자 : = , += , -= 
            형변환 연산자 => HTML에서 읽어오는 모든 데이터 문자열이다 
                    숫자 변환 : Number("10") , parseInt("10")
                    문자 변환 : String(10) 
                    논리 변환 : Boolean("true") , Boolean(1) = true 
         => 제어문 
            조건문 : if , if~else 
            반복문 : for,for of 
                   ===========
                   일반 for는 사용 
                   for of => map() , forEach()
            반복제어 : break 
            
         => 배열 : [] , 객체 : {}
         => RestFul => 다른 프로그램과 연동 
                       자바   <=> 자바스크립트 연동 
                       ~VO        {}
                       List       []  ==> JSON/JSONP/클로저/호이스팅 
                                          ====================== 면접 100%
         => 가장 많이 등장 
            함수 
            function 함수명(매개변수...){}
            let 함수명=function(){}
            let 함수명=()=>{}
            =============== vue/react
            
            useEffect(){
              이벤트를 설정 
              function 함수명(매개변수...){}(X)
              let 함수명=()=>{} (O)
            }
            
        자바스크립트에서 지원하는 기본 객체 
        String : 문자열 
          = substring(시작인덱스,끝인덱스) : 문자열를 자르는 경우 
            ===========       ===== -1
            => 인덱스번호는 0번부터 시작 
          = indexOf() : 찾기 
          = length()
          = split() : 분리 => []
          = trim() : 공백 제거 
        Number : 숫자 
          => 형변환 
          => NaN => 연산처리가 안된 경우 
        Array : 배열 
          = map() => for
          = push() => 배열 추가 
          = pop() => 마지막 제거 
          = slice(start,end) => 새로운 배열을 만든다 
          = ... 연산자 => 그대로 복사 
          [1,2,3,4,5]
          [...,6,7,8]
        Math : 수학 
          ceil(), round() , random()
        Date : 날짜 
               getFullYear() 
               getMonth()
               getDate()
               getDay() => 요일 읽기 
        =================================
        브라우저 객체 
                window : 브라우저 제어 => open(),close() , scrollbar
                  |
           ===================================
           |        |       |                |     
        location  document history         event
        화면이동     화면제어    기록정보         이벤트 처리
        href      write     back(),go()    => function aaa(event)
                 querySelector()
       ===========================================================
       => 라이브러리 
          jquery : 단순하게 만든 라이브러리 => 사용빈도가 욕심 => 라이브러리가 방대(속도 저하)
           => 3 
       ==========================================================
       jquery : 대부분이 브라우저에서 사용이 가능 (호환성) 
       = HTML (DOM=트리형태)를 쉽게 조작이 가능 , 간단하게 CSS적용이 가능 
         예)
            let a=document.querySelector("a")
            a.style.backgorund='red'
            
            $('a').css('background','red')
            ------
             document.querySelector("a")
          
       = 오픈 소스 : 사용이 자유롭다 => 이전 개발 (유지보수) 
       = 속도 속도가 늦다 => 가상돔 (가상메모리) 
                        ============== Vue,React 
       = 추세 : Front / Back 
              ============== Full Stack => 모바일 
               AI 
               
               React : 42%
               Vue3 : 38% 
               jquery : 12%
               Typescript 
       문법 형식)
        자바스크립트 
          main: window.onload=function(){}
        Jquery 
          main: $(function(){}) , $(document).ready(function(){})
                =============== 
        Vue3 
          main: new Vue({
                   mounted:function(){}
                })
        React : class Main extends Component
                {
                   componentDidMount(){}
                }
                => HOOKS
                function main()
                {
                  useEffect(()=>{
                    
                  })
                }
          React-Query : 서버연결 ********
       ============================================
       
       제이쿼리 : 태그를 제어하는 프로그램 (태그 가지고 오는 방법) 
               값 , 속성 , CSS , 이벤트 제어 
               $('태그명').처리 
               $('아이디명').처리 
               .......... 선택자 
       1) 값을 읽기 (태그)
          val() / val(값) : input , select , textarea
          text() / text(값) : 모든 태그 : <태그>값설정</태그> :문자열 
          html() / html() : HTML을 적용 => 단일
          append() / append() : 여러개 동시에 사용 
       2) 제어 (이벤트)    
          click 
          change
          hover
          keyup
          keydown
          
       3) Ajax 
          브라우저 : HttpXMLRequest가 존재
                   ============== 서버 연결 => 서버 응답 
          => if(httpRequest.readyState==4)
             {
                if(httpRequest.status==200)
                {
                }
             }
             => success:function()
                {
                }
          => $.ajax({
                type: => get/post
                url: => 실행할 JSP를 지정 
                data: => ? => {키:값}
                success:function()
                {
                   // 정상 수행 
                }
                error:function(e)
                {
                   // 서버에서 오류 발생 
                }
             })
             
           => axios.get('url',{ => Vue,React
               params:{
                 키 : 값
               }
            }
           ).then(res=>{
              결과값 받기
           })
              axios.post()
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>