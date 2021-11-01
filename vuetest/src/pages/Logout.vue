<template>
  <div>

  </div>
</template>


<script>
import store from '@/store'
import router from "@/router";

export default {
  methods: {

  },
  mounted() {
  },
  created() {
    localStorage.setItem('token', '')
    store.commit('setAuth', false)
    router.push('/login')
  }
}
</script>


<style scoped>

</style>