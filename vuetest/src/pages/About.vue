<template>
  <nav-bar></nav-bar>
  <div class="container">
    <div class="row justify-content-center align-self-center mt-5">
      <div class="col-5 mt-5">
        <h1>  made by @qveex</h1>
      </div>
    </div>
    <div class="row justify-content-center align-self-center mt-5">
        <br>
      <div class="col-2">
        <div @click="gitHub"><i class="bi-github"></i></div>
      </div>
        <div class="col-3">
        <div @click="telegram"><i class="bi-telegram"></i></div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  methods: {
    gitHub() {
      window.location.href = 'https://github.com/qveex';
    },
    telegram() {
      window.location.href = 'https://t.me/qveex';
    }
  }
}
</script>


<style scoped>
.bi-github:hover, .bi-telegram:hover {
  color: #22cc8b;
}
.bi-github, .bi-telegram {
  font-size: 75px;
}
.row {

}
</style>