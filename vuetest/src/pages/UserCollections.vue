<template>
  <div>
    <nav-bar></nav-bar>
    <div class="container mt-5">
      <h1 align="middle">{{$route.params.login}}'s collections</h1>
      <collection-list :collections="collections"
                       @remove="removeCollection"
                       />
    </div>
  </div>
</template>


<script>
import axios from "axios";
import CollectionList from "../components/CollectionList";
import store from "../store"


export default {
  components: {
    CollectionList
  },
  data() {
    return {
      collections: [],
      baseURL:'http://localhost:8080/api/users/'
    }
  },
methods: {
  async fetchCollections() {
    try {
      const response = await axios.get(this.baseURL + localStorage.getItem('login'),{
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
      })
      console.log(response)
      this.collections = response.data;
    } catch (e) {
      alert(e.message)
    }
  },
  async removeCollection(collection) {
    try {
      await axios.delete("http://localhost:8080/api/collections" + '/' + collection.id, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
      })
      await this.fetchCollections()
    } catch (e) {
      alert(e.message)
    }
  },
},
  created() {
    this.fetchCollections()
  }
}
</script>


<style scoped>

</style>