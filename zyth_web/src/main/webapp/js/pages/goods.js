
function createEvaluateOption(data){
    var categorys = [{
                "name": "销售额",
                "max": 0
            }, {
                "name": "客户数",
                "max": 0
            }, {
                "name": "销售量",
                "max": 0
            }, {
                "name": "均价",
                "max": 0
            }, {
                "name": "利润率",
                "max": 0
            }, {
                "name": "利润",
                "max": 0
            }
    ];
    var series = [];
    var colors = ["rgba(204, 0, 51,", "rgba(102, 0, 153,", "rgba(126, 0, 35,", "rgba(9, 96, 0,", "rgba(255, 222, 51,", "rgba(255, 153, 51,"];
    for(var i=0;i<data.category_name.length;i++){
        if(data.data[i].sales_amount > categorys[0].max){
            categorys[0].max = data.data[i].sales_amount;
        }
        if(data.data[i].user_count > categorys[1].max){
            categorys[1].max = data.data[i].user_count;
        }
        if(data.data[i].qty > categorys[2].max){
            categorys[2].max = data.data[i].qty;
        }
        if(data.data[i].sale_avg_price > categorys[3].max){
            categorys[3].max = data.data[i].sale_avg_price;
        }
        if(data.data[i].sales_profit_rate > categorys[4].max){
            categorys[4].max = data.data[i].sales_profit_rate;
        }
        if(data.data[i].sales_profit > categorys[5].max){
            categorys[5].max = data.data[i].sales_profit;
        }
        series.push({
            "name": data.category_name[i],
            "type": "radar",
            "symbol": "circle",
            "symbolSize": 5,
            "itemStyle":{
                "color":colors[i]+' 0.7)',
                "borderColor":colors[i]+' 0.4)',
                "borderWidth":5,
            },
            "areaStyle": {
                "normal": {
                    "color": colors[i]+' 0.5)'
                }
            },
            "lineStyle": {
                "normal": {
                    "type": "dashed",
                    "color": colors[i]+' 0.8)',
                    "width": 1
                }
            },
            "data": [[data.data[i].sales_amount, data.data[i].user_count, data.data[i].qty, data.data[i].sale_avg_price, format(data.data[i].sales_profit_rate?data.data[i].sales_profit_rate*100:0)+"%", data.data[i].sales_profit]]
        });
    }

    for(var i=0;i<categorys.length;i++){
        categorys[i].max = categorys[i].max*1.1;
    }

    option = {
        "backgroundColor":"black",
        "tooltip": {
            "show": true,
            "trigger": "item"
        },
        "legend": {
            "show": true,
            "icon": "rect",
            "left": "75%",
            "top": "10%",
            "orient": "vertical",
            "textStyle": {
                "fontSize": 11,
                "color": "#ffffff"
            },
            "data": data.category_name
        },
        "radar": {
            "center": ["40%", "50%"],
            "radius": "80%",
            "startAngle": 90,
            "splitNumber": 4,
            "shape": "polygon",
            "splitArea": {
                "areaStyle": {
                    "color": ["transparent"]
                }
            },
            "axisLabel": {
                "show": false,
                "fontSize": 11,
                "color": "#FFFFFF",
                "fontStyle": "normal",
                "fontWeight": "normal"
            },
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#F4F4F4"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": "#F4F4F4"
                }
            },
            "name": {
                "show": true,
                "fontSize": 11,
                "color": "#ffffff",
            },
            "nameGap": 8,
            "indicator": categorys
        },
        "series": series
    };
    return option;
}

function creategrdClassSales(data,grdClassSales){
	//data=[{"zipinlei":"书架","pinlei":"日用品","xiaoshou":"123","lirun":"23","lrunlv":"1.7%","xiaoliang":"1111","kuhushu":"2","renjun":"4","pinjun":"2"},
	//{"zipinlei":"书架","pinlei":"日用品","xiaoshou":"123","lirun":"23","lrunlv":"1.7%","xiaoliang":"1111","kuhushu":"7","renjun":"4","pinjun":"2"}
	//];
	 //the path to images required by grid 
	grdClassSales.setImagePath("./codebase/imgs/");
	grdClassSales.setHeader("序号, 子品类, 品类,销售额, 利润,利润率,销售量,客户数,均价,人均消费");//the headers of columns  
	grdClassSales.setInitWidths("40,*,*,*,*,*,*,*,*,*");          //the widths of columns  
	grdClassSales.setColAlign("right,left,left,left,left,left,left,left,left,left");       //the alignment of columns   
	grdClassSales.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");                //the types of columns  
    grdClassSales.setColSorting("int,str,str,int,int,int,int,int,int,int");          //the sorting types   
//     grdClassSales.setStyle(
//         "background-color:white;color:white; font-weight:bold;",
//         "background-color:white;color:black;"
//    );
	grdClassSales.init();      //finishes initialization and renders the grid on the page
	var grdData={
	    rows:[]
	};
	for(var i=0;i<data.length;i++){
	    grdData.rows.push({
	        id: i+1,
	        data: [i+1, data[i].category_child_name,
			       data[i].category_name,
				   format(data[i].sales_amount?data[i].sales_amount:0),
			       format(data[i].sales_profit?data[i].sales_profit:0),
				   format(data[i].sales_profit_rate?data[i].sales_profit_rate*100:0)+"%",
			       format(data[i].qty ?data[i].qty:0),
				   format(data[i].user_count?data[i].user_count:0),
			       format(data[i].sale_avg_price?data[i].sale_avg_price:0),
				   format(data[i].sale_avg_user?data[i].sale_avg_user:0)]
	    });
	}
	grdClassSales.parse(grdData,"json"); //takes the name and format of the data source
}

//csv导出
function exportCvs(data){
            var title =["序号, 子品类  , 品类  ,销售额  , 利润  ,利润率  ,销售量  ,客户数  ,均价  ,人均消费  "];
            var str = [];
            str.push(title.join(",")+"\r\n");
            for(var i=0;i<data.length;i++){
                var temp = [];           
                    temp.push(i+1, data[i].category_child_name,
			       data[i].category_name,
				   format(data[i].sales_amount?data[i].sales_amount:0),
			       format(data[i].sales_profit?data[i].sales_profit:0),
				   format(data[i].sales_profit_rate?data[i].sales_profit_rate*100:0)+"%",
			       format(data[i].qty ?data[i].qty:0),
				   format(data[i].user_count?data[i].user_count:0),
			       format(data[i].sale_avg_price?data[i].sale_avg_price:0),
				   format(data[i].sale_avg_user?data[i].sale_avg_user:0));
                str.push(temp.join(",")+"\r\n");
            }
            var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str.join(""));
            var downloadLink = document.createElement("a");
            downloadLink.href = uri;
            downloadLink.download = new Date().toISOString().substring(0,10)+"-子品类销售额排行.csv";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
};
