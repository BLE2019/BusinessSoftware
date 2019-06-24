function formatSeconds(value){
	 var secondTime = parseInt(value);// 秒
     var minuteTime = 0;// 分
     var hourTime = 0;// 小时
	 var dayTime=0;//天
     if(secondTime > 60) {//如果秒数大于60，将秒数转换成整数
         //获取分钟，除以60取整数，得到整数分钟
         minuteTime = parseInt(secondTime / 60);
         //获取秒数，秒数取佘，得到整数秒数
         secondTime = parseInt(secondTime % 60);
         //如果分钟大于60，将分钟转换成小时
         if(minuteTime > 60) {
             //获取小时，获取分钟除以60，得到整数小时
             hourTime = parseInt(minuteTime / 60);
             //获取小时后取佘的分，获取分钟除以60取佘的分
             minuteTime = parseInt(minuteTime % 60);
         }
         if(hourTime>24){
			 dayTime=parseInt(hourTime / 24);
			 hourTime=parseInt(hourTime % 24);
		 }
	
	 }
     var result = "" + parseInt(secondTime) + "秒";

    if (dayTime > 0) {
        result = "" + parseInt(dayTime) + "天" + parseInt(hourTime) + "小时";
    } else if (hourTime > 0) {
        result = "" + parseInt(hourTime) + "小时" + parseInt(minuteTime) + "分";
    } else if (minuteTime > 0) {
        result = "" + parseInt(minuteTime) + "分" + parseInt(secondTime) + "秒";
    }
     return result;
	
}

function createwebAccessTrendOption(data){
	var totaldays=[];//统计日期
	var accesscount=[];//日访问量
	var usercount=[];//日访客量
	for(var i=0;i<data.length;i++){
		totaldays.push(data[i].access_date);
		accesscount.push(data[i].access_count);
		usercount.push(data[i].user_count);
	}
	option = {
    tooltip : {
        trigger: 'axis'
    },
  
    calculable : true,
    legend: {
        data:['日访问量','日访客量'],
        left:10,
    },
	  textStyle: {
	       color: '#ffffff'
	   },
    xAxis : [
        {
            type : 'category',
            data : totaldays,
            axisTick: {
                alignWithLabel: true, //刻度线和标签对齐
                interval:0   //间隔
            },
            splitLine: {
                show: true, //网格线开关
            },   
        },  
    ],
    yAxis : [
		{
		     type : 'value',
		     name : '日访问量',
		     splitLine: { // 分隔线
		         show: false, // 默认显示，属性show控制显示与否
		     },
		   },
        {
            type : 'value',
            name : '日访客量',
			splitNumber:3,
            splitLine: { // 分隔线
                show: false, // 默认显示，属性show控制显示与否

            },

        },    
	],
    series : [
  {
            name:'日访问量',
            type:'line',
            data:accesscount,
            itemStyle: {
                normal: {
                    color: '#fdb94e'
                },
            },
         areaStyle: {
        color: 'rgba(1,98,133,0.6)'
        }
		},
  {
            name:'日访客量',
            type:'line',
            data:usercount,
			yAxisIndex: 1,
            itemStyle: {
                normal: {
                    //barBorderRadius: 15,
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1, [{
                                offset: 0,
                                color: 'green'
                            },
                            {
                                offset: 1,
                                color: 'green'
                            }
                        ]
                    )
                }
            }
           
        }
      
      
    ]
};
   return option;
}

function grdwebAccessWord(data,grdWebAccessWord){
	grdWebAccessWord.setHeader("序号,搜索词,浏览量,访客数,IP数,跳出率,访问时长",["background-color:blue","color:white"]);//the headers of columns  
// 	grdWebAccessWord.setStyle(
// 	     "background-color:white;color:white; font-weight:bold;",
// 		 "background-color:white;color:black;", "", ""
// 	);	
	grdWebAccessWord.setInitWidths("40,*,*,*,*,*,*");          //the widths of columns  
	grdWebAccessWord.setColAlign("right,left,left,left,left,left,left");       //the alignment of columns   
	grdWebAccessWord.setColTypes("ro,ro,ro,ro,ro,ro,ro");                //the types of columns  
	grdWebAccessWord.setColSorting("int,str,int,int,int,int,int");          //the sorting types   
	grdWebAccessWord.init();      //finishes initialization and renders the grid on the page
	//grdWebAccessWord.enablePaging(true, 10, 5, "pagingArea", false);
	//grdWebAccessWord.enableSmartRendering(true,data.length,10);//
	//grdWebAccessWord.setPagingSkin("bricks");
	var grdData={
	    rows:[]
	};
	for(var i=0;i<data.length;i++){
		
	    grdData.rows.push({
	        id: i+1,		
	        data: [i+1, data[i].detail_name,//搜索词
			       data[i].access_count?data[i].access_count:0,//浏览量
				   data[i].user_count?data[i].user_count:0,//访客数
			       data[i].ip_count ? data[i].ip_count : 0,//ip数
				   format(data[i].fail_rate ?data[i].fail_rate*100:0)+"%",//跳出率
			       formatSeconds(data[i].access_time_total),//访问时长
		
				   ]
	    });
	}
	grdWebAccessWord.parse(grdData,"json"); //takes the name and format of the data source
} 
//csv导出
function exportCvs(data){
            var title =["序号,搜索词,浏览量,访客数,IP数,跳出率,访问时长"];
            var str = [];
            str.push(title.join(",")+"\r\n");
            for(var i=0;i<data.length;i++){
                var temp = [];           
                    temp.push(i+1, data[i].detail_name,//搜索词
			       data[i].access_count?data[i].access_count:0,//浏览量
				   data[i].user_count?data[i].user_count:0,//访客数
			       data[i].ip_count ? data[i].ip_count : 0,//ip数
				   format(data[i].fail_rate ?data[i].fail_rate*100:0)+"%",//跳出率
			       formatSeconds(data[i].access_time_total));
                str.push(temp.join(",")+"\r\n");
            }
            var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str.join(""));
            var downloadLink = document.createElement("a");
            downloadLink.href = uri;
            downloadLink.download = new Date().toISOString().substring(0,10)+"-搜索词来源详情.csv";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
};