<template>
    <div class="container-body" :style="styleObject">
        <span>这是echarts 图</span>
        <div ref="chart1" style="width:90%;height:316px"></div>
        <div ref="chartPie" style="width:90%;height:376px"></div>
    </div>
</template>

<script type="text/ecmascript-6">
  import {getWindowHeight} from '@/utils/style'
  import {fillCharsRight} from '@/utils/util'
  export default {
    name: "",
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        pieData:[
          {value: 428573.3, ratio:'51.17%', name: '基层医疗卫生机构门急诊人次'},
          {value: 375265.6, ratio:'44.81%', name: '医院门急诊人次          '},
          {value: 33546.5, ratio:'4.01%', name: '专业公共卫生机构门急诊人次'},
          {value: 162, ratio:'0.02%', name: '其他医疗卫生机构门急诊人次'},
        ],
        styleObject:{
          height:this.clientHeight
        }
      }
    },
    mounted(){
      this.getEchartData();
      this.drawPieChart();
      this.styleObject.height = getWindowHeight();
    },
    methods:{
      getEchartData() {
        const chart = this.$refs.chart1;
        console.log("test echarts!!!!!")
        if (chart) {
          let myChart = this.$echarts.init(chart);
          // 指定图表的配置项和数据
          var option = {
            title: {
              text: ''
            },
            tooltip: {},
            legend: {
              data:['门急诊总人数','门急诊总人数增长率']
            },
            xAxis: {
              data: ["2015","2016","2017","2018","2019"]
            },
            yAxis: [
              {
                type: 'value',
                name: '人次',
                min: 0,
                max: 1250,
                interval: 250,
                axisLabel: {
                  formatter: '{value}',
                },
              }, {
                type: 'value',
                name: '%',
                min: -100,
                max: 100,
                interval: 25,
              }
            ],
            series: [{
              name: '门急诊总人数',
              type: 'bar',
              data: [600, 800, 700, 1000, 750]
            },{
              name: '门急诊总人数增长率',
              type: 'line',
              yAxisIndex: 1,
              data: [0,3.18,-46.22,95.17,4.98]
            }]
          };

          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);

          window.addEventListener("resize", function() {
            myChart.resize()
          })
        }

      },
      drawPieChart() {
        let myChart = this.$echarts.init(this.$refs.chartPie);
        var option = {
          title: {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            bottom: '8%',
            formatter:(params)=>{
              console.log('我的参数',params)  //打印出来的数据（营业，网络，房屋，tgtn,g）
              for (let a = 0; a < this.pieData.length; a++) {// this.pieXY  这个数据中有名称和次数
                if (this.pieData[a].name == params) {   //两个名称进行对比，取出对应的次数
                  return fillCharsRight(params,18) + '占比' + this.pieData[a].ratio + '   ' + this.pieData[a].value + '人次'  //然后return你需要的legend格式即可
                }
              }
              return params;
            }
          },
          series: [
            {
              type: "pie",
              radius: "45%",
              center: ["50%", "40%"],
              data: this.pieData,
              label: {
                normal: {
                  show: true,  //设置show为false即可
                  position: 'center'
                }
              }
            }
          ]
        };
        myChart.setOption(option);
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
.container-body
    display: flex
    flex-direction: column
    overflow-y: auto
</style>
