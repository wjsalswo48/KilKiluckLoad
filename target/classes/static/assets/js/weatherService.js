const API_KEY = 'a8bad63ced0dc03a3d2babbe6d53f74e';

 // 현재 위도 경도 위치 
function onGeoOk(position) {
   const latitude = position.coords.latitude;
   const longitude = position.coords.longitude;
 
    console.log(`You live in ${latitude} and ${longitude}`);
 
   fetch(
      `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${API_KEY}&units=metric`,
   )
      .then(response => response.json())
      .then(data => console.log(`온도 : ${data.main.temp}, 날씨 : ${data.weather[0].main}`));
    
      // 아이콘
      var weatherIcon = {
        '01' : 'fas fa-sun',
        '02' : 'fas fa-cloud-sun',
        '03' : 'fas fa-cloud',
        '04' : 'fas fa-cloud-meatball',
        '09' : 'fas fa-cloud-sun-rain',
        '10' : 'fas fa-cloud-showers-heavy',
        '11' : 'fas fa-poo-storm',
        '13' : 'far fa-snowflake',
        '50' : 'fas fa-smog'
    };
    
    // openweatherapi 날씨
    $.ajax({
        url: `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${API_KEY}&units=metric`,
        dataType: "json",
        type: "GET",
        async: "false",
        success: function(resp) {
    
            var $Icon = (resp.weather[0].icon).substr(0,2);
            var $weather_description = resp.weather[0].main;
            var $Temp = Math.floor(resp.main.temp) + 'º';
            var $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + resp.main.humidity+ ' %';
            var $wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' +resp.wind.speed + ' m/s';
            var $city = resp.name;
            var $cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + resp.clouds.all +"%";
            var $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_min) + 'º';
            var $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_max) + 'º';
            
    
            $('.weather_icon').append('<i class="' + weatherIcon[$Icon] +' fa-5x" style="height : 50%; width : 50%;"></i>');
            $('.weather_description').prepend($weather_description);
            $('.current_temp').prepend($Temp);
            $('.humidity').prepend($humidity);
            $('.wind').prepend($wind);
            $('.city').append($city);
            $('.cloud').append($cloud);
            $('.temp_min').append($temp_min);
            $('.temp_max').append($temp_max);               
        }
    })
}

// 현 위치 검색 실패
function onGeoError() {
   alert("위치정보를 허가해 주세요!");
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);