<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>제이쿼리 - 시각효과메서드</title>
        <style>
            div{
                position: relative;
                width: 100px; height: 100px;
                background-color: orange;
            }
        </style>
        <script src="http://code.jquery.com/jquery.js"></script>
        <script>
            $(function(){
                $('button').click(function(){
                    $('div').animate({
                        left: 300,
                        top: 300
                    },'slow');
                });
            });
        </script>
    </head>
    <body>
        <button>이동</button>
        <hr>
        <div></div>
    </body>
</html>