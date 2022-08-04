var gpspoint = true;

// 清除 marker
function clearMarker() {
    if (marker) {
        marker.setMap(null);
        marker = null;
    }
    
}
var pointag = false;
var mysi;
var marker = null;


function pointGet() {
    info();
    pointime._data.result = "定位获取请求中……";
    mysi = setInterval(interval, 2500);
}
function interval() {
    
    $.ajax({
        // url: 'http://hatlate.cn:8080/bud/map/current_position',
        // url: 'http://1.117.89.111:8080/bud/map/current_position',
		url : '/bud/map/current_position',
        // url: 'http://10.20.245.93:8080/bud/map/current_position',
        type: 'GET',
        data: {
            uid: pointime._data.cuid
        },
        dataType: 'json',
        success: function (response) {
            
            var point = [];
            point.push(response.data.longitude);
            point.push(response.data.latitude);
            if (pointag)
                clearMarker();
            marker = new AMap.Marker({
                icon: "image/point.png",
                position: point,
                offset: new AMap.Pixel(-13, -30)
            });
            marker.setMap(map);
            pointag = true;
            pointime._data.time = response.data.time;
            pointime._data.speed = response.data.speed + "米/秒";
            if (gpspoint == true) {
                suc();
                map.setFitView([marker]);
                document.getElementById('timecolor').style.color = "#155724";
                document.getElementById('timecolor').style.backgroundColor = "#d4edda";
                document.getElementById('timecolor').style.borderColor = "#c3e6cb";
                document.getElementById('timecolor1').style.color = "#155724";
                document.getElementById('timecolor1').style.backgroundColor = "#d4edda";
                document.getElementById('timecolor1').style.borderColor = "#c3e6cb";
                pointime._data.result = "请求成功！正在周期性读取孩童当前定位";
                gpspoint = false;
            } 
            
        },
        error: function () {
            danger();
            pointime._data.result = "请求失败";
        }
    });
}


function clearpoint() {
    if (gpspoint == false){
    clearInterval(mysi);info();pointime._data.result = "暂停定位获取……";gpspoint = true;
    }else{
        danger()
        pointime._data.result = "未定位"
    }
}