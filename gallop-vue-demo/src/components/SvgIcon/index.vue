<template>
    <svg class="svg-icon" aria-hidden="true">
        <use :xlink:href="iconName"/>
    </svg>
</template>

<script type="text/ecmascript-6">
  export default {
    name: "SvgIcon",
    props: {
      iconClass: {
        type: String,
        required: true
      }
    },
    computed: {
      iconName() {
        return `#icon-${this.iconClass}`
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .svg-icon {
        width: 2em;
        height: 2em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
</style>
