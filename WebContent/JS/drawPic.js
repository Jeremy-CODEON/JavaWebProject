function drawPiePic(documentId, data) {
	var legendData = [];
	for ( var item in data) {
		legendData.push(data[item].name);
	}

	var myChart = echarts.init(document.getElementById(documentId));

	// 指定图表的配置项和数据
	var option = {
		color : [ '#9400D3', '#7B68EE', '#0000FF', '#4682B4', '#008B8B',
				'#40E0D0', '#228B22', '#FFD700', '#FFA500', '#FF4500',
				'#D2691E' ],
		tooltip : {
			trigger : 'item', // item时显示该点数据，axis时显示该列下所有所有坐标轴对应的数据
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : { // 图例
			orient : 'vertical',
			left : 'left',
			data : legendData,
			textStyle : {
				"fontSize" : 16,
				color : "#AAAAAA"
			}
		},
		series : [ {
			name : '销售量',
			type : 'pie',
			radius : '50%',
			center : [ '50%', '50%' ], // 饼图定位
			radius : [ '50%', '70%' ],
			data : data,
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			},
			label : {
				normal : {
					show : true,
					textStyle : {
						"fontSize" : 20
					}
				}
			}
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}

// ------------------------------------------//
function drawProductPic(documentId, data) {
	var legendData = [];
	var countData = [];
	for ( var item in data) {
		legendData.push(data[item].name);
	}
	for ( var item in data) {
		countData.push(data[item].value);
	}

	var myChart = echarts.init(document.getElementById(documentId));

	var option = {
		tooltip : {
			trigger : 'item', // item时显示该点数据，axis时显示该列下所有所有坐标轴对应的数据
			formatter : "{a} <br/>{b} : {c} "
		},
		legend : { // 图例
			orient : 'vertical',
			left : 'left',
			data : legendData,
			textStyle : {
				fontSize : 20
			}
		},
		xAxis : {
			name : '音乐类别',
			nameTextStyle : {
				fontSize : 16
			},
			data : legendData,
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		yAxis : {
			name : '销售额/元',
			nameTextStyle : {
				fontSize : 16
			},
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		color : [ '#00F5FF' ],
		series : [ {
			name : '销售额',
			type : 'bar',
			label : { // 每个柱子顶端显示数值
				normal : {
					show : true,
					position : 'top',
					textStyle : {
						color : [ "#AAAAAA" ],
						fontSize : 16
					}
				}
			},
			data : countData,

		} ]
	}

	myChart.setOption(option);
}

//------------------------------------------//
function drawProductHistoryLine(documentId, data) {
	var legendData = [];
	var countData = [];
	for ( var item in data) {
		legendData.push(data[item].name);
	}
	for ( var item in data) {
		countData.push(data[item].value);
	}

	var myChart = echarts.init(document.getElementById(documentId));

	var option = {
		tooltip : {
			trigger : 'item', // item时显示该点数据，axis时显示该列下所有所有坐标轴对应的数据
			formatter : "{a} <br/>{b} : {c} "
		},
		legend : { // 图例
			orient : 'vertical',
			left : 'left',
			data : legendData,
			textStyle : {
				fontSize : 20
			}
		},
		xAxis : {
			name : '已上架月份',
			nameTextStyle : {
				fontSize : 16
			},
			data : legendData,
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		yAxis : {
			name : '销售量/次',
			nameTextStyle : {
				fontSize : 16
			},
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		color : [ '#00F5FF' ],
		series : [ {
			name : '历史销量走势',
			type : 'line',
			label : { // 每个柱子顶端显示数值
				normal : {
					show : true,
					position : 'top',
					textStyle : {
						color : [ "#AAAAAA" ],
						fontSize : 16
					}
				}
			},
			data : countData,

		} ]
	}

	myChart.setOption(option);
}

//------------------------------------------//
function drawBrowseLine(documentId, data) {
	var legendData = [];
	var countData = [];
	for ( var item in data) {
		legendData.push(data[item].name);
	}
	for ( var item in data) {
		countData.push(data[item].value);
	}

	var myChart = echarts.init(document.getElementById(documentId));

	var option = {
		grid:{
	        x:100,
	        y:50,
	        x2:100,
	        y2:50,
	        borderWidth:50
	    },    
		tooltip : {
			trigger : 'item', // item时显示该点数据，axis时显示该列下所有所有坐标轴对应的数据
			formatter : "{a} <br/>{b} : {c} "
		},
		legend : { // 图例
			orient : 'vertical',
			left : 'left',
			data : legendData,
			textStyle : {
				fontSize : 20
			}
		},
		xAxis : {
			name : '用户年龄段',
			nameTextStyle : {
				fontSize : 16
			},
			data : legendData,
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		yAxis : {
			name : '平均登录时长/min',
			nameTextStyle : {
				fontSize : 16
			},
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		color : [ '#00F5FF' ],
		series : [ {
			name : '各年龄段用户平均登录时长',
			type : 'line',
			label : { // 每个柱子顶端显示数值
				normal : {
					show : true,
					position : 'top',
					textStyle : {
						color : [ "#AAAAAA" ],
						fontSize : 16
					}
				}
			},
			data : countData,

		} ]
	}

	myChart.setOption(option);
}

//------------------------------------------//
function drawActiveLine(documentId, data) {
	var legendData = [];
	var countData = [];
	for ( var item in data) {
		legendData.push(data[item].name);
	}
	for ( var item in data) {
		countData.push(data[item].value);
	}

	var myChart = echarts.init(document.getElementById(documentId));

	var option = {
		grid:{
            x:100,
            y:50,
            x2:100,
            y2:50,
            borderWidth:50
        },
		tooltip : {
			trigger : 'item', // item时显示该点数据，axis时显示该列下所有所有坐标轴对应的数据
			formatter : "{a} <br/>{b} : {c} "
		},
		legend : { // 图例
			orient : 'vertical',
			left : 'left',
			data : legendData,
			textStyle : {
				fontSize : 20
			}
		},
		xAxis : {
			name : '用户年龄段',
			nameTextStyle : {
				fontSize : 16
			},
			data : legendData,
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		yAxis : {
			name : '活跃度指标',
			nameTextStyle : {
				fontSize : 16
			},
			axisLine : {
				lineStyle : {
					color : "#AAAAAA", // X轴及其文字颜色
				}

			},
			axisLabel : {
				show : true,
				fontSize : 16
			}
		},
		color : [ '#00F5FF' ],
		series : [ {
			name : '各年龄段用户活跃度',
			type : 'line',
			label : { // 每个柱子顶端显示数值
				normal : {
					show : true,
					position : 'top',
					textStyle : {
						color : [ "#AAAAAA" ],
						fontSize : 16
					}
				}
			},
			data : countData,

		} ]
	}

	myChart.setOption(option);
}