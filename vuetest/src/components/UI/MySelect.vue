<template>
  <select v-model="modelValue" @change.native="changeOption">
    <option disabled value="">Choose your destiny</option>
    <option
        v-for="option in options"
        :key="option.value"
        :value="option.value"
    >
      {{ option.name }}
    </option>
  </select>
</template>

<script>
export default {
  name: 'my-select',
  props: {
    modelValue: {
      type: String,
      default: () => "Coin"
    },
    options: {
      type: Array,
      default: () => []
    }
  },
  methods: {
    changeOption(event) {
      this.$emit('update:modelValue', event.target.value)
    }
  }
}
</script>


<style scoped>
select {
  width: 100%;
  font-family: Calibri, serif;
  font-size: 18px;
  margin-top: 10px;
  align-self: flex-start;
  padding: 10px;
  background: white;
  color: black;
  border: 1px solid dimgrey;
}
</style>