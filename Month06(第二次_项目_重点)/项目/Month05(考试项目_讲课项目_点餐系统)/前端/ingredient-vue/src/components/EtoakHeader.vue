<template>
  <el-menu
    :default-active="$route.path"
    router
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
    <i class="el-icon-switch-button logout" @click="logout"></i>
  </el-menu>
</template>

<script>

  import {logout} from '@/api/user'
export default {
  name: "EtoakHeader",

  methods: {
    logout() {
      logout().then(resp => {
        const {code, message} = resp
        if (code === 200) {
          sessionStorage.clear()
          this.$router.push('/login')
        }
      }).catch(e => {
        console.log(e)
      })
    }
  }
}
</script>

<style scoped>
.logout {
  float: right;
  color: #FFF;
  font-size: 32px;
  padding: 15px;
  cursor: pointer;
}
</style>