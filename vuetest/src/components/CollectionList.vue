<template>
  <div v-if="collections.length > 0" style="margin-bottom: 75px">
    <h3 align="middle">List of collections</h3>
    <transition-group name="collection-list">
      <collection-item
          v-for="collection in collections"
          :collection="collection"
          :key="collection.id"
          @remove="$emit('remove', collection)"
      />
    </transition-group>
  </div>
  <h3 v-else align="middle">
    List of collections empty
  </h3>
</template>

<script>
import CollectionItem from "@/components/CollectionItem";
export default {
  components: {CollectionItem},
  props: {
    collections: {
      type: Array,
      required: true
    }
  }

}
</script>

<style scoped>
.collection-list-item {
  display: inline-block;
  margin-right: 10px;
}
.collection-list-enter-active {
  opacity: 0;
  transform: translateY(150px);
}
.collection-list-leave-active {
  transition: all 0.4s ease
}
.collection-list-enter-from {
  opacity: 0;
  transform: translateX(75px);
}
.collection-list-leave-to {
  opacity: 0;
  transform: translateX(-75px);
}
.collection-list-move {
  transition: transform 0.5s ease;
}
</style>
