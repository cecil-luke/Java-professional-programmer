<template>
  <el-menu
    :default-active="$route.path"
    router
    class="menu-header"
    :class="{'fixed-header': isFixed}"
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">

    <!-- logo -->
    <img src="../assets/logo.png" style="float: left; padding: 15px">

    <!-- 导航条 -->
    <el-menu-item index="/welcome" style="margin-left: 50px">首页</el-menu-item>
    <el-menu-item index="/ingredient">食材管理</el-menu-item>
    <el-menu-item index="/prepare">预制菜品管理</el-menu-item>
    <el-menu-item index="/order">订单管理</el-menu-item>

    <!-- 退出按钮 -->
    <!-- <i class="el-icon-switch-button logout"></i>-->
  </el-menu>
</template>

<script>
export default {
  name: "EtoakHeader2",
  data () {
    return {
      isFixed: false
    }
  },

  mounted () {
    window.addEventListener('scroll', this.initHeight,true)
  },

  methods: {
    initHeight () {
      // 各种浏览器的滑动偏移参数获取
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      this.isFixed = scrollTop > 60
    }
  },

  destroyed () {
    // 销毁，其他页面也会加载，浪费资源 true是捕获，false是冒泡
    window.removeEventListener('scroll', this.initHeight, false)
  }
}
</script>

<style scoped lang="scss">
  .menu-header {
    width: 100%;
    height: 60px;
    position: fixed;
    left: 0px;
    top: 0px; /* 脱离文档流, 不占页面位置*/
    z-index: 999;
    transform: translateY(-100%);
    opacity: 0; /* 不显示 */

    &.fixed-header {
      transition: all 0.1s linear;
      transform: none;
      opacity: 1; /* 显示 */
    }
  }
</style>