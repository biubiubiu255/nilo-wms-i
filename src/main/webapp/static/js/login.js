layui.use(['form'], function() {
	var form = layui.form;
	
	checkLogin();
	//提交
	form.on('submit(LAY-user-login-submit)', function(obj) {
		layer.load(2);
		$.post("/servlet/login", obj.field, function(data) {
			if (data.status == 'succ') {
				layer.msg("SUCCESS",{icon: 1});
				localStorage.setItem("token", data.token);
				localStorage.setItem("user", JSON.stringify(data.user));
				setTimeout(function() {
					location.replace("index.html");
				}, 2000);
			} else {
				layer.closeAll('loading');
				layer.msg(data.error);
			}
		}, "json");
	});
	
	getCode();  //获取验证码
	$("#LAY-user-get-vercode").click(function(){
		getCode();
	});
});

//获取验证码
function getCode(){
	$("#LAY-user-get-vercode").attr("src","/servlet/captcha/image?n="+Math.random());
}

//检查是否登录
function checkLogin(){
	var tempUser = JSON.parse(localStorage.getItem("user"));
	if (tempUser != null) {
		location.replace("index.html");
	}
}
