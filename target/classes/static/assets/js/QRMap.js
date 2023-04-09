function onGeoOk(position) {
    // 현재 위도 경도 = latitude, longitude
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    
    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(latitude, longitude),
        level: 3
    };
    var map = new kakao.maps.Map(container, options);
    
    // 마커 표시
    var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 
    
    var positions = [
        {
            title: '1코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.31601804268958, 129.2621948862194)
        },
        {
            title: '1코스 1구간 종점',
            latlng: new kakao.maps.LatLng(35.24446800428602, 129.22107893835832)
        },
        {
            title: '1코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.24455167204976, 129.22103728366892)
        },
        {
            title: '1코스 2구간 종점',
            latlng: new kakao.maps.LatLng(35.15722882704108, 129.1761829138613)
        },
        {
            title: '2코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.15727787116399, 129.1760613266045)
        },
        {
            title: '2코스 1구간 종점',
            latlng: new kakao.maps.LatLng(35.16113847032044, 129.13085344447376)
        },
        {
            title: '2코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.16146597698672, 129.13067763751818)
        },
        {
            title: '2코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.09964721696999, 129.1234750688315)
        },
        {
            title: '3코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.09965112722732, 129.1234576261709)
        },
        {
            title: '3코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.13511652851037, 129.05959901803928)
        },
        {
            title: '3코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.13513454409634, 129.05959947192764)
        },
        {
            title: '3코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.08135759567804, 129.04120146411205)
        },
        {
            title: '3코스 3구간 시작점',
            latlng: new kakao.maps.LatLng(35.08138281801318, 129.0412020926293)
        },
        {
            title: '3코스 3구간 끝점',
            latlng: new kakao.maps.LatLng(35.07558964116543, 129.07561872279874)
        },
        {
            title: '4코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.08138002670751, 129.0411537867144)
        },
        {
            title: '4코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.08412065286087, 128.9978164790027)
        },
        {
            title: '4코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.08410314214596, 128.99778535472444)
        },
        {
            title: '4코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.04499132857519, 128.9691522099807)
        },
        {
            title: '4코스 3구간 시작점',
            latlng: new kakao.maps.LatLng(35.04460308923821, 128.9690859080399)
        },
        {
            title: '4코스 3구간 끝점',
            latlng: new kakao.maps.LatLng(35.104989453392974, 128.95643518296967)
        },
        {
            title: '5코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.1049787140889, 128.95643053978662)
        },
        {
            title: '5코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.06562055728897, 128.8350070327021)
        },
        {
            title: '5코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.06560961370877, 128.83501555663207)
        },
        {
            title: '5코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.06562055728897, 128.8350070327021)
        },
        {
            title: '6코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.10480701285013, 128.95679489778672)
        },
        {
            title: '6코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.20616714888395, 128.9961601639987)
        },
        {
            title: '6코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.20611346264626, 128.99613689029172)
        },
        {
            title: '6코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.18348453337321, 129.04594371940033)
        },
        {
            title: '6코스 3구간 시작점',
            latlng: new kakao.maps.LatLng(35.206195974616385, 128.996160869714)
        },
        {
            title: '6코스 3구간 끝점',
            latlng: new kakao.maps.LatLng(35.24539090573453, 129.06374260331657)
        },
        {
            title: '7코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.183579549446236, 129.04586707065454)
        },
        {
            title: '7코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.24537588739941, 129.06356646546206)
        },
        {
            title: '7코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.24535051626426, 129.06357461025024)
        },
        {
            title: '7코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.26641021262345, 129.1125420544797)
        },
        {
            title: '8코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.26641021262345, 129.1125420544797)
        },
        {
            title: '8코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.217271513974865, 129.1188004319354)
        },
        {
            title: '8코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.21729711884776, 129.11877913581628)
        },
        {
            title: '8코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.16112405857129, 129.13085306844465)
        },
        {
            title: '9코스 1구간 시작점',
            latlng: new kakao.maps.LatLng(35.266417341722466, 129.11254663463475)
        },
        {
            title: '9코스 1구간 끝점',
            latlng: new kakao.maps.LatLng(35.27539355153746, 129.1789228901013)
        },
        {
            title: '9코스 2구간 시작점',
            latlng: new kakao.maps.LatLng(35.275357761474346, 129.1789087448025)
        },
        {
            title: '9코스 2구간 끝점',
            latlng: new kakao.maps.LatLng(35.24455871612959, 129.22104626330918)
        }
    ];

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

    for (var i = 0; i < positions.length; i ++) {
    
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35); 
        
        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
        
        var marker1 = new kakao.maps.Marker({
              map: map, // 마커를 표시할 지도
              position: positions[i].latlng, // 마커를 표시할 위치
              title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image : markerImage // 마커 이미지 
        });
}
    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
}

// 현 위치 검색 실패
function onGeoError() {
    alert("위치정보를 허가해 주세요!");
 }

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);