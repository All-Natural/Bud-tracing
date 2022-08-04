var a;
var b;
var lostpoint = [];
var pathpolylinearrayfinal = [];
//绑定按钮事件
function traceshow() {
    if(lowtime!=null && uptime!=null){
        info();
        pointime._data.result = "正在发送轨迹查询请求……";

    //发送请求
    const xhr = new XMLHttpRequest();
    
    //设置响应体数据类型,为json格式
    xhr.responseType = 'json';
    var cuid= pointime._data.cuid;
    var lower = 'lower=' + lowtime;
    var upper = 'upper=' + uptime;
    var uid = 'uid=' + cuid;
    //初始化ajax
    xhr.open('GET', 
    // 'http://hatlate.cn:8080/bud/map/get_trace?'
    // 'http://1.117.89.111:8080/bud/map/get_trace?'
	'/bud/map/get_trace?'
    + uid + '&' + lower + '&' + upper);

    //发送ajax
    xhr.send();

    //事件绑定
    xhr.onreadystatechange = function () {

        //判断状态，4为有返回结果
        if (xhr.readyState === 4) {

            //判断状态码，闭开区间 [200, 300）属于成功
            if (xhr.status >= 200 && xhr.status < 300) {
                suc();
                pointime._data.result = "请求成功！所选时间段内轨迹已显示";

                //JSON文件  String 转 Object
                var path = xhr.response.data;
                path.forEach(
                    function (self, index, arr) {
                        if (index < path.length - 1) {
                            a = path[index].millisec;
                            b = path[index + 1].millisec;
                            if (((b - a) / 1000) > 10) {
                                lostpoint.push(index);
                            }
                        }
                    })


                path.forEach(
                    function (self, index, arr) {
                        //剔除
                        delete path[index].millisec;
                    })

                var pathpolyline = [];
                for (var i in path) {
                    var maincol = [];
                    for (var j in path[i]) {
                        maincol.push(path[i][j]);
                    }
                    pathpolyline.push(maincol);
                }


                /**
                 * 这里的if用来实现，当用户选择的轨迹区间内的点过多时，提醒用户并且由用户输入需要显示的点个数
                 */
                //当数组长度超过1000时，size用于接收用户输入的值
                // if (pathpolyline.length > 1000) {
                //     var size = prompt('当前选取数据过大，总计[' + pathpolyline.length + ']条。请输入要显示的数据项数：');
                //     if (size <= pathpolyline.length) {
                //         //以size为界限切开数组
                //         pathpolyline.splice(size);
                //         console.log(pathpolyline.length);
                //     } else {
                //         alert('填写的数据有误，自动显示出全部数据');
                //     }
                // } else {
                //     console.log(pathpolyline.length);
                // }
                // 定义折线对象
                if (lostpoint == '') {
                    var polyline = new AMap.Polyline({
                        path: pathpolyline,
                        // 折线颜色
                        strokeColor: "#3366FF",
                        strokeOpacity: 1,
                        // 折线粗度
                        strokeWeight: 12,
                        // 折线样式还支持 'dashed'
                        strokeStyle: "solid",
                        lineJoin: 'round',
                        lineCap: 'round',
                        zIndex: 10,
                        showDir: true,
                    })

                    // 将折线绘制到地图上
                    polyline.setMap(map);

                    // 缩放地图到合适的视野级别
                    map.setFitView([polyline]);
                } else {

                    var pathpolylinearray = [];
                    if (lostpoint[0] != 0)
                        lostpoint.unshift(0);
                    lostpoint.forEach(function (self, index, arr) {
                        if (index != (lostpoint.length - 1)) {
                            pathpolylinearray.push(pathpolyline.slice(lostpoint[index], lostpoint[index + 1] + 1));
                            lostpoint[index + 1]++;
                        }
                    })
                    pathpolylinearray.forEach(
                        function (self, index, arr) {
                            pathpolylinearrayfinal.push(new AMap.Polyline({
                                path: pathpolylinearray[index],
                                // 折线颜色
                                strokeColor: "#3366FF",
                                strokeOpacity: 1,
                                // 折线粗度
                                strokeWeight: 12,
                                // 折线样式还支持 'dashed'
                                strokeStyle: "solid",
                                lineJoin: 'round',
                                lineCap: 'round',
                                zIndex: 10,
                                showDir: true,
                            }));

                            // 将折线绘制到地图上
                            // pathpolylinearrayfinal[index].setMap(map);
                            map.add(pathpolylinearrayfinal[index]);


                        }
                    )
                    // 缩放地图到合适的视野级别
                    map.setFitView([pathpolylinearrayfinal[0]]);
                }
            }else{
                danger();
                pointime._data.result = "请求出现异常！";
                
            }
        }
    }
}else{
danger();
pointime._data.result = "尚未选择轨迹时间区间！";
}


};
