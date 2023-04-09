var map;
var mapContainer;
var coords;
window.onload = function() {

	var adress = document.getElementById('Adress').value;
	var maprestaurantID = document.getElementById('id').value;
	var mapContainer = document.getElementById('map'),
		mapOption = {
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level: 3
		};


	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapCenter = new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심 좌표
		mapOption = {
			center: mapCenter, // 지도의 중심 좌표
			level: 4 // 지도의 확대 레벨
		};

	map = new kakao.maps.Map(mapContainer, mapOption)

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();


	geocoder.addressSearch(adress, function(result, status) {

		// 정상적으로 검색이 완료됐으면
		if (status === kakao.maps.services.Status.OK) {

			coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			// document.getElementById('long').innerHTML=result[0].x;
			// document.getElementById('lat').innerHTML=result[0].y;
			console.log(result[0].y);
			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: '<div style="width:150px;text-align:center;padding:6px 0;">'+maprestaurantID+'</div>'
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}


		var rvContainer = document.getElementById('roadview'); // 로드뷰를 표시할 div
	var rv = new kakao.maps.Roadview(rvContainer); // 로드뷰 객체 생성
	var rc = new kakao.maps.RoadviewClient(); // 좌표를 통한 로드뷰의 panoid를 추출하기 위한 로드뷰 help객체 생성
	var rvResetValue = {} //로드뷰의 초기화 값을 저장할 변수
	rc.getNearestPanoId(coords, 50, function(panoId) {
		rv.setPanoId(panoId, coords);//좌표에 근접한 panoId를 통해 로드뷰를 실행합니다.
		rvResetValue.panoId = panoId;
	});

	// 로드뷰 초기화 이벤트
	kakao.maps.event.addListener(rv, 'init', function() {

		// 로드뷰에 올릴 마커를 생성합니다.
		var rMarker = new kakao.maps.Marker({
			position: coords,
			map: rv //map 대신 rv(로드뷰 객체)로 설정하면 로드뷰에 올라갑니다.
		});

		// 로드뷰에 올릴 장소명 인포윈도우를 생성합니다.
		var rLabel = new kakao.maps.InfoWindow({
			position: coords,
			content: maprestaurantID
		});
		rLabel.open(rv, rMarker);

		// 로드뷰 마커가 중앙에 오도록 로드뷰의 viewpoint 조정 합니다.
		var projection = rv.getProjection(); // viewpoint(화면좌표)값을 추출할 수 있는 projection 객체를 가져옵니다.

		// 마커의 position과 altitude값을 통해 viewpoint값(화면좌표)를 추출합니다.
		var viewpoint = projection.viewpointFromCoords(rMarker.getPosition(), rMarker.getAltitude());
		rv.setViewpoint(viewpoint); //로드뷰에 뷰포인트를 설정합니다.

		//각 뷰포인트 값을 초기화를 위해 저장해 놓습니다.
		rvResetValue.pan = viewpoint.pan;
		rvResetValue.tilt = viewpoint.tilt;
		rvResetValue.zoom = viewpoint.zoom;
	});

	//Geolocation api 사용 위치 정보를 얻기
	navigator.geolocation.getCurrentPosition(function(pos) {

   	var nowlat = pos.coords.latitude;
   	var nowlng = pos.coords.longitude;
    var restlat =	result[0].y;
    var restlng = result[0].x;

    //  document.getElementById('nowlat').innerHTML=nowlat;
    //  document.getElementById('nowlng').innerHTML=nowlng;
    // console.log("restlat = "+restlat);
    // console.log("restlng = "+restlng);
    // console.log("nowlat = "+nowlat);
    // console.log("nowlng = "+nowlng);
	// var mrst;
    // var radLat1 = Math.PI * nowlat / 180;
    // var radLat2 = Math.PI * restlat / 180;
    // var theta = nowlng - restlng;
    // var radTheta = Math.PI * theta / 180;
    // var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
    // if (dist > 1)
    //     dist = 1;
    //
    // dist = Math.acos(dist);
    // dist = dist * 180 / Math.PI;
    // dist = dist * 60 * 1.1515 * 1.609344 * 1000;
    // if (dist < 100) {
	// dist = Math.round(dist / 10) * 10
	// mrst = dist;
	// }
    // else {
	// dist = Math.round(dist / 100) * 100
	// mrst = dist;
	// }

    // console.log("mrst = "+mrst);
    // document.getElementById('mrst').innerHTML=mrst;
		});
	});
}