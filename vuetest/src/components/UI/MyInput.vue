<template>
    <input :value="modelValue" @input.native="updateInput" class="input">
</template>


<script>
export default {
  name: 'my-input',
  props: {
    modelValue: [String, Number]
  },
  methods: {
    updateInput(event) {
      this.$emit('update:modelValue', event.target.value)
    }
  }
}
</script>


<style scoped>
.input {
  border-radius: 15px;
  background: rgba(255,255,255,0.2);
  width: 100%;
  border: 1px solid dimgrey;
  padding: 10px;
  margin-top: 5px;
  font-size: 18px;

}

input::placeholder {
  color: #9a9a9a;
}




div {
  position: relative;
}

input {
  border-radius: 15px;
  color: white;
  padding: 10px;
  margin-top: 5px;
  font-size: 18px;
  font-family: inherit;
  background-color: rgba(255,255,255,0.2);
  border: 1px solid transparent;
  transition: background-color 0.3s ease-in-out;
}

</style>