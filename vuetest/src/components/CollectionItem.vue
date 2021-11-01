<template>
  <div class="collection">
    <div>
      <div class="title">{{ collection.cost }}$</div>
      <div>
        <strong> {{ collection.name }}</strong>
      </div>
      <div>{{ collection.type }}</div>
      <div>{{ collection.description }}</div>
    </div>
    <div class="collection_btns">

      <delete-button
          v-if="$store.state.isAuth === true"
          @click.native="$emit('remove', collection)"
      >
      </delete-button>
    </div>
  </div>
</template>


<script>
import DeleteButton from "@/components/UI/deleteButton";
import store from "../store"

export default {
  components: {DeleteButton},
  props: {
    collection: {
      type: Object,
      required: true
    },
  }
}
</script>


<style scoped>
.title {
  float: right;
}
.collection {
  border-radius: 16px;
  padding: 15px;
  border: 2px solid dimgrey;
  margin-top: 10px;
  font-size: 22px;
  background: rgba(255, 255, 255, 0.2);

  align-items: center;
  justify-content: space-between;
}
</style>