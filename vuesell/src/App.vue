<template>
  <div id="app">
    <v-headers :seller="seller"></v-headers>
    <div class="tab border-1px">
      <div class="tab-item">
        <router-link to='/home'>商品</router-link>
      </div>
      <div class="tab-item">
        <router-link to='/about'>评论</router-link>
      </div>
      <div class="tab-item">
        <router-link to='/seller'>商家</router-link>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import headers from './components/header/headers.vue'

const ERR_OK = 0

export default {
  data () {
    return {
      seller: {}
    }
  },
  components: {
    'v-headers': headers
  },
  created () {
    this.$http.get('/api/seller').then((response) => {
      response = response.data
      console.log(response)
      if (response.errno === ERR_OK) {
        this.seller = response.data
        console.log(this.seller)
      }
    })
  }
}
</script>

<style  lang="stylus" rel="stylesheet/stylus">
  @import "./common/stylus/mixin.styl"
  #app
    .tab
      display: flex
      width: 100%
      height: 40px
      line-height: 40px
      // border-bottom: 1px solid rgb(7, 17, 27, 0.1)
      border-1px(rgb(7, 17, 27, 0.1))
      .tab-item
        flex:1
        text-align: center
        & > a
          display: block
          font-size 14px
          color: rgb(77,85,93)
          &.active
            color: #c81414
</style>
