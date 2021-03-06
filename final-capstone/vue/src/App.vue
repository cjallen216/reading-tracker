<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <h1>Welcome To The Dewey Decimal Reading Tracker!</h1>
    <p>Conan The Librarian Approved</p>
    <router-view />
  </div>
</template>
