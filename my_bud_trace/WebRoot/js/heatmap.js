if (!isSupportCanvas()) {
    alert('热力图仅对支持canvas的浏览器适用,您所使用的浏览器不能使用热力图功能,请换个浏览器试试~')
}


var data = []

getdata = function()
{
    info()
    pointime._data.result = "正在请求热力图";
    var cuid= pointime._data.cuid;
    var lower = 'lower=' + lowtime;
    var upper = 'upper=' + uptime;
    var uid = 'uid=' + cuid;
    var heaturl = '/bud/map/heat_data?'+ uid + '&' + lower + '&' + upper;
    k.gest_rest(heaturl, function(result) {
        // alert('hello');
        if (result.errcode == 200) {
            var dataArr = result.data;
            data.splice(0, data.length); //清空数组
            for (var index = 0; index < dataArr.length; index++) {
                data.push(dataArr[index]);
            }
            heatmap.setDataSet({
                data: data,
                max: 100
            });
            suc()
            pointime._data.result = "已生成热力图";
        }else{
            danger()
            pointime._data.result = "请求失败";
        }
    })
}

var heatmap;

map.plugin(["AMap.Heatmap"], function () {
    //初始化heatmap对象
    heatmap = new AMap.Heatmap(map, {
        radius: 25, //给定半径
        opacity: [0, 0.8]
    });
});

//判断浏览区是否支持canvas
function isSupportCanvas() {
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}