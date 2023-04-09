// 코스정보 테이블에 위동경도 넣어야 계산가능

function onGeoOk(position) {
    // 현재 위도 경도 = latitude, longitude
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
	
    // db에서 받아온 위경도, url파라미터 값
    const latlong = document.getElementById('latlong').innerText;
    const gugan = document.getElementById('gugan').innerText;
    const pos = document.getElementById('pos').innerText;
    const QRFlag = document.getElementById('QRFlag').innerText;

    console.log(latlong);
    console.log(gugan);
    console.log(pos);
    console.log(QRFlag);
    console.log(`You live in ${latitude} and ${longitude}`);

    const startEnd = latlong.split(',');
    console.log(startEnd[0], startEnd[1]);
    
    // 시작 url 일때 값과 종점 url일때 값 분리
    if(pos=='start'){
        if(QRFlag == '0'){
        var lat1 = startEnd[0];
        var lng1 = startEnd[1];
        console.log("before lat1=="+lat1);
        console.log("before lng1="+lng1);
        getDistanceFromLatLonInKm(lat1, lng1, QRFlag, gugan);
         }
         else {
            location.href="/QR/QRFail";
         }
        // start 유무 판별 후 넘기길 요망

    }else if(pos == 'end'){
        if(QRFlag == '1'){
        var lat1 = startEnd[2];
        var lng1 = startEnd[3];
        getDistanceFromLatLonInKm(lat1, lng1, QRFlag, gugan);

        }
        else if(QRFlag=='0') {
            location.href="/QR/QRFail";
        }
    }
    
    function getDistanceFromLatLonInKm(lat1, lng1, QRFlag, gugan) {
        var distance = 0;
        // 테이블에 있는 위도경도1, 현 위치 위도경도2

        function deg2rad(deg) {
            return deg * (Math.PI/180)
        }
        
        var r = 6371; //지구의 반지름(km)
        var dLat = deg2rad(latitude-lat1);
        var dLon = deg2rad(longitude-lng1);
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(latitude)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var d = r * c; // Distance in km
        distance = Math.round(d*1000);

            // 거리가 멀면 fail 가까울때 start
            console.log("gugan=="+gugan);
            console.log("lat1=="+lat1);
            console.log("lng1="+lng1);
            console.log("QRFlag="+QRFlag);
            console.log("distance="+distance); // m 단위
            if (distance < 200000){ // 200km 거리 밖이면 실패 나중에 200m 정도로 조정 할 예정
                    console.log("======================");
                if(QRFlag=='0'){
                    console.log("======================");
                    const QRFlag1= '1';
                    const getPoint= '0';
                    send(QRFlag1, getPoint, gugan);
                    location.href="/QR/QRStart?gugan="+gugan+"&pos="+pos+"&distance="+distance;           
                }
                if(QRFlag=='1'){
                    const QRFlag1= '0';
                    const getPoint= '2000';
                    send(QRFlag1, getPoint, gugan);
                    location.href="/QR/QREnd?gugan="+gugan+"&pos="+pos+"&distance="+distance;
                }
            }
            else {
                location.href="/QR/QRFail";
            }
            return distance;
    }
}

function send(QRFlag1, getPoint, gugan) {
    $.ajax({
        url : "/QR/QRLink",
        type : 'post',
        data : {
        	gugan: gugan,
            QRFlag: QRFlag1,
            rewardPoint: getPoint
        },
        success : function(data) {
            //alert("success");
        },
        error : function() {
            //alert("error");
        }
    });
}

// 현 위치 검색 실패
function onGeoError() {
    alert("위치정보를 허가해 주세요!");
 }
navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);