
		$(function() {
			
			$('#linkLogout').click(function() {
				layer.confirm('您确定要继续退出吗？', {
					skin: '#227dc5',
					  btn: ['让我走~','我再想想'] //按钮
					}, function(){
					  window.location = _ctx + '/logout';
					}, function(){
					  
					});
			});
			$('#linkModifyPassword').click(function() {
				layer.open({
				      type: 2,
				      title: '修改密码',
				      skin: '#227dc5',
				      shade: 0.4,
				      shadeClose: true,
				      maxmin: false, //开启最大化最小化按钮
				      area: ['400px', '300px'],
				      content: _ctx + '/modify-password'
				    });
			});
			
			$('#hd-avatar').click(openPersonInfo);
			$('#hd-name').click(openPersonInfo);
		});
     
		function openPersonInfo() {
			layer.open({
			      type: 2,
			      title: '个人信息',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      cancel: function(index){ 
			    	location.reload();  
			    	},
			      maxmin: false, //开启最大化最小化按钮
			      area: ['400px', '440px'],
			      content: _ctx + '/modify-person-info'
			    });
			}