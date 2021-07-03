<template>
    <div>
        <van-tabbar v-model="active" active-color="#ee0a24" inactive-color="#000" class="tabbar">
            <van-tabbar-item @click="topage(val.content)" badge v-for="(val,inx) in tabbar" :key="inx">
                <span>{{val.content}}</span>
                <template #icon="inx">
                    <img :src="inx.active ? val.active : val.inactive"/>
                </template>
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>


<script>
  import {Tabbar, TabbarItem} from 'vant';

  export default {
    props: {
      activeNavIndex:{
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        active: 0,
        navTabs: ['home', 'Category', 'cart', 'me'], // 底部导航
        tabbar: [
          {
            name: "worktable",
            active: require("@/assets/image/worktable.png"),
            inactive: require("@/assets/image/worktable.png"),
            content: "工作台"
          },
          {
            name: "analysis",
            active: require("@/assets/image/analysis.png"),
            inactive: require("@/assets/image/analysis.png"),
            content: "分析"
          },
          {
            name: "subject",
            active: require("@/assets/image/subject.png"),
            inactive: require("@/assets/image/subject.png"),
            content: "专题"
          }
        ]
      };
    },

    /*watch: {
      $route: 'changeActive'
    },*/
    watch:{
      activeNavIndex: function(val,oldval){
        console.log("avl:"+val)
        console.log("oldval:"+oldval)
        this.active = val
      },
      $route (to, from) {
        const {navTabs} = this.$data;
        const toName = to.name;
        const fromName = from.name;
        //如果是在 navTab 页面内刷新浏览器或初始进入系统，则显示导航栏
        if(navTabs.includes(toName) && !fromName) this.isShowNav = true;
        console.log("toName:",toName)
        console.log("fromName:",fromName)
        switch (toName) {
          case 'worktable': this.active = 0; break;
          case 'analysis': this.active = 1; break;
          case 'subject': this.active = 2; break;
          default: this.activeNavIndex = 0;
        }
      }
    },

    created() {
      const toName = this.$route.name;
      console.log("toName:"+toName)

    },

    methods: {
      topage(content) {
        switch (content) {
          case "工作台":
            if(this.$route.name != 'worktable'){
              this.$router.push("/");
            }
            break;
          case "分析":
            if(this.$route.name != 'analysis'){
              this.$router.push("/analysis");
            }else {
              //this.$router.go(0)
            }
            break;
          case "专题":
            if(this.$route.name != 'subject'){
              this.$router.push("/subject");
            }
            break;
          default:
            console.log("无跳转路由地址匹配");
        }
      }
    },

    components: {
      [Tabbar.name]: Tabbar,
      [TabbarItem.name]: TabbarItem
    }
  };
</script>
<style lang="stylus">
    .tabbar {
        width: 100%;
        height: 50px;
        bottom: 0;
        left: 50%;
        transform: translateX(-50%);
    }
</style>
