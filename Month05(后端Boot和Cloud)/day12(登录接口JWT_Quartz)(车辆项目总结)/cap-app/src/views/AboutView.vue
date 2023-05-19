<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template>
<script>
import { getAreaList } from '@/api/area'
export default {
  data() {
    return {
      area: []
    }
  },

  mounted() {
    getAreaList().then(resp => {
      this.area = resp.data.data
    }).catch(e => {
      console.log(e)
    })
  }
}
</script>
