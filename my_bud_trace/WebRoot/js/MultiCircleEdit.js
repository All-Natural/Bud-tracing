var crcldt = {};
var circlearray = [];
crcldt.uid;
crcldt.gfids;
crcldt.style;
var showtag = true;
var indextag = 0;
var circletype = [];
/**
 * 从服务器获取圆形围栏
*/
function showcircle() {
    map.clearMap();
    crcldt.uid = null;
    crcldt.gfids = null;
    circlearray = [];
    info();
    pointime._data.result = "预设围栏请求中……";
    $.ajax({
        // url: 'http://hatlate.cn:8080/bud/map/query_circle_fence',
        // url: 'http://1.117.89.111:8080/bud/map/query_circle_fence',
        // url: 'http://10.20.181.180/bud/map/query_circle_fence',
        // url: 'http://10.20.181.180/bud/map/query_polygon_fence',
		// url: '/bud/map/query_circle_fence',
        url: '/bud/map/view_fence',
        type: 'GET',
        data: {
            uid: pointime._data.puid,
            cuid: pointime._data.cuid
        },
        dataType: 'json',
        success: function (response) {
            suc();
            pointime._data.result = "请求成功！已显示所有围栏";
            var crclarr = response.data.results;
            var vuecircle = new Vue({
                el: '#vuecircle',
                data: {
                    circleinform: circlearray,
                    safe: '<h5 id="safe" style="color: green;border-bottom-style: solid;border-width: 2px">安全</h5>',
                    danger: '<h5 id="danger" style="color: red;border-bottom-style: solid;border-width: 2px">危险</h5>',
                    circltype: circletype,
                },
                methods: {
                    crclfind: function (index) {
                        crcldt.uid = circlearray[index].getExtData().uid;
                        crcldt.gfids = circlearray[index].getExtData().gfid;
                        crcldt.style = parseInt(circlearray[index].getExtData().style);
                        map.setFitView([circlearray[index]]);
                        
                    }
                }
            })
            
            if (showtag)
                document.getElementsByClassName('vuebtn')[0].style.display = "block";
            showtag = false;
            crclarr.forEach(function (self, index, arr) {
                circletype.push(crclarr[index].type);
                if(crclarr[index].shape.style == 0){
                circlearray.push(new AMap.Circle({
                    center: crclarr[index].shape.center.split(','),
                    radius: crclarr[index].shape.radius, //半径
                    borderWeight: 3,
                    strokeColor: "#FF33FF",
                    strokeOpacity: 1,
                    strokeWeight: 6,
                    strokeOpacity: 0.2,
                    fillOpacity: 0.4,
                    strokeStyle: 'solid',
                    strokeDasharray: [10, 10],
                    // 线样式还支持 'dashed'
                    fillColor: crclarr[index].type == 1 ? '#1791fc' : 'red',
                    zIndex: 50,
                    extData: {
                        uid: pointime._data.puid,
                        gfid: crclarr[index].gfid,
                        name: crclarr[index].name,
                        desc: crclarr[index].desc,
                        type: crclarr[index].type,
                        style: crclarr[index].shape.style
                    },
                }));
            }else{
                circlearray.push(new AMap.Polygon({
                    path: JSON.parse(crclarr[index].shape.points),
                    strokeColor: "#FF33FF", 
                    strokeWeight: 6,
                    strokeOpacity: 0.2,
                    strokeStyle: 'solid',
                    fillOpacity: 0.4,
                    fillColor: crclarr[index].type == 1 ? '#1791fc' : 'red',
                    zIndex: 50,
                    extData: {
                        uid: pointime._data.puid,
                        gfid: crclarr[index].gfid,
                        name: crclarr[index].name,
                        desc: crclarr[index].desc,
                        type: crclarr[index].type,
                        style: crclarr[index].shape.style
                    },
                }))
            }
            })
            // console.log(circlearray[0].getExtData().type);
            /**
             * 圆的数组集合遍历
             * 对于每次循环，先在地图上显示出来，接着绑定单击事件
             */
            circlearray.forEach(
                function (self, index, arr) {
                    //显示圆
                    circlearray[index].setMap(map);
                    
                    /**
                     * 绑定单击事件
                    */
                    AMap.event.addListener(circlearray[index], "click", function () {
                        //注册选中圆的uid
                        crcldt.uid = parseInt(this.getExtData().uid);
                        crcldt.gfids = this.getExtData().gfid;
                        crcldt.style = parseInt(this.getExtData().style);
                        info()
                        pointime._data.result = "已选中围栏["+this.getExtData().name+"]";
                    });
                });
            // 缩放地图到合适的视野级别
            map.setFitView([circlearray[0]]);
        },
        error: function () {
            danger();
            pointime._data.result = "请求失败";
    }});
}



/**
 * 用按钮删除选定圆
*/
function deletecircle() {
    if (crcldt.uid != null && crcldt.gfids != null) {
        info();
        pointime._data.result = "电子围栏删除请求中……";
        if(crcldt.style == 0)
        var url = '/bud/map/delete_circle_fence'
        else
        var url = '/bud//map/delete_polygon_fence'
        $.ajax({
            // url: 'http://hatlate.cn:8080/bud/map/delete_circle_fence',
            // url: 'http://1.117.89.111:8080/bud/map/delete_circle_fence',
            // url: 'http://10.20.181.180:8080/bud/map/delete_circle_fence',
            url: url,
            type: 'POST',
            data: JSON.stringify(crcldt),
            success: function () {
                suc();
                pointime._data.result = "电子围栏已删除！";
                circlearray.forEach(
                    function (self, index, arr) {
                        if (circlearray[index].getExtData().gfid === crcldt.gfids) {
                            circlearray[index].hide();
                            circlearray.splice(index, 1);
                        }
                    }
                )
                crcldt.uid = null;
                crcldt.gfids = null;
                crcldt.style = null;
            },
            error: function () {
                danger();
                pointime._data.result = "请求失败";
            }
        }
        );
    } else { 
        danger();
        pointime._data.result = "你还没有选择电子围栏！";
    }
}
