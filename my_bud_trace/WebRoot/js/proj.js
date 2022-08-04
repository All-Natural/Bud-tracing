var k={};

k.rest=function(uri,req,handler)
{
	var reqStr=JSON.stringify(req);
	jQuery.ajax({
		type:'POST',
		url:uri, 
		contentType:'application/json',
		data:reqStr,
		dataType:'json',
		success: function(resp){ 
			if(handler!=null)
			{
				handler(resp);
			}
		},
	});
}

k.gest_rest=function(uri,handler)
{
	// var reqStr=JSON.stringify(req);
	jQuery.ajax({
		type:'GET',
		url:uri, 
		// contentType:'application/json',
		// data:reqStr,
		dataType:'json',
		success: function(resp){ 
			if(handler!=null)
			{
				handler(resp);
			}
			
		},
	});
}


k.getParam=function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }

k.isChecked=function(id){
//	var target=document.getElementById(id);
//	var target=$(id);
	var bool = $(id).is(':checked');
	
	return bool;
}

k.css=function(id,attr,val)
{
	$(id).css(attr,val);
	
}



