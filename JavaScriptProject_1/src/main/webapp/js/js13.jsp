<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script type="text/javascript">
    window.onload=()=>{
    let temp = document.querySelector('#temp');
    let min = document.querySelector('#min');
    let max = document.querySelector('#max');
    let wind = document.querySelector('#wind');
    let weather = document.querySelector('#weather');
    let icon = document.querySelector("#icon");
    let icon_url = "https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/";

    axios.get('https://api.openweathermap.org/data/2.5/find?q=Seoul&units=metric&appid=7d96bc5108f52b80e2d9075a369b9f35')
      .then(function(response) {
        console.log(response.data);
        let wdata = response.data.list[0];
        let exdata = response.data.list[0].weather[0];
        
        temp.innerHTML = wdata.main.temp + "°C";
        min.innerHTML = wdata.main.temp_min;
        max.innerHTML = wdata.main.temp_max;
        wind.innerHTML = wdata.wind.speed;

        weather.innerHTML = exdata.main + "," + exdata.description;
        icon.setAttribute('src', icon_url + exdata.icon + ".png");
      })
      .catch(function(error) {
        console.log(error);
      })
      .then(function() {});
    }
    </script>
    <title>실시간 날씨 읽기</title>
  </head>

  <body>
    <div class="container w-75 mt-5 p-5 shadow text-center bg-warning text-dark">
      <H2>오늘의 날씨</H2>
      <hr>
      <div class="row bg-success p-5 rounded-circle">
        <div class="col-5">
          <img id="icon">
        </div>
        <div class="col-7 m-auto">
          <h1 class="display-3" id="temp"></h1>
          <strong id="weather"></strong><br> 
          최저: <span id="min"></span>°C, 최고: <span id="max"></span>°C<br> 
          바람: <span id="wind"></span>m/s
        </div>
      </div>
    </div>
  </body>

</html>