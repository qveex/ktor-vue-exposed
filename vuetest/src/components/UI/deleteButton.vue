<template>
  <button class="btn btn-outline-danger btn-circle btn-lg  mt-2">
    <i class="bi bi-trash"></i>
  <slot></slot>
</button>
</template>


<script>
export default {
  name: 'delete-button'
}

</script>

<style scoped>
.mbtn {
  background-color: #f4511e;
  border: none;
  color: white;
  padding: 6px 12px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
  opacity: 0.6;
  transition: 0.3s;
  display: inline-block;
  text-decoration: none;
  cursor: pointer;
}

.btn:hover {opacity: 2}
</style>