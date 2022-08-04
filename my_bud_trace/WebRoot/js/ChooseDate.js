function datetimeFormat(longTypeDate) {
  var dateTypeDate = "";
  var date = new Date();
  date.setTime(longTypeDate);
  dateTypeDate += date.getFullYear(); //年  
  dateTypeDate += "-" + (date.getMonth()+1); //月  
  dateTypeDate += "-" + date.getDate(); //日  
  return dateTypeDate;
}
var now = new Date()

var lowtime = moment(datetimeFormat(now.getTime())+' 00:00:00').format("YYYY-MM-DD HH:mm:ss")
var uptime = moment(datetimeFormat(now.getTime())+' 23:59:59').format("YYYY-MM-DD HH:mm:ss")
var Main = {
    data() {
      return {
        value1: ""}
      },
      methods:{
        clickevent:function(){
          lowtime = moment(this.value1[0]).format("YYYY-MM-DD HH:mm:ss");
          uptime = moment(this.value1[1]).format("YYYY-MM-DD HH:mm:ss");
        }
      }
      };
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')