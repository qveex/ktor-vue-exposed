<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-challenge">
    <div class="container-fluid ps-0">
      <a class="navbar-item pe-3" :href="vue" target="_blank" rel="noopener noreferrer">
        <i class="fab vue-icon fa-vuejs fa-2x" aria-hidden="true" />
      </a>
      <span class="navbar-brand" href="#">Code Challenge Vue</span>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/photos" class="nav-link active">Photos</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/about" class="nav-link active">About</router-link>
          </li>
        </ul>
        <form class="d-flex">
          <a :href="linkedin" target="_blank" rel="noopener noreferrer" class="link-icons px-2">
            <i class="fab fa-linkedin fa-2x" aria-hidden="true"></i>
          </a>
          <a :href="github" target="_blank" rel="noopener noreferrer" class="link-icons">
            <i class="fab fa-github fa-2x" aria-hidden="true"></i>
          </a>
        </form>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      linkedin: 'https://www.linkedin.com/in/gonzalo-bangueses/',
      vue: 'https://vuejs.org/',
      github: 'https://github.com/GBangueses/',
    };
  },
};
</script>