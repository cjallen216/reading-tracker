<template>
  <div id="app">
    <nav-bar />
    <h1>Welcome To The Dewey Decimal Reading Tracker</h1>
    <h3>Conan The Librarian Approved!</h3>
    <router-view />
<p>
  <button
      type="button"
      class="btn"
      @click="showModal"
    >
      Open Modal
    </button>

    <modal
      v-show="isModalVisible"
      @close="closeModal"
    />
</p>

  </div>
</template>
<script>
import NavBar from "@/components/NavBar.vue";
import modal from '@/components/Modal.vue';

  export default {
    name: 'app',
    components: {
      NavBar,
      modal,
    },
    data () {
      return {
        isModalVisible: false,
      };
    },
    methods: {
      showModal() {
        this.isModalVisible = true;
      },
      closeModal() {
        this.isModalVisible = false;
      }
    },
  };
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
  }

  #nav a {
    font-weight: bold;
    color:#ffffff;
  }

  #nav a.router-link-exact-active {
    text-decoration: none;  
    color: whitesmoke;
    text-transform: uppercase;
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