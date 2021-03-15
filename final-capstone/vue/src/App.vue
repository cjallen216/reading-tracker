<template>
  <div id="app">
    <nav-bar />
    <router-view />

  </div>
</template>
<script>
import navBar from "@/components/NavBar.vue";

  export default {
    name: 'app',
    components: {
      navBar,
    },
  };
</script>
<style>
  body {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    background: linear-gradient(to right, #03A678 20%, #6f96b6 80%);
    height: 100%;
    display: flex;
    flex-direction: column;
  }

</style>