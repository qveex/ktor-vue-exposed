<template>
  <button class="btn">
    <slot></slot>
  </button>
</template>


<script>
export default {
  name: 'my-button'
}
</script>


<style scoped>
button {
  border-radius: 15px;
  align-self: flex-start;
  z-index: 1;
  position: relative;
  font-size: inherit;
  font-family: inherit;
  color: #bdc0c4;
  padding: 0.5em 1em;
  outline: none;
  border: none;
  background-color: rgba(255,255,255,0.2);
  overflow: hidden;
  transition: color 0.4s ease-in-out;
}

button::before {
  content: '';
  z-index: -1;
  position: absolute;
  bottom: 100%;
  right: 100%;
  width: 1em;
  height: 1em;
  border-radius: 50%;
  background-color: #3cefff;
  transform-origin: center;
  transform: translate3d(50%, 50%, 0) scale3d(0, 0, 0);
  transition: transform 0.45s ease-in-out;
}

button:hover {
  cursor: pointer;
  color: #161616;
}

button:hover::before {
  transform: translate3d(50%, 50%, 0) scale3d(15, 15, 15);
}

</style>