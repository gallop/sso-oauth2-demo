<template>
    <div>
        <van-collapse v-model="activeName" >
            <!--name="index" 这里同data里定义的数组里保持一致-->
            <van-collapse-item v-for="(item,index) in menuData" :key="index" :name="index" :title="item.indexName">

            </van-collapse-item>
            <van-collapse-item name="index" title="医改主要指标" icon="shop-o">
                <van-collapse-item title="组织领导">

                </van-collapse-item>
                <van-collapse-item title="卫生投入">
                    <div v-for="(item, index) in items" :key="index" :value="item.title">
                        <template>
                            <div class="resultChild" @click="eachResult">
                                {{item.title}}
                                <span class="result_child">
                                <van-icon name="arrow" />
                            </span>
                            </div>
                            <van-divider />
                        </template>
                    </div>
                </van-collapse-item>

            </van-collapse-item>

        </van-collapse>
    </div>
</template>

<script type="text/ecmascript-6">
  import {List} from 'vant';
  import { Collapse, CollapseItem, Divider, Icon } from 'vant';

  export default {
    name: "",
    components: {
      [List.name]: List,
      [Collapse.name]: Collapse,
      [CollapseItem.name]: CollapseItem,
      [Divider.name]: Divider,
      [Icon.name]: Icon
    },
    data() {
      return {
        activeName: ['index'], // 保持一致
        // 本地模拟数据
        menuData:[
          {
            indexId:"1",
            indexName:"医改主要指标",
            children:[
              {
                indexId:"10",
                indexName:"组织领导"
              },
              {
                indexId:"12",
                indexName:"卫生投入",
                children:[
                  {
                    indexId:"121",
                    indexName:"财政卫生健康支出年增长率",
                  },
                  {
                    indexId:"122",
                    indexName:"医疗卫生支出占财政支出的比重",
                  },
                  {
                    indexId:"123",
                    indexName:"个人卫生支出占卫生总费用的比重",
                  }
                ]
              }
            ]
          },
          {
            indexId:"2",
            indexName:"有序推进分级诊疗制度建设",
          }
        ],
        items: [
          { title: "身高体重" },
          { title: "血压" },
          { title: "内科" },
          { title: "外科" },
          { title: "眼科常规检查" }
        ]
      }
    },
    methods: {
      eachResult(){

      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">

</style>
