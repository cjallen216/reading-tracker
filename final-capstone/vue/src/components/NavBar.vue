<template>
  <div id="nav">
      <ul>
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{ name: 'my-books'}">My Books</router-link></li>
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{ name: 'new-book-form'}">Add Book</router-link></li>
        <li v-if="$store.state.token != ''"><router-link v-bind:to="{ name: 'logout' }" >Logout</router-link></li>
      </ul>
    </div>
</template>

<script>
export default {
    name: "nav-bar",
};

</script>

<style>

#nav {
  padding: 10px;
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