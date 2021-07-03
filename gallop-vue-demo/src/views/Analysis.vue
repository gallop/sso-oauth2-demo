<template>
    <div class="container-body">
        <div>
            <van-nav-bar
                    title="指标数据分析"
                    left-arrow
                    @click-left="onClickLeft"
                    @click-right="onClickRight" style="background-color: #f8f8f8">
                <template #right>
                    <svg-icon style="width: 24px; height: 24px" icon-class="setting"/>
                </template>
            </van-nav-bar>
        </div>
        <ul class="title">
            <li class="title-item">指标：门急诊总人数</li>
            <li class="title-item">数据：837,547.4 万人次 | 2019</li>
            <li class="title-item">区域：中国</li>
        </ul>

        <div ref="chart1" style="width:100%;height:180px;"></div>
        <div ref="chartPie" style="width:100%;height:246px"></div>
        <div class="contentData">
            <span class="content-title">门急诊总人次地域分布(万人)</span>
            <div class="tabs">
                <ul class="tabs">
                    <li id="default-item" class="tab-item" :class="isTabActive == 0 ? 'is-active tab-item:hover' : ''" @click="clickTab(0)">门急诊总人次</li>
                    <li class="tab-item" :class="isTabActive == 1 ? 'is-active' : ''" @click="clickTab(1)">基层医疗卫生机构门急诊人次</li>
                </ul>
                <ul class="tabs">
                    <li class="tab-item" :class="isTabActive == 2 ? 'is-active' : ''" @click="clickTab(2)">医院门急诊人次</li>
                    <li class="tab-item" :class="isTabActive == 3 ? 'is-active' : ''" @click="clickTab(3)">专业公共卫生机构门急诊人次</li>
                </ul>
                <ul>
                    <li class="tab-item" :class="isTabActive == 4 ? 'is-active' : ''" @click="clickTab(4)" style="width: 160px">其他医疗卫生机构门急诊人次</li>
                </ul>

            </div>


        </div>
    </div>
</template>

<script type="text/ecmascript-6">
  import {getWindowHeight,triggerEvent} from '@/utils/style'
  import {fillCharsRight} from '@/utils/util'
  import {NavBar, Tab, Tabs} from 'vant';
  export default {
    name: "",
    components:{
      [NavBar.name]: NavBar,
      [Tab.name]: Tab,
      [Tabs.name]: Tabs
    },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        styleObject:{
          height:this.clientHeight
        },
        pieData:[
          {value: 428573.3, ratio:'51.17%', name: '基层医疗卫生机构门急诊人次',itemStyle: { color: '#005eff' }},
          {value: 375265.6, ratio:'44.81%', name: '医院门急诊人次          ',itemStyle: { color: '#11ec27' }},
          {value: 33546.5, ratio:'4.01%', name: '专业公共卫生机构门急诊人次',itemStyle: { color: 'rgba(238,11,65,0.93)' }},
          {value: 162, ratio:'0.02%', name: '其他医疗卫生机构门急诊人次',itemStyle: { color: '#ff6f00' }},
        ],
        isTabActive:0, // 控制切换的变量
      }
    },
    mounted(){
      this.getEchartData();
      this.drawPieChart();
      this.styleObject.height = getWindowHeight();
      let element = document.getElementById("default-item")
      console.log("element:"+element)
      triggerEvent(element,"mouseOver")
    },
    methods:{
      getEchartData() {
        const chart = this.$refs.chart1;
        console.log("test echarts!!!!!")
        if (chart) {
          let myChart = this.$echarts.init(chart);
          // 指定图表的配置项和数据
          var option = {
            backgroundColor: '#f8f8f8',
            title: {
              text: '门急诊总人次趋势分析',
              textStyle: {
                fontSize: 12,
                fontWeight: 'bold'
              },
              padding:[10,5,10,5],
            },
            grid:{
              top: '35%',
              bottom: '16%'
            },
            tooltip: {},
            legend: {
              show: true,
              top: '35px',
              itemWidth: 11,
              itemHeight: 11,
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
              data: [600, 800, 700, 1000, 750],
              barWidth : 15, // 柱形图宽度
              itemStyle: {
                color: '#4c8efa'

              }
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
            text: '门急诊总人次构成',
            textStyle: {
              fontSize: 12,
              fontWeight: 'bold'
            },
            padding: [10,8]
          },
          legend: [
            {
              orient: 'vertical',
              x: '5%',
              bottom: '2%',
              itemWidth: 12,
              itemHeight: 12,
              formatter:(params)=>{
                //console.log('我的参数',params)  //打印出来的数据（营业，网络，房屋，tgtn,g）
                for (let a = 0; a < this.pieData.length; a++) {// this.pieXY  这个数据中有名称和次数
                  if (this.pieData[a].name == params) {   //两个名称进行对比，取出对应的次数
                    return fillCharsRight(params,18)   //然后return你需要的legend格式即可
                  }
                }
                return params;
              }
            },
            {
              orient: 'vertical',
              bottom: '2%',
              x: '50%',
              //y: '10%',
              itemWidth: 0,
              itemHeight: 0,
              formatter:(params)=>{
                //console.log('我的参数',params)  //打印出来的数据（营业，网络，房屋，tgtn,g）
                for (let a = 0; a < this.pieData.length; a++) {// this.pieXY  这个数据中有名称和次数
                  if (this.pieData[a].name == params) {   //两个名称进行对比，取出对应的次数
                    return  '占比' + this.pieData[a].ratio   //然后return你需要的legend格式即可
                  }
                }
                return params;
              }
            },
            {
              orient: 'vertical',
              bottom: '2%',
              x: '70%',
              //y: '10%',
              itemWidth: 0,
              itemHeight: 0,
              formatter:(params)=>{
                //console.log('我的参数',params)  //打印出来的数据（营业，网络，房屋，tgtn,g）
                for (let a = 0; a < this.pieData.length; a++) {// this.pieXY  这个数据中有名称和次数
                  if (this.pieData[a].name == params) {   //两个名称进行对比，取出对应的次数
                    return   this.pieData[a].value + '人次'  //然后return你需要的legend格式即可
                  }
                }
                return params;
              }
            }
          ],
          series: [
            {
              type: "pie",
              radius: "38%",
              center: ["50%", "40%"], // 饼图的中心坐标,一个横坐标，一个纵坐标
              data: this.pieData,
              //top: '-35px',
              label: {
                show: true, //饼图图形上的文本标签
                formatter: '{d}', // 字符串模板,如指标名系列名，百分比等,可在官网 配置项查找详细说明
              }
            }
          ]
        };
        myChart.setOption(option);
      },
      onClickLeft(){},
      onClickRight(){},

      //tab切换操作
      clickTab(number){
        this.isTabActive = number;
      }

    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .container-body
        display: flex
        flex-direction: column
        overflow-y: auto
        background-color #ffffff
        .title
            text-align left
            padding-left 12px
            background-color #f8f8f8
            height 76px
            margin-top 2px
            margin-bottom 6px
            .title-item
                padding-top 6px


        .contentData
            display flex
            flex-direction column
            height 200px
            overflow-y auto
            margin-top 10px
            .content-title
                font-size 10px
                width 100%
                height 20px
                line-height 20px
                font-weight bold
                margin-left 14px
                text-align left

            .tabs
                display flex
                justify-content flex-start
                align-items flex-start
                flex-wrap wrap
                .tab-item
                    height 30px
                    font-size 12px
                    font-weight bold
                    line-height 28px
                    margin 0 12px
                    padding 0 4px
                    margin-top 8px
                    border 1px solid #656262
                .tab-item:hover {
                    border: 1px solid #61acff;
                    z-index: 1;
                }
                .tab-item:nth-child(1)
                    width 100px
                .tab-item:nth-child(2)
                    width 160px
    .is-active
        color #61acff
</style>
