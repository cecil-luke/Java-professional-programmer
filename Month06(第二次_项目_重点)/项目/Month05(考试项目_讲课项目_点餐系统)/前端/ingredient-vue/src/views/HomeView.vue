<template>
  <div class="home">
    <!-- 导航条 -->
    <etoak-header />
    <fixed-header />

    <!-- 主体区域 -->
    <el-main class="main">
      <router-view />
    </el-main>
  </div>
</template>

<script>
import EtoakHeader from '@/components/EtoakHeader'
import FixedHeader from '@/components/FixedHeader'
export default {
  name: 'HomeView',
  components: {
    EtoakHeader,
    FixedHeader
  }
}
</script>

<style scoped>
.el-main {
  padding: 20px 0px;
}

.main{
  width: 85%;
  margin: 0 auto;
}
</style>
