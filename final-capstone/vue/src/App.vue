<template>
  <div id="app">
    <!-- <nav></nav> -->
    <div id="nav">
      <ul>
        <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
        <li><router-link v-bind:to="{ name: 'register' }">Register</router-link></li>
        <li><router-link v-bind:to="{ name: 'login' }">Login</router-link></li>
        <li><router-link v-bind:to="{ name: 'new-book-form'}">Add Book</router-link></li>
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{ name: 'logout' }" >Logout</router-link></li>
      </ul>
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }">Register</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'new-book-form'}">Add Book</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> -->
    </div>
    <h1>Welcome To The Dewey Decimal Reading Tracker!</h1>
    <p>Conan The Librarian Approved</p>
    <router-view />
  </div>
</template>

<script>
// import nav from "@/components/nav.vue";

export default {
  // components: {
  //   nav
  // }
  
}
</script>

<style>

body {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    background: linear-gradient(to right, #49D49D 10%, #A2C7E5 90%);
    height: 100%;
}

#nav {
  padding: 6px;
  /* background-color: whitesmoke; */
}

#nav a {
  font-weight: bold;
  color:#ffffff;
}

#nav a.router-link-exact-active {
  text-decoration: none;  
  color: whitesmoke;
  text-transform: uppercase;
  /* background-color: whitesmoke; */
}

#nav ul {
    padding: 5px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}

#nav ul li {
    list-style: none;
    width: 10%;
    background-color: #6a49ff;
    border-radius: 5px;
    text-align: center;
    line-height: 50px;
}

</style>