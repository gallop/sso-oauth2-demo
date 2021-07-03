<template>
    <div>
        <van-nav-bar class="nav-bar"
                     title="医改监测平台"
                     left-text=""
                     right-text=""

                     @click-left="onClickLeft"
        ></van-nav-bar>
        <div class="main">
            <!--<ul>
                <li>对比分析</li>
                <li>专题报告</li>
                <li>绩效考核</li>
            </ul>-->
            <div class="cartWrap">
                <!--<el-card shadow="always" class="cartContent">
                    <div class="cartItem">图片</div>
                    <div class="cartItem">指标数据分析</div>

                </el-card>-->
                <div class="cartContent">
                    <div class="cartItem">
                        <span class="svg-container">
                            <svg-icon style="width: 72px; height: 72px" icon-class="dataAnalysis"/>
                        </span>
                    </div>
                    <div class="cartItem">
                        <div class="itemTitle">指标数据分析</div>
                        <div class="itemContent">具体指标的趋势与各省份情况</div>
                    </div>
                </div>
                <div class="cartContent">
                    <div class="cartItem">
                        <span class="svg-container">
                            <svg-icon style="width: 72px; height: 72px" icon-class="analysis2"/>
                        </span>
                    </div>
                    <div class="cartItem">
                        <div class="itemTitle">对比分析</div>
                        <div class="itemContent">多项指标，多地域数据对比</div>
                    </div>
                </div>
                <div class="cartContent">
                    <div class="cartItem">
                        <span class="svg-container">
                            <svg-icon style="width: 72px; height: 72px" icon-class="analysis3"/>
                        </span>
                    </div>
                    <div class="cartItem">
                        <div class="itemTitle">专题报告</div>
                        <div class="itemContent">重点政策的专题分析</div>
                    </div>
                </div>
                <div class="cartContent">
                    <div class="cartItem">
                        <span class="svg-container">
                            <svg-icon style="width: 72px; height: 72px" icon-class="analysis4"/>
                        </span>
                    </div>
                    <div class="cartItem">
                        <div class="itemTitle">绩效考核</div>
                        <div class="itemContent">重点项目的执行成效</div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script type="text/ecmascript-6">
  import {NavBar} from 'vant';
  import {Toast} from 'vant';

  export default {
    name: "",
    components: {
      [NavBar.name]: NavBar,
      [Toast.name]: Toast
    },
    mounted() {
      var code = this.$route.query.code;
      console.log("-------code=="+code)
      if(code){
        this.$router.push("/")
      }


    },

    methods: {
      onClickLeft() {
        Toast('返回');
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .nav-bar
        width 100%
        background-color #f8f8f8

    .main
        background-color #ffffff

    .cartWrap
        margin-top 30px
        margin 0 auto
        width 82%

        .cartContent
            display flex
            height 120px
            align-items center
            box-shadow 0px 0px 8px #888888;
            margin-top 30px
            .cartItem
                flex 1
                height 80px
                .svg-container
                    padding: 6px 5px 6px 15px;
                    color: #7e8c8d;
                    vertical-align: middle;
                    display: inline-block;
            .cartItem:nth-child(2)
                flex 2
                display flex
                flex-direction column
                justify-content space-around
                .itemTitle
                    font-size 18px
                .itemContent
                    font-size 10px

</style>
