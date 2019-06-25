var apiUrl="js/api/";
/**
 * 共通js
 */
$.ajax({
	type: "get",
	url: apiUrl+'getMenu.htm',
	contentType: "application/json",
	sync:false,
	success: function (result) {
		var r = JSON.parse(result);
		var menuList = r.data;
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