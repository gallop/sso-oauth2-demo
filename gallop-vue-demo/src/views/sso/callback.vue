<template>
    <div>
    </div>
</template>
<script type="text/ecmascript-6">
    import {getQueryVariable} from "@utils/util";
    export default {
    name: "callback",
    created() {
      let code = getQueryVariable('code');
      console.log("======code:"+code)
      this.$router.push({ path: '/' })
    }
  }
</script>
