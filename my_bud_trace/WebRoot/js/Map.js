//定义地图，center属性缺省则会自动选取用户当前定位为中心
var map = new AMap.Map("container", {
    zoom: 18,
    expandZoomRange: true,
    zooms: [3, 20]
});

function suc(){
    document.getElementById('resultcolor').style.animationName = 'fade-in';
    document.getElementById('resultcolor').style.color = "#155724";
                document.getElementById('resultcolor').style.backgroundColor = "#d4edda";
                document.getElementById('resultcolor').style.borderColor = "#c3e6cb";

}
function danger(){
    document.getElementById('resultcolor').style.animationName = 'fade-in';
    document.getElementById('resultcolor').style.color = "#721c24";
                document.getElementById('resultcolor').style.backgroundColor = "#f8d7da";
                document.getElementById('resultcolor').style.borderColor = "#f5c6cb";
}
function info(){
    document.getElementById('resultcolor').style.animationName = 'fade-in';
    document.getElementById('resultcolor').style.color = "#0c5460";
                document.getElementById('resultcolor').style.backgroundColor = "#d1ecf1";
                document.getElementById('resultcolor').style.borderColor = "#bee5eb";
}
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) { return pair[1]; }
    }
    return (false);
}
var nowtype
function setCircle(){
nowtype = 'circle'
}
function setPoly(){
    nowtype = 'poly'
    }