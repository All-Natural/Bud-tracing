var dat1 = document.getElementById('dat1');
var dat2 = document.getElementById('dat2');
var tim2 = document.getElementById('tim2');
var tim1 = document.getElementById('tim1');
var chg1 = function () {
    if (dat1.value > dat2.value) {
        dat2.value = dat1.value;
    }

}
var chg2 = function () {
    if (dat2.value < dat1.value) {
        dat1.value = dat2.value;


    }}