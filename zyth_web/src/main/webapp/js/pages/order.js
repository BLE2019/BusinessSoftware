
// 销售额 && 利润
function createSalesProfitAreaOption(data, tag) {

    var salesProfitTag = tag == "sales" ? "销售额" : "利润";

    var years = [];
    var categorys = [];
    var sales = [];

    var lineColors = new Array('#096', '#ffde33', '#ff9933', '#cc0033', '#660099', '#7e0023');
    var series = [categorys.length];

    var yearIndex = -1;
    for (var i = 0; i < data.length; i++) {
        if(data[i].target != salesProfitTag) {
            continue;
        }
        var newYear = false;
        var newCate = $.inArray(data[i].area_name, categorys) == -1;
        if ((data[i].create_datetime.length >= 7)) {
            var year = data[i].create_datetime.substring(0, 4)+"年";
            newYear = $.inArray(year, years) == -1;
            if (newYear) {
                years.push(year);
                yearIndex++;
            }
        }

        if (newCate) {
            var cateIndex = categorys.length;
            categorys.push(data[i].area_name);
            sales.push([]);

            for (var j = 0; j < yearIndex; j++) {
                sales[cateIndex].push("_");
            }
            var serie = {
                name: data[i].area_name,
                smooth: true,
                type: 'bar',
                symbolSize: 5,
                symbol: 'circle',
                itemStyle: {
                    normal: {
                        label: {
                            formatter: function (params) {
                                return 100 - params.value;
                            },
                            fontSize: 40,
                            padding: [90, 0, 0, 0],
                            color: '#000',
                            textStyle: {
                                baseline: 'top'
                            }
                        },
                        color: lineColors[cateIndex],
                        borderWidth: 3
                    }
                },
                data: sales[cateIndex]
            };
            series.push(serie);
        }
        var x = $.inArray(data[i].area_name, categorys);
        sales[x].push(data[i].salemoney);
    }

    var xAxisData = years;

    option = {
        title: {
            text: '类别',
            subtext: '',
            left: '1%',
            show: tag == "sales" ? true : false,
            top: '1',
            textStyle: {
                color: '#ffffff',
                fontSize: 11,
            }
        },
        legend: {
            data: categorys,
            top: '2',
            left: tag == "sales" ? "10%" : "1%",
            show: tag == "sales" ? true : false,
            itemGap: 1,
            textStyle: {
                color: '#ffffff',
                fontSize: 8,
            }
        },
        textStyle: {
            color: '#ffffff'
        },
        backgroundColor: '#000000',
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'

            }
        },
        grid: {
            left: '3%',
            right: '5%',
            top: tag == "sales" ? '40%' : '5%',
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
                color: '#ffffff'
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#626262'
                }
            },
            // data: tag == "sales" ? "" : xAxisData
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
                color: '#ffffff',
                formatter: function (val) {
                    if (val == 0) return tag == "sales" ? "销售额 0K" : "   利润 0K"
                    return tag == "sales" ?  val / 1000 + 'K' :  val / 1000 + 'K';
                }
            },
            splitNumber: 2,
            splitLine: {
                lineStyle: {
                    // color: '#626262'
                }
            },
            scale: false
        },
        series: series
    };
    // 使用刚指定的配置项和数据显示图表。

    return option;
}

// 销售额 & 利润 趋势
function createTrendFrofitAreaOption(data) {

    var months = [];
    var categorys = [];
    var sales = [];
    var series = [categorys.length];

    var lineColors = new Array('rgb(137,189,27)','rgb(0,136,212)');
    var borderColors = new Array("rgba(137, 189, 27, 0.3)","rgba(0, 136, 212, 0.3)");
    var borderColorsOffset_0 = new Array("rgba(137, 189, 27, 0.2)","rgba(0, 136, 212, 0.2)");
    var borderColorsOffset_08 = new Array("rgba(137, 189, 27, 0)", "rgba(0, 136, 212, 0)", )

    var monIndex = -1;
    for (var i = 0; i < data.length; i++) {
        var newMon = false;
        var newCate = $.inArray(data[i].target, categorys) == -1;
        if (data[i].create_datetime == null) {
            continue;
        }
        if((data[i].create_datetime.length >= 7)){
            var month = data[i].create_datetime.substring(0, 4)+"年"+data[i].create_datetime.substring(5, 7)+"月";
            newMon = $.inArray(month, months) == -1;
            if(newMon){
                months.push(month);
                monIndex++;
            }
        }
        if(newCate){
            var cateIndex = categorys.length;
            categorys.push(data[i].target);
            sales.push([]);

            for (var j = 0; j < monIndex; j++) {
                sales[cateIndex].push("_");
            }
            var serie = {
                name: data[i].target,
                smooth: true,
                type: 'line',
                symbolSize: 5,
                symbol: 'circle',
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: borderColorsOffset_0[cateIndex]
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: lineColors[cateIndex],
                        borderColor: borderColors[cateIndex],
                        borderWidth: 12
                    }
                },
                data: sales[cateIndex]
            };
            series.push(serie);
        }
        var x = $.inArray(data[i].target, categorys);
        sales[x].push(data[i].salemoney);
    }

    var xAxisData = months;


    option = {
        backgroundColor: '#000000',
        title: {
            text: '指标',
            textStyle: {
                fontWeight: 'normal',
                fontSize: 11,
                color: '#ffffff'
            },
            left: '6%'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#57617B'
                }
            }
        },
        legend: {
            icon: 'rect',
            itemWidth: 14,
            itemHeight: 5,
            itemGap: 13,
            data: categorys,
            right: '4%',
            textStyle: {
                fontSize: 12,
                color: '#ffffff'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLine: {
                show: false,
                lineStyle: {
                    color: '#6995aa'
                },
                onZero: false
            },
            axisLabel: {
                fontSize: 11,
                color: '#ffffff'
            },
            data: xAxisData
        }],
        yAxis: [{
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
                margin: 10,
                fontSize: 11,
                color: '#ffffff',
                textStyle: {
                    fontSize: 14
                },
                formatter: function (val) {
                    return val/10000+'W';
                }
            },
            splitNumber: 3,
            splitLine: {
                lineStyle: {
                    // color: '#57617B'
                }
            }
        }],
        series: series
    };

    return option;

}

// 支付方式
function createPayWayOption(data) {

    // data = [{"payway": "支付宝", "records": "100"},
    // {"payway": "微信", "records": "300"},
    // {"payway": "333", "records": "400"},
    // {"payway": "222", "records": "500"}];

    var payWayName = [];
    var paywayRecords = [];
    var seriesData = [];
    console.log(data);

    for (var i = 0; i < data.length; i++) {
        // 支付方式
        payWayName.push(data[i].payway);
        // 支付方式的数值
        paywayRecords.push(data[i].records);
        // seriesData
        seriesData.push({value: data[i].records, name: data[i].payway});
    }


    option = {
        title : {
            text: '',
            show: false,
            subtext: '',
            x:''
        },
        tooltip : {
            trigger: 'item',
            formatter: '{b} {c}'
        },
        legend: {
            orient: 'vertical',
            show: false,
            left: 'left',
            data: payWayName
        },
        series : [
            {
                name: '支付方式',
                type: 'pie',
                radius : '50%',
                center: ['50%', '40%'],
                data: seriesData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    },
                    normal: {
                        label: {
                            show: true,
                            formatter:'{b}' + '\n' + '({d}%)'
                        }
                    }
                    
                }
            }
        ]
    };
    

    return option;
}

// 订单来源
function createOrderSourceOption(data) {

    var orderSourceName = [];
    var orderRecords = [];
    var seriesData = [];
    console.log(data);

    for (var i = 0; i < data.length; i++) {
        // 订单来源
        orderSourceName.push(data[i].ordersource);
        // 订单来源的数值
        orderRecords.push(data[i].records);
        // seriesData
        seriesData.push({value: data[i].records, name: data[i].payway});
    }

    var colorList = ['#2eddc1', '#FCCE10', '#E87C25', '#27727B','#9efdc6', '#f27C99', '#a27C99', '#27727B' ];

    option = {
        title: {
            text: '订单数量',
            textStyle: {
                fontWeight: 'normal',
                fontSize: 11,
                color: '#ffffff'
            },
            left: '3%'
        },
        tooltip: {
            trigger: 'item',
            formatter:'{b}'　　　
        },
        grid: {
            left: '3%',
            right: '5%',
            top: '10%',
            bottom: '20%',
            containLabel: true
        },
        xAxis: {
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
                color: '#ffffff'
            },
            data: orderSourceName
        },
        yAxis: {
            splitNumber: 2,
            axisLine: {
                show: false,
                lineStyle: {
                    color: '#ffffff'
                },
                onZero: true
            },
            axisLabel: {
                fontSize: 11,
                color: '#ffffff'
            }
        },
        series: [{
            type: 'bar',
            barWidth : 30,
            itemStyle: {
                normal: {
                    color: function(params) {
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{c}'
                    }
                }
            },
            data: orderRecords
        }]
    };
    

    return option;
}


function createPredictOption(data){
    var months = [];
    var predicts = [];
    var forecast = [];
    var low = [];
    data.predict[0].map(function (item, index, array) {
        months.push(item.datetime);
        if (index<data.real.length) {
        	low.push({'datetime': item.datetime, 'sales': item.predict});
        	predicts.push({'datetime': item.datetime, 'sales': 0});
        	forecast.push({'datetime': item.datetime, 'sales': '_'});
        }else{
        	var low_value = data.predict[2][index-data.real.length].predict;
        	if (low_value < 0) {
        		low_value = 0;
        	}
        	low.push({'datetime': item.datetime, 'sales': low_value});
        	predicts.push({'datetime': item.datetime, 'sales': item.predict-low_value});
        	forecast.push({'datetime': item.datetime, 'sales': data.predict[1][index-data.real.length].predict});
        }
        return item.datetime;
    })

    option = {
        legend: {
            data: ['实际', '估计'],
            top: '8',
            textStyle: {
                color: '#ffffff',
                fontSize: 11,
            }
        },
        backgroundColor: '#000000',
        textStyle: {
            color: '#ffffff'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                animation: false,
                label: {
                    show: false,
                    backgroundColor: '#ccc',
                    borderColor: '#aaa',
                    borderWidth: 1,
                    shadowBlur: 0,
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    textStyle: {
                        color: '#222'
                    }
                }
            },
            formatter: function (params) {
            	if (params.length > 3) {
            		return params[0].name + '<br />实际:' + params[0].value + '<br />估计:' + params[1].value;
            	}else{
					return params[2].name + '<br />实际:' + '-' + '<br />估计:' + params[2].value;
            	}
            }
        },
        grid: {
            left: '3%',
            right: '6%',
            bottom: '3%',
            top: '15%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: months,
            axisLabel: {
                formatter: function (value, idx) {
                    return value.substring(0, 4)+"年"+value.substring(5, 7)+"月";
                }
            },
            splitLine: {
                show: false
            },
            boundaryGap: false
        },
        yAxis: {
            axisLabel: {
                formatter: function (val) {
                    return val/10000+'万';
                }
            },
            axisPointer: {
                show : false,
                label: {
                    formatter: function (params) {
                        return format(params.value);
                    }
                }
            },
            splitNumber: 3,
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '实际',
            type: 'line',
            data: data.real.map(function (item) {
                return format(item.sales);
            }),
            hoverAnimation: false,
            symbolSize: 6,
            itemStyle: {
                normal: {
                    color: '#F19B2C'
                }
            },
            showSymbol: false
        }, {
            type: 'line',
            data: low.map(function (item) {
            	if (item.sales<0) {
            		return format(0);
            	}
                return format(item.sales);
            }),
            hoverAnimation: false,
            symbolSize: 6,
            itemStyle: {
                normal: {
                    color: '#D4EAED'
                }
            },
            stack: 'confidence-band',
            showSymbol: false
        }, {
            type: 'line',
            data: predicts.map(function (item) {
                return format(item.sales);
            }),
            hoverAnimation: false,
            symbolSize: 6,
            itemStyle: {
                normal: {
                    color: '#D4EAED'
                }
            },
            areaStyle: {
                normal: {
                    color: '#ccc'
                }
            },
            stack: 'confidence-band',
            showSymbol: false
        }, {
        	name: '估计',
            type: 'line',
            data: forecast.map(function (item) {
                return format(item.sales);
            }),
            hoverAnimation: false,
            symbolSize: 6,
            itemStyle: {
                normal: {
                    color: '#1082C9'
                }
            },
            showSymbol: false
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    return option;
}
