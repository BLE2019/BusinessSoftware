
/**
 * 订单预测
 * */
function createOrderCountPredictOption(data){

    var dates = [];
    var predicts = [];
    var forecast = [];
    var low = [];
    data.predict[0].map(function (item, index, array) {
        dates.push(item.datetime);
        if (index<data.real.length) {
        	low.push({'datetime': item.datetime, 'order_count': item.predict});
        	predicts.push({'datetime': item.datetime, 'order_count': 0});
        	forecast.push({'datetime': item.datetime, 'order_count': '_'});
        }else{
        	var low_value = data.predict[2][index-data.real.length].predict;
        	if (low_value < 0) {
        		low_value = 0;
        	}
        	low.push({'datetime': item.datetime, 'order_count': low_value});
        	predicts.push({'datetime': item.datetime, 'order_count': item.predict-low_value});
        	forecast.push({'datetime': item.datetime, 'order_count': data.predict[1][index-data.real.length].predict});
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
            data: dates,
            axisLabel: {
                formatter: function (value, idx) {
                    return value;
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
                    return val;
                }
            },
            axisPointer: {
                show : false,
                label: {
                    formatter: function (params) {
                        return format(params.value, 0);
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
                return format(item.order_count < 0 ? 0 : item.order_count, 0);
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
            	if (item.order_count<0) {
            		return format(0, 0);
            	}
                return format(item.order_count < 0 ? 0 : item.order_count, 0);
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
                return format(item.order_count < 0 ? 0 : item.order_count, 0);
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
                return format(item.order_count < 0 ? 0 : item.order_count, 0);
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


/**
 * 用户预测
 * */
function createUserCountPredictOption(data){

    var dates = [];
    var predicts = [];
    var forecast = [];
    var low = [];
    data.predict[0].map(function (item, index, array) {
        dates.push(item.datetime);
        if (index<data.real.length) {
        	low.push({'datetime': item.datetime, 'user_count': item.predict});
        	predicts.push({'datetime': item.datetime, 'user_count': 0});
        	forecast.push({'datetime': item.datetime, 'user_count': '_'});
        }else{
        	var low_value = data.predict[2][index-data.real.length].predict;
        	if (low_value < 0) {
        		low_value = 0;
        	}
        	low.push({'datetime': item.datetime, 'user_count': low_value});
        	predicts.push({'datetime': item.datetime, 'user_count': item.predict-low_value});
        	forecast.push({'datetime': item.datetime, 'user_count': data.predict[1][index-data.real.length].predict});
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
            data: dates,
            axisLabel: {
                formatter: function (value, idx) {
                    return value;
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
                    return val;
                }
            },
            axisPointer: {
                show : false,
                label: {
                    formatter: function (params) {
                        return format(params.value, 0);
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
                return format(item.user_count< 0 ? 0 : item.user_count, 0);
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
            	if (item.user_count<0) {
            		return format(0, 0);
            	}
                return format(item.user_count < 0 ? 0 : item.user_count, 0);
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
                return format(item.user_count < 0 ? 0 : item.user_count, 0);
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
                return format(item.user_count < 0 ? 0 : item.user_count, 0);
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

//转化率预测
function createconversionratePredictOption(data){
    var months = [];
    var predicts = [];
    var forecast = [];
    var low = [];
    data.predict[0].map(function (item, index, array) {
        months.push(item.datetime);
        if (index<data.real.length) {
            low.push({'datetime': item.datetime, 'conversoinrate': item.predict});
            predicts.push({'datetime': item.datetime, 'conversoinrate': 0});
            forecast.push({'datetime': item.datetime, 'conversoinrate': '_'});
        } else {
            var low_value = data.predict[2][index-data.real.length].predict;
            if (low_value < 0) {
                low_value = 0;
            }
            low.push({'datetime': item.datetime, 'conversoinrate': low_value});
            predicts.push({'datetime': item.datetime, 'conversoinrate': item.predict-low_value});
            forecast.push({'datetime': item.datetime, 'conversoinrate': data.predict[1][index-data.real.length].predict});
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
	                return value;
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
	                return val;
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
	        splitNumber: 4,
	        splitLine: {
	            show: false
	        }
	    },
	    series: [
			{
	        name: '实际',
	        type: 'line',
	        data: data.real.map(function (item) {
	            return format(item.conversoinrate);
	        }),
	        hoverAnimation: false,
	        symbolSize: 6,
	        itemStyle: {
	            normal: {
	                color: '#F19B2C'
	            }
	        },
	        showSymbol: false
	    },{
            type: 'line',
            data: low.map(function (item) {
            	if (item.conversoinrate<0) {
            		return format(0);
            	}
                return format(item.conversoinrate);
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
                return format(item.conversoinrate);
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
                return format(item.conversoinrate<0?0:item.conversoinrate);
            }),
            hoverAnimation: false,
            symbolSize: 6,
            itemStyle: {
                normal: {
                    color: '#1082C9'
                }
            },
            showSymbol: false
        }
        ]
	};
	// 使用刚指定的配置项和数据显示图表。
	return option;
}
