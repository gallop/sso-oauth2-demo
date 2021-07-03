<template>
  <div class="list" :style="styleObject">
    <div class="listItem" v-for="(item, index) in list" :key="index">
      {{ item.name }}
    </div>
  </div>
</template>

<script>
  import {getWindowHeight} from '@/utils/style'
export default {
  data () {
    return {
      list: [
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' },
        { name: 'vue-cli4脚手架' },
        { name: 'vant按需引入' }
      ],
      clientHeight:'',
      styleObject:{
        height:this.clientHeight
      }
    }
  },
  mounted(){
    // 获取浏览器可视区域高度
    this.clientHeight = getWindowHeight();          //document.body.clientWidth;
    console.log("height:"+this.clientHeight);
    this.styleObject.height = this.clientHeight;
  }
}
</script>

<style scoped lang="stylus">
.list
  display: flex
  flex-direction: column
  width: 100%
  overflow-y auto
  margin-bottom: 50px
  .listItem
    width: 100%
    height: 45px
    line-height: 45px
    border-bottom: 1px solid #823c3c;
</style>
