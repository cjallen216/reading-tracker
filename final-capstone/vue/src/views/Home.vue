<template>
  <div class="center">
    <h1>Welcome To The Dewey Decimal Reading Tracker</h1>
    <h3>Conan The Librarian Approved!</h3>
    <h1>Dashboard</h1>
    <p><iframe width="560" height="315" src="https://www.youtube.com/embed/XHbdoO7uCkk" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></p>   
  
  <p></p>
  <timer />
  </div>
</template>

<script>
import Timer from '../components/Timer.vue';
export default {
  components: { Timer },
  name: "home"
};
</script>
