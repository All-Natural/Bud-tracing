//tag用于标记圆编辑的当前状态，1为休眠，2为激活
let tag = 1;

//is_circle_exist用于标记当前地图上是否生成过圆
let is_circle_exist = 0;

//此处定义了圆的基本属性
var circle = new AMap.Circle({
    center: map.getCenter(),
    radius: 1000, //半径
    borderWeight: 3,
    strokeColor: "#006600",
    strokeWeight: 4,
    strokeOpacity: 0.2,
    fillOpacity: 0.4,
    strokeStyle: 'dashed',
    strokeDasharray: [10, 10],
    // 线样式还支持 'dashed'
    fillColor: '#FFAA00',
    zIndex: 50,
})

//此处定义编辑圆的类
var circleEditor = new AMap.CircleEditor(map, circle)
// AMap.plugin(["AMap.CircleEditor"],function () {
//     circleEditor = new AMap.CircleEditor(map, circle);
// });

/**
 * 此处定义圆编辑过程中的状态反馈：
 * 1. 移动圆心 2. 半径修改 3. 结束编辑
 */
circleEditor.on('move', function (event) {
    setCircle()
    info();
    pointime._data.result = '圆心已移动到' + circle.getCenter().lng + ', ' + circle.getCenter().lat;
    // log.info('圆心已移动到' + circle.getCenter().lng + ', ' + circle.getCenter().lat)
})

circleEditor.on('adjust', function (event) {
    setCircle()
    info();
    pointime._data.result = '圆范围已调整为' + circle.getRadius();
    // log.info('圆范围已调整为' + circle.getRadius())
})

circleEditor.on('end', function (event) {
    setCircle()
    info();
    pointime._data.result = '结束圆形编辑'
})

/**
 * 绑定按钮单击事件
 * 1. 首次单击时 tag 为1，在地图上生成圆，并激活圆编辑，tag 置为2
 * 2. 再次单击时 tag 为2，圆编辑休眠，tag 置为1
 */
function crclshow() {
    /**
     * 此处判断地图上是否已经生成过圆
     */
        circle.setMap(map);
        setCircle()
    if (tag == 1) {
        info();
        pointime._data.result = "开始编辑电子围栏";
        circle.setCenter(map.getCenter())
        //将圆可视
        circle.show();
        // 缩放地图到合适的视野级别
        map.setFitView([circle]);
        kapp._data.editext = "结束编辑圆形";
        //激活圆编辑
        circleEditor.open();
        tag++;
    } else {
        info();
        pointime._data.result = "结束编辑电子围栏";
        kapp._data.editext = "新增圆形围栏";
        // 休眠圆编辑
        circleEditor.close();
        //将圆不可视
        circle.hide();
        tag--;
    }
}
/**
 * 该函数绑定的是 圆数据返回按钮
 * 用于在控制台返回所设定圆的经纬度和半径
 * 并且发送数据给服务端
 */
var show = function (name,desc,type,cop) {
    let dt =
    {
        puid: parseInt(pointime._data.puid),
        cuid: parseInt(pointime._data.cuid),
        name: name,
        desc: desc,
        type: parseInt(type),
    };
    if (cop =='circle'){
        dt.center = circle.getCenter().lng + ',' + circle.getCenter().lat
        dt.radius = circle.getRadius()
        dt.lat = circle.getCenter().lat
        dt.lng = circle.getCenter().lng
        dt.figuretype = 1
        var url = '/bud/map/add_circle_fence'
    }
    else{
        
        dt.points = []
        polygon.getPath().forEach(function (self, index, arr) {
            dt.points.push([self.lng,self.lat])
        })
        dt.figuretype = 0
        var url = '/bud/map/add_polygon_fence'
    }
    info();
    pointime._data.result = "围栏上传请求中……";
    //ajax请求
    $.ajax({
        url: url,
        type: 'POST',
        data: JSON.stringify(dt),
        success: function () {
            suc();
                pointime._data.result = "电子围栏已记录";
                nowtype = null
        },
        error: function () {
            danger();
            pointime._data.result = "请求失败";
        }
    });
}
