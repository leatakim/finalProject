
//지도 관련
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.56792412182713, 126.98298720739353), //지도의 중심좌표.
    level: 2 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options);

var marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter()
});


// 지도에 마커를 표시합니다
marker.setMap(map);

let lng = null;
let lat = null;

kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;
    marker.setPosition(latlng);
    lng = latlng.getLng();
    lat = latlng.getLat();
});

//  getLng() 위
//  getLat() 경

document.getElementsByClassName("submitButton")[0].addEventListener("click",function submitAction(){
    if(confirm("랜드마크를 등록 하시겠습니까?")){
        const contents = document.getElementById("contents").value;
        const title = document.getElementById("title").value;
        const hashTag = document.getElementsByClassName("tag_input")[0].value;
        const hashTagArray = hashTag.spilt("#");
        const selectLocation = document.getElementById("locations-list").value;
        const images = document.getElementsByClassName("images")

        $.ajax({
            url: "/write/"+selectLocation,
            data: {"landmarkContents": contents,
                    "landmarkName": title,
                    "hashTag": hashTagArray,
                    "lng":lng,
                    "lat":lat,
                    "locationNum":selectLocation},
            type: "post"
        })
    }
})
