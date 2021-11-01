<template>
  <div class="container">
    <div class="navbar">
      <div class="heart" @click="$router.push('/')"><i class="bi-heart-fill" style="font-size: 35px"></i></div>
      <div class="navbar__btns">
        <my-button @click="$router.push('/')">Collections</my-button>
        <my-button v-if="$store.state.isAuth !== false" style="margin-left: 20px" @click="myCollections">my collections</my-button>
        <my-button style="margin-left: 20px" @click="$router.push('/about')">About</my-button>
        <my-button v-if="$store.state.isAuth !== false" style="margin-left: 20px" @click="logout">log out</my-button>
        <my-button v-else style="margin-left: 20px" @click="$router.push('/login')">log in</my-button>
      </div>
    </div>
  </div>
</template>

<script>
import store from "../store";
import router from "../router";

export default {
  name: 'nav-bar',

  methods: {
    logout() {
      localStorage.setItem('token', '')
      localStorage.setItem('id', 0)
      localStorage.setItem('login', '')
      store.commit('setAuth', false)
      router.push('/login')
    },

    myCollections() {
      router.push(localStorage.getItem('login'))
    }
  }
}
</script>

<style scoped>
.navbar {
  height: 50px;
  background-color: rgb(35, 36, 37);
  display: flex;
  align-items: center;
  padding: 15px 15px;
}
.navbar__btns {
  align-self: center;
}
my-button {
  background: #9a9a9a;
}
.bi-heart-fill:hover {
  color: #f4511e;
}

</style>