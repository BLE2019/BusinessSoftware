
function createTrendOption(data){
    var months = [];
    var categorys = [];
    var sales = [];
    
    var lineColors = new Array('#096','#ffde33','#ff9933','#cc0033','#660099','#7e0023');
    var borderColors = new Array("rgba(1, 242, 238, 0.5)","rgba(255, 234, 0, 0.5)","rgba(102, 255, 0, 0.5)","rgba(102, 255, 0, 0.5)","rgba(102, 255, 0, 0.5)","rgba(102, 255, 0, 0.5)");
    var series = [categorys.length];

    var monIndex = -1;
    for (var i = 0; i < data.length; i++) {
        var newMon = false;
        var newCate = $.inArray(data[i].name, categorys) == -1;
        if((data[i].datetime.length >= 7)){
            var month = data[i].datetime.substring(0, 4)+"年"+data[i].datetime.substring(5, 7)+"月";
            newMon = $.inArray(month, months) == -1;
            if(newMon){
                months.push(month);
                monIndex++;
            }
        }
        if(newCate){
            var cateIndex = categorys.length;
            categorys.push(data[i].name);
            sales.push([]);

            for (var j = 0; j < monIndex; j++) {
                sales[cateIndex].push("_");
            }
            var serie = {
                name: data[i].name,
                smooth: true,
                type: 'line',
                symbolSize: 5,
                symbol: 'circle',
                itemStyle: {
                    normal: {
                        label: {
                            formatter: function(params) {
                                return 100 - params.value;
                            },
                            fontSize: 40,
                            padding: [90, 0, 0, 0],
                            color: '#fff',
                            textStyle: {
                                baseline: 'top'
                            }
                        },
                        color: lineColors[cateIndex],
                        borderColor: borderColors[cateIndex],
                        borderWidth: 3
                    }
                },
                showSymbol: false,
                data: sales[cateIndex]
            };
            series.push(serie);
        }
        var x = $.inArray(data[i].name, categorys);
        sales[x].push(format(data[i].sales));
    }

    // lineStyle: {
    //     normal: {
    //         type: 'dotted'
    //     }
    // },
    // tooltip: {
    //     formatter: '{b0},{a0}: {c0}<br />{b2}'
    // },
    //var xAxisData = ['2018年7月', '2018年8月', '2018年9月', '2018年10月', '2018年11月', '2018年12月', '2019年1月'];
    var xAxisData = months;
    var firstLineData = [90, 50, 39, 50, 120, 85, '_'];
    var firstLineDottedData = ['_', '_', '_', '_', '_', 85, 100];
    var sendLineData = [70, 50, 50, 87, 90, 42, '_'];
    var sendLineDottedData = ['_', '_', '_', '_', '_', 42, 65];
    var threeLineData = [220, 182, 191, 234, 290, 78, '_'];
    var threeLineDottedData = ['_', '_', '_', '_', '_', 78, 180];

    option = {
        title: {
            text: '类别',
            subtext: '',
            left: '1%',
            top: '8',
            textStyle: {
                color: '#FFFFFF',
                fontSize: 11,
            }
        },
        legend: {
            data: categorys,
            top: '8',
			left:'40px',
            textStyle: {
                color: '#FFFFFF',
                fontSize: 11,
            }
        },
        textStyle: {
            color: '#FFFFFF'
        },
        backgroundColor: '#000000',
        tooltip: {
            show: true,
            trigger: 'item'
        },
        grid: {
            left: '1%',
            right: '10%',
            top: '20%',
            bottom: '4%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            axisTick: {
                show: false
            },
            axisLine: {
                show: false,
                lineStyle: {
                    color: '#6995aa'
                },
                onZero: true
            },
            axisLabel: {
                fontSize: 11,
                color: '#FFFFFF'
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#626262'
                }
            },
            data: xAxisData
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: false,
                lineStyle: {
                    color: '#6995aa'
                },
                onZero: false
            },
            axisLabel: {
                fontSize: 11,
                color: '#FFFFFF',
                formatter: function (val) {
                    return val/1000+'K';
                }
            },
            splitNumber: 3,
            splitLine: {
                lineStyle: {
                    color: '#626262'
                }
            },
            scale: false
        },
        series: series
    };
    // 使用刚指定的配置项和数据显示图表。
    return option;
}

function createDistributedOption(data){
    var map = [];
    for (var i = 0; i < data.length; i++) {
        map.push({
            "name": data[i].province.replace(/省/g,"").replace(/市/g,""),
            "value": format(data[i].sales_amount)
        });
    }

    option = {
        backgroundColor:"#000000",
        color: '#FFFFFF',
        tooltip: {
            trigger: 'item',
            formatter: function(params) {
                if(params.name == ""){
                    return "";
                }
                return params.name+": "+params.value;
            },
        },
        visualMap: {
            type: 'continuous', // 定义为连续型 viusalMap
            min: 0,
            max: 170000,
            left: 'right',
            top: 'top',
            text: ['高', '低'],
            calculable: true,
            itemWidth: 5,
            itemHeight: 120,
            color: ['#046ACD', '#9EBFD9'],
            textStyle: {
                color: '#FFFFFF',
                fontSize: 9,
            }
        },
        geo: {
            map: 'china',
            zoom:1.0,
            layoutCenter : ['50%','50%'],
            layoutSize : '100%',

        },
        series: [
            {
                type: 'map',
                mapType: 'china',
                showLegendSymbol: false,
                zoom:1.0,
                layoutCenter : ['50%','50%'],
                layoutSize : '100%',
                label: {
                    normal: {
                        show: false,/*是否城市名字*/
                        textStyle:{
                            color:'rgba(255,255,255,0.2)'
                        }
                    },
                    emphasis: {
                        show: false
                    }
                },
                itemStyle: {
                    normal:{
                    }
                },
                data: map
            }
        ]
    }
    return option;
}


function createSalesRanking(data, grdRanking){
    //the path to images required by grid 
    grdRanking.setImagePath("./codebase/imgs/");
    grdRanking.setHeader("序号, 省/自治区, 销售额, 利润");//the headers of columns  
    grdRanking.setInitWidths("40,100,80,*");       //the widths of columns  
    grdRanking.setColAlign("right,left,left,left");       //the alignment of columns   
    grdRanking.setColTypes("ro,ed,ed,ed");                //the types of columns  
    grdRanking.setColSorting("int,str,int,int");          //the sorting types   
	// grduserRanking.setStyle("overflow:hidde")
    grdRanking.init();      //finishes initialization and renders the grid on the page
    var grdData={
        rows:[]
    };
    for(var i=0;i<data.length;i++){
        grdData.rows.push({
            id: i+1,
            data: [i+1, data[i].province, format(data[i].sales_amount), format(data[i].sales_profit)]
        });
    }
    grdRanking.parse(grdData,"json"); //takes the name and format of the data source
}

//csv导出
function exportCvs(data){
            var title =["序号, 省/自治区, 销售额, 利润"];
            var str = [];
            str.push(title.join(",")+"\r\n");
            for(var i=0;i<data.length;i++){
                var temp = [];           
                    temp.push(i+1, data[i].province, format(data[i].sales_amount), format(data[i].sales_profit));
                str.push(temp.join(",")+"\r\n");
            }
            var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str.join(""));
            var downloadLink = document.createElement("a");
            downloadLink.href = uri;
            downloadLink.download = new Date().toISOString().substring(0,10)+"-各省销售额排行.csv";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
};
