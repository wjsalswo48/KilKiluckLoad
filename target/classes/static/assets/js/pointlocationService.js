
window.onload = function (){
    var latitude = document.getElementById('latitude').value;
    var longtitude = document.getElementById('longitude').value;
    var addressname = document.getElementById('id').value;

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapCenter = new kakao.maps.LatLng(latitude, longtitude), // 지도의 중심 좌표
        mapOption = {
            center: mapCenter, // 지도의 중심 좌표
            level: 4 // 지도의 확대 레벨
        };
        
    // 마커가 표시될 위치입니다
    var markerPosition  = new kakao.maps.LatLng(latitude, longtitude);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    
    // 지도에 올릴 마커를 생성합니다.
    var mMarker = new kakao.maps.Marker({
        position: mapCenter, // 지도의 중심좌표에 올립니다.
        map: map // 생성하면서 지도에 올립니다.
    });
    
    var iwContent = '<div style="padding:5px;">'+addressname+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(latitude, longtitude), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성하고 지도에 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        map: map, // 인포윈도우가 표시될 지도
        position : iwPosition,
        content : iwContent,
        removable : iwRemoveable
    });

    var mLabel = new kakao.maps.InfoWindow({
        position: mapCenter, // 지도의 중심좌표에 올립니다.
        content: addressname // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
    });


    var rvContainer = document.getElementById('roadview'); // 로드뷰를 표시할 div
    var rv = new kakao.maps.Roadview(rvContainer); // 로드뷰 객체 생성
    var rc = new kakao.maps.RoadviewClient(); // 좌표를 통한 로드뷰의 panoid를 추출하기 위한 로드뷰 help객체 생성
    var rvResetValue = {} //로드뷰의 초기화 값을 저장할 변수
    rc.getNearestPanoId(mapCenter, 50, function(panoId) {
        rv.setPanoId(panoId, mapCenter);//좌표에 근접한 panoId를 통해 로드뷰를 실행합니다.
        rvResetValue.panoId = panoId;
    });


// 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map),
        infowindow.setContent(iwContent),
        infowindow.open(map, marker),
        infowindow = new kakao.maps.InfoWindow({zindex: 1});

// 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
        infowindow = new kakao.maps.InfoWindow({zindex: 1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
        searchDetailAddrFromCoords(mouseEvent.latLng, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';

                var content = '<div class="bAddr">' + detailAddr + '</div>';

                // 마커를 클릭한 위치에 표시합니다
                marker.setPosition(mouseEvent.latLng);
                marker.setMap(map);


                // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                infowindow.setContent(content);
                infowindow.open(map, marker);
            }
        });
    });

    // 로드뷰 초기화 이벤트
    kakao.maps.event.addListener(rv, 'init', function() {

        // 로드뷰에 올릴 마커를 생성합니다.
        var rMarker = new kakao.maps.Marker({
            position: mapCenter,
            map: rv //map 대신 rv(로드뷰 객체)로 설정하면 로드뷰에 올라갑니다.
        });

        // 로드뷰에 올릴 장소명 인포윈도우를 생성합니다.
        var rLabel = new kakao.maps.InfoWindow({
            position: mapCenter,
            content: addressname
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
    //지도 이동 이벤트 핸들러
    function moveKakaoMap(self){

        var center = map.getCenter(),
            lat = center.getLat(),
            lng = center.getLng();

        self.href = 'https://map.kakao.com/link/map/' + encodeURIComponent(addressname) + ',' + lat + ',' + lng; //Kakao 지도로 보내는 링크
    }

//지도 초기화 이벤트 핸들러
    function resetKakaoMap(){
        map.setCenter(mapCenter); //지도를 초기화 했던 값으로 다시 셋팅합니다.
        map.setLevel(mapOption.level);
    }

//로드뷰 이동 이벤트 핸들러
    function moveKakaoRoadview(self){
        var panoId = rv.getPanoId(); //현 로드뷰의 panoId값을 가져옵니다.
        var viewpoint = rv.getViewpoint(); //현 로드뷰의 viewpoint(pan,tilt,zoom)값을 가져옵니다.
        self.href = 'https://map.kakao.com/?panoid='+panoId+'&pan='+viewpoint.pan+'&tilt='+viewpoint.tilt+'&zoom='+viewpoint.zoom; //Kakao 지도 로드뷰로 보내는 링크
    }

//로드뷰 초기화 이벤트 핸들러
    function resetRoadview(){
        //초기화를 위해 저장해둔 변수를 통해 로드뷰를 초기상태로 돌립니다.
        rv.setViewpoint({
            pan: rvResetValue.pan, tilt: rvResetValue.tilt, zoom: rvResetValue.zoom
        });
        rv.setPanoId(rvResetValue.panoId);
    }

// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'idle', function () {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    });

    function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
    }

    function searchDetailAddrFromCoords(coords, callback) {
        // 좌표로 법정동 상세 주소 정보를 요청합니다
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
}
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for (var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }
}
