//创建右键菜单
var menu = new ContextMenu(map);
var ngat = null;
//自定义菜单类
function ContextMenu(map) {
    var me = this;

    //地图中添加鼠标工具MouseTool插件
    // this.mouseTool = new AMap.MouseTool(map);
    AMap.plugin(["AMap.MouseTool"], function () {
        mouseTool = new AMap.MouseTool(map);
    });

    this.contextMenuPositon = null;

    var content = [];

    content.push("<div class='info context_menu' style='position: relative;padding: 0px;min-width: 13rem;'>");
    // content.push("  <div class='fm'>热力图选项<ul class='info' style='padding: 0px;min-width: 13rem;'><li onclick='menu.heatmap()'>查询热力图</li></ul></div>");
    // content.push("  <div class='fm'>其他电子围栏<ul class='info' style='padding: 0px;min-width: 13rem;'><li id='weilan' title='开始编辑一个多边形围栏' onclick='menu.polygonedit()'>多边形围栏</li></ul></div>");
    content.push("  <div class='fm' onclick='window.location.reload()'>刷新页面</div>");
    content.push("</div>");

    //通过content自定义右键菜单内容
    this.contextMenu = new AMap.ContextMenu({ isCustom: true, content: content.join('') });

    //地图绑定鼠标右击事件——弹出右键菜单
    map.on('rightclick', function (e) {
        me.contextMenu.open(map, e.lnglat);
        me.contextMenuPositon = e.lnglat; //右键菜单位置
        ngat = e.lnglat
    });
    map.on('moveend', function () { })
}
ContextMenu.prototype.heatmap = function () {  //右键菜单添加Marker标记
    // this.mouseTool.close();
    getdata();
    this.contextMenu.close();
};
ContextMenu.prototype.hideheat = function () {  //右键菜单添加Marker标记
    // this.mouseTool.close();
    heatmap.hide();
    this.contextMenu.close();
};
var polyshow = function () {
    polygon.setMap(map);
    setPoly()
    if (ptag == 1) {
        info();
        polygon.setPath([
            [map.getCenter().lng - 0.0100500, map.getCenter().lat - 0.030500],
            [map.getCenter().lng - 0.050000, map.getCenter().lat - 0.005000],
            [map.getCenter().lng - 0.000750, map.getCenter().lat - 0.000750]
        ])
        pointime._data.result = "开始编辑电子围栏";
        kapp._data.editext1 = "结束编辑多边形";
        polygon.show();
        map.setFitView([polygon]);
        polyEditor.open();
        ptag++;
    } else {
        info();
        pointime._data.result = "结束编辑电子围栏";
        kapp._data.editext1 = "新增多边形围栏";
        polyEditor.close();
        polygon.hide();
        ptag--;
    }
}