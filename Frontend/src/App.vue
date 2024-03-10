<template>
  <div>
    <NavBar />
    <div class="container">
      <RouterView />
    </div>
  </div>
</template>
<script setup>
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import NavBar from '@/views/NavBar.vue'
</script>