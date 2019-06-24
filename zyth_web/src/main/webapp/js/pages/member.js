function createUserCountOption(data){

    var usercounts=[];//客户数
	var ordercounts=[];//订单数
	var userates=[];//客户数累计占比
	var rate=0;
	for(var i=0;i<data.length;i++){
		usercounts.push(data[i].all_user_count);
		ordercounts.push(data[i].order_count);
		rate+=data[i].all_user_rate*100;
		userates.push(rate);
	}
	
	option = {
	backgroundColor:"#000000",
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                //color: '#999'
            }
        }
    },
   
	visualMap:
	{
	 calculable : true,
	  orient: 'horizontal',
	  top: '',
	  symbolSize:[0, 5],
	  left:'center',
	  min:0,
	  textStyle: {
	         color: '#ffffff'
	     },
	 // max:maxusercounts,
	 max:Math.max.apply(null,usercounts),
	  //text: ['900','0' ],
	  // Map the score column to color
	  dimension: 1,
	  seriesIndex:0,
	  inRange: {
	      color: ['#91D099', '#009900']
	  }
	  },
    textStyle: {
            color: '#ffffff'
        },
    xAxis: [
        {
            type: 'category',
            name:'订单数',
			nameLocation:'middle',
			nameGap:23,
            data: ordercounts,
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '客户数',
            //min: 0,
            //max: 1000,
            //interval: 250,
            axisLabel: {
                formatter: '{value} '
            }
        },
        {
            type: 'value',
            name: '客户数累计占比',
			//offset:-13,
            //min: 0,
            //max: 100,
            //interval: 25,
            axisLabel: {	             
                formatter: '{value}%'
            }
        }
    ],
   
	series: [
        {
            name:'客户数',
            type:'bar',
            data:usercounts,
			visualMap: true,

			
        },
       
	   {
            name:'客户数累计占比',
            type:'line',
            yAxisIndex: 1,
            data:userates,
			visualMap: false,
       itemStyle:{
       	 normal:{
       	         color:"yellow"     	 
       	}
       } 
	 }
	   
	]
  
};
 return option;
}

function creategrduserRanking(data,grduserRanking){
	 //the path to images required by grid 
	//grduserRanking.setImagePath("./codebase/imgs/");
	
    //grduserRanking.enableAlterCss("even","uneven");
	grduserRanking.setHeader("序号,客户,总消费,产生利润,利润率,订单量,客单价,城市数,首次购买年份");//the headers of columns  
// 	grduserRanking.setStyle(
// 	     "background-color:white;color:white; font-weight:bold;",
// 		 "background-color:white;color:black;",
// 		 "",
// 		 ""
// 	);
	
	grduserRanking.setInitWidths("40,*,*,*,*,*,*,*,*");          //the widths of columns  
	grduserRanking.setColAlign("right,left,left,left,left,left,left,left,left");       //the alignment of columns   
	grduserRanking.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro");                //the types of columns  
	grduserRanking.setColSorting("int,str,int,int,int,int,int,int,int");          //the sorting types   
	grduserRanking.init();      //finishes initialization and renders the grid on the page
	grduserRanking.enablePaging(true, 10, 5, "pagingArea", false);
	//grduserRanking.enableSmartRendering(true,data.length,10);//

	// grduserRanking.setPagingSkin("bricks","dhx_web");
	grduserRanking._pgn_custom=function(page,start,end){
     /* page - the current page
         start - the first visible row
         last - the last visible row */
     var html='<input type="button" value="上一页" style="background-color:black;border:none;"><span>当前页: '+page
       +'</span><input type="button" value="下一页" style="background-color:black;border:none;">';
     grduserRanking._pgn_parentObj.innerHTML=html;
     grduserRanking._pgn_parentObj.childNodes[0].onclick=function() { // the previous button logic
     grduserRanking.changePageRelative(-1);
     }
     grduserRanking._pgn_parentObj.childNodes[2].onclick=function() { // the next button logic
     grduserRanking.changePageRelative(1);
     }
    }
    grduserRanking.setPagingSkin("custom");
	
	
	var grdData={
	    rows:[]
	};
	for(var i=0;i<data.length;i++){
		
	    grdData.rows.push({
	        id: i+1,		
	        data: [i+1, data[i].user_id,//客户
			       format(data[i].user_amount?data[i].user_amount:0),//总消费
				   format(data[i].sales_profit?data[i].sales_profit:0),//利润
			       format(data[i].sales_profit_rate ? data[i].sales_profit_rate*100 : 0)+"%",//利润率
				   data[i].order_count ?data[i].order_count:0,//订单量
			       format(data[i].user_avg_amount?data[i].user_avg_amount:0),//客单价
				   data[i].address_count?data[i].address_count:0,//城市数
			       data[i].min_date.substring(0, 4)//首次购买年份
				   ]
	    });
	}
	grduserRanking.parse(grdData,"json"); //takes the name and format of the data source
}

//csv导出
function exportCvs(data){
            var title =["序号,客户,总消费,产生利润,利润率,订单量,客单价,城市数,首次购买年份"];
            var str = [];
            str.push(title.join(",")+"\r\n");
            for(var i=0;i<data.length;i++){
                var temp = [];           
                    temp.push(i+1, data[i].user_id,//客户
			       format(data[i].user_amount?data[i].user_amount:0),//总消费
				   format(data[i].sales_profit?data[i].sales_profit:0),//利润
			       format(data[i].sales_profit_rate ? data[i].sales_profit_rate*100 : 0)+"%",//利润率
				   data[i].order_count ?data[i].order_count:0,//订单量
			       format(data[i].user_avg_amount?data[i].user_avg_amount:0),//客单价
				   data[i].address_count?data[i].address_count:0,//城市数
			       data[i].min_date.substring(0, 4)//首次购买年份);
				   );
                str.push(temp.join(",")+"\r\n");
            }
            var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str.join(""));
            var downloadLink = document.createElement("a");
            downloadLink.href = uri;
            downloadLink.download = new Date().toISOString().substring(0,10)+"-客户消费排行榜.csv";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
};