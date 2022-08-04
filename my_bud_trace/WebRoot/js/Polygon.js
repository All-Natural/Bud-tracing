var ptag = 1
var plng = map.getCenter().getLng()
var plat = map.getCenter().getLat()
    var polygonpath = [
        [plng-0.0100500,plat-0.030500],
        [plng-0.050000,plat-0.005000],
        [plng-0.000750,plat-0.000750]
    ]
var polygon = new AMap.Polygon({
    path: polygonpath,
    strokeColor: "#006600", 
    strokeWeight: 4,
    strokeOpacity: .1,
    fillOpacity: .4,
    strokeStyle: 'dashed',
    fillColor: '#FFAA00',
    zIndex: 50,
    draggable:true,
})

var polyEditor = new AMap.PolyEditor(map, polygon)

polyEditor.on('addnode', function(event) {
    info();
    pointime._data.result = '新增目标点';
})

polyEditor.on('adjust', function(event) {
    info();
    pointime._data.result = '调整目标点';
})

polyEditor.on('removenode', function(event) {
    info();
    pointime._data.result = '删除目标点';
})

polyEditor.on('end', function(event) {
    info();
    pointime._data.result = '结束多边形编辑';
})