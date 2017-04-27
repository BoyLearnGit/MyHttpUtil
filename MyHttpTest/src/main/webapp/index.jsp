<html>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">
function uploadFile() {
	$.ajaxFileUpload({
		type:'get',
		dataType:'jsonp',
		jsonp: 'jsoncallback',
        url:'http://192.168.112.10:9090/personcerter/updateHead',
        secureuri: false, //一般设置为false
	    fileElementId: 'file', // 上传文件的id、name属性名
        success:function(data, status){
        	 //var  data = jQuery.parseJSON(jQuery(data).text());
        	console.log(data);
        },
        error:function(data,staus,e){
        	// var  data = jQuery.parseJSON(jQuery(data).text());
        	console.log(e);
        }

	});
}
	

	
</script>
<body>
<h2>Hello World!</h2>
<input type="file" id="file" name="file">
<input type="button" value="提交" onclick="uploadFile()">
</body>
</html>
