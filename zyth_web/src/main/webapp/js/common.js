var apiUrl="http://localhost/zyth_web/";
/**
 * 共通js
 */
$.ajax({
	type: "post",
	url: apiUrl+'operator/getMenu.htm',
	contentType: "application/json",
	sync:false,
	success: function (result) {
		var menuList = result.data;
		var url = window.location.href;
		$(menuList).each(function(i,menu){
			var active = "";
			var tempHtml ='<ul class="treeview-menu">';
			$(menu.subMenu).each(function(j,sub){
				var subActive = '';
				if(url.indexOf(sub.linkUrl)!=-1){
					subActive = 'active';
					active = 'active';
				}
				tempHtml += '<li class="'+subActive+'"><a href="'+sub.linkUrl
					+'"><i class="fa '+sub.iconClass+'"></i>'+sub.name+'</a></li>';
			})
			tempHtml += '</ul>';
			$(".sidebar-menu").append('<li class="treeview '+active+'"><a href="#"><i class="fa '+menu.iconClass+'"></i><span class="hidden-tablet">'+menu.name+'</span></a>'+tempHtml+'</li>');
		})
	}
});
function getObjectURL(file) {
	 var url = null ;
	 if (window.createObjectURL!=undefined) { // basic
		 url = window.createObjectURL(file) ;
	 } else if (window.URL!=undefined) { // mozilla(firefox)
		 url = window.URL.createObjectURL(file) ;
	 } else if (window.webkitURL!=undefined) { // webkit or chrome
		 url = window.webkitURL.createObjectURL(file) ;
	 }
	 return url ;
}
//分页语言设置
var dtLang = {
		  "sLengthMenu": "每页显示 _MENU_ 条记录",
		  "sZeroRecords": "抱歉， 没有找到",
		  "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		  "sInfoEmpty": "没有数据",
		  "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
		  "sZeroRecords": "没有检索到数据",
		  "sSearch": "页面内检索:",
		  "oPaginate": {
		    "sFirst": "首页",
		    "sPrevious": "前一页",
		    "sNext": "后一页",
		    "sLast": "尾页"
			}
		}