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
    background: linear-gradient(to bottom right, #117864 10%, #A3E4D7 90%);
    height: 100%;
    display: flex;
    flex-direction: column;
  }

</style>