<template>
  <form @submit.prevent>
    <h3 align="middle">Create new collection!</h3>
    <my-input class="input"
        v-model="collection.name"
        type="text"
        placeholder="Name"
    />

    <select
    v-model="collection.type">
      <option disabled value="">Choose your destiny</option>
      <option>The Postage Stamp</option>
      <option>Coin</option>
      <option>Mineral</option>
    </select>
    <my-input
        v-model="collection.description"
        type="text"
        placeholder="Description"
    />
    <my-input class="input"
        v-model="collection.cost"
        min="0"
        type="number"
        placeholder="Cost"
    />
    <my-button v-if="$store.state.isAuth === true" style="padding: 10px; margin-top: 10px;"
        @click.native="createCollection">
      Create
    </my-button>
  </form>
</template>

<script>
import store from '@/store'

export default {
  data() {
    return {
      collection: {
        name: '',
        type: '',
        description: '',
        cost: '',
      },
      selectedType: "The Postage Stamp",
      typeOptions: [
        {value: "The Postage Stamp", name: 'The Postage Stamp'},
        {value: "Coin", name: 'Coin'}
      ]
    }
  },
  methods: {
    createCollection() {
      this.$emit('create', this.collection)
      this.collection = {
        name: '',
        type: '',
        description: '',
        cost: ''
      }
    }
  },
  watch: {
    selectedType(newValue) {
      this.collection.type = newValue.type
      console.log(newValue)
    }
  }
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}

select {
  border-radius: 15px;
  width: 100%;
  font-family: Calibri, serif;
  font-size: 18px;
  margin-top: 5px;
  align-self: flex-start;
  padding: 10px;
  background: rgba(255, 255, 255, 0.2);
  color: #bdc0c4;
  border: 1px solid dimgrey;
}
select:focus {
  background: #4f5051;
}

</style>