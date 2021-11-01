<template>
  <div>
    <nav-bar></nav-bar>
    <div class="container mt-5">
      <collection-form @create="createCollection"/>
      <collection-list :collections="collections"
                       @remove="removeCollection"
      />
    </div>
  </div>
</template>


<script>
import CollectionForm from "@/components/CollectionForm";
import CollectionList from "@/components/CollectionList";
import VueBottomNavigation from "bottom-navigation-vue";
import quickMenu from 'vue-quick-menu';
import axios from 'axios';
import MyMenu from "@/components/MyMenu";
import store from "../store";

export default {
  components: {
    MyMenu,
    CollectionList,
    CollectionForm,
    quickMenu,
    VueBottomNavigation
  },
  data() {
    return {
      collections: [],
      baseURL:'http://localhost:8080/api/collections',
    }
  },
  methods: {

    async createCollection(collection) {
      if (collection.name === '' || collection.type === '' || collection.description === '')
        alert('Please, fill all fields')
      else {
        try {
          const reqCollection = {
            name: collection.name,
            type: collection.type,
            description: collection.description,
            cost: collection.cost,
            userId: localStorage.getItem('id')

          }
          console.log(reqCollection)
          await axios.post(this.baseURL, JSON.parse(JSON.stringify(reqCollection)), {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem('token')
            }
          })
          await this.fetchCollections()
        } catch (e) {
          alert(e.message)
        }
      }


    },
    async removeCollection(collection) {
      try {
        await axios.delete(this.baseURL + '/' + collection.id, {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
          }
        })
        await this.fetchCollections()
      } catch (e) {
        alert(e.message)
      }
    },


    async fetchCollections() {
      try {
        const response = await axios.get(this.baseURL)
        this.collections = response.data;
        console.log(this.collections)
      } catch (e) {
        alert(e.message)
      }
    }
  },
  created() {
    console.log(store.state.isAuth)
    this.fetchCollections()
    console.log(store.getters.getId)
    if (localStorage.getItem('token') !== '')
      store.commit('setAuth', true)
    else
      store.commit('setAuth', false)
  },
  mounted() {
    //this.fetchCollections()
  }
}
</script>


<style>
* {
  color: #bdc0c4;

  box-sizing: border-box;
  font-family: Calibri, serif;
}
h3 {
  padding: 15px;
}

</style>