<template>
  <div>
    <nav-bar></nav-bar>
    <div class="container" style="">
      <div class="row justify-content-center align-self-center mt-5">
        <div class="col-5">
          <h1 align="middle">Hello there</h1>
          <form @submit.prevent="handleSubmit">
            <my-input
                v-model="login"
                type="text"
                placeholder="login"
            />

            <my-input
                v-model="password"
                type="password"
                placeholder="password"
            />
            <my-button style="padding: 10px; margin-top: 10px; width: 100px;">
              login
            </my-button>
            <my-button @click="$router.push('/registration')" style="padding: 10px; margin-top: 10px; margin-left: 325px; width: 100px;">
              register
            </my-button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios';
import sjcl from 'sjcl'
import store from "@/store";
import router from "@/router"
export default {
  data() {
    return {
      login: "",
      password: ""
    }
  },
  methods: {
      async handleSubmit() {
        if (this.login === '' || this.password === '')
          alert('Please, fill all fields')
        else {
          try {
            const hash = sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(this.password))
            const login = this.login
            const response = await axios.post('https://localhost:8443/login', JSON.parse(JSON.stringify({login, hash})))
            if (response.data.token != null) {

              store.commit('setAuth', true)
              store.commit('setLogin', login)
              store.commit('setId', response.data.id)

              localStorage.setItem('id', response.data.id)
              localStorage.setItem('login', this.login)
              localStorage.setItem('token', response.data.token)

              await router.push('/')
            } else {
              this.login = ''
              this.password = ''
            }
          } catch (e) {
            alert('Wrong login or password')
          }
        }
      }
  }
}
</script>


<style scoped>

</style>