<template>
    <div>
    </div>
</template>
<script type="text/ecmascript-6">
    import {toAuthorize} from "@api/ssoAuth";

    export default {
    name: "ssoLogin",
    created() {
      console.log("into the ssoLogin page!")
      //this.handleAuth()
      //授权中心地址
      /*let uaaUri = 'http://localhost:8082/sso/oauth2/authorize'
      let clientId = 'Z000000000000000000000000TSYS010'
      let state = '123'
      let url = uaaUri+'?client_id='+clientId+'&redirect_uri=http://localhost:8088/callback&response_type=code&state='+state;

      window.location = url*/
      window.location = 'http://localhost:8086/user/sso/auth'
    },
    methods: {
      async handleAuth(){
        await toAuthorize()
      }
    }
  }
</script>
