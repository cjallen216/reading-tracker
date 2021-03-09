<template>
  <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }">Register</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'new-book-form'}">Add Book</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
</template>

<script>
export default {
    name: "nav",
};

</script>

<style>

#nav {
  padding: 10px;
  background-color: whitesmoke;
}

#nav a {
  font-weight: bold;
  color:#6a49ff;
}

#nav a.router-link-exact-active {
  text-decoration: none;  
  color: black;
}
</style>