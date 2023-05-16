<template>
  <el-menu
    :default-active="$route.path"
    router
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">

    <!-- logo -->
    <img src="../assets/logo.png" class="logo">

    <!-- 导航 -->
    <el-menu-item index="/welcome" style="margin-left: 40px">首 页</el-menu-item>
    <el-menu-item index="/car/manage">车辆管理</el-menu-item>
    <el-menu-item index="/car/show">车辆展示</el-menu-item>

    <!-- 退出按钮 -->
    <i class="el-icon-switch-button logout"></i>
  </el-menu>
</template>

<script>
export default {
  name: "EtoakHeader"
}
</script>

<style scoped>
.logo {
  height: 32px;
  width: 32px;
  float: left;
  padding: 15px;
}

.logout {
  font-size: 32px;
  float: right;
  color: #FFF;
  padding: 15px;
  cursor: pointer;
}
</style>