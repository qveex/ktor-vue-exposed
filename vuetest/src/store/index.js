import { createStore } from 'vuex'
import {collectionModule} from "@/store/collectionModule";

export default createStore({
  state: {
    baseURL:'https://localhost:8443',
    apiURL:'https://localhost:8443/api/collections',
    isAuth: false,
    login: '',
    token: '',
      id: 0
  },
  mutations: {
      setToken(state, token) {
          state.token = token;
      },
      setAuth(state, isAuth) {
          state.isAuth = isAuth;
      },
      setLogin(state, login) {
          state.login = login;
      },
      setId(state, id) {
          state.id = id;
      }
  },
  actions: {

  },
  modules: {
      collection: collectionModule
  },
    getters: {
      getLogin: state => {
          return state.login
      },
      getId: state => {
          return state.id
      }
    }
})
