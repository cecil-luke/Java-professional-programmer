<template>
  <div class="home">
    <!-- 导航条 -->
    <etoak-header />

    <!-- 页面主体部分 -->
    <el-main>
      <router-view />
    </el-main>
  </div>
</template>

<script>
import EtoakHeader from "@/components/EtoakHeader";
export default {
  name: 'HomeView',
  components: {
    EtoakHeader
  },

}
</script>
