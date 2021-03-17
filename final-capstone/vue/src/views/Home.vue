<template>
  <div class="center">
    <h1>Welcome To The Dewey Decimal Reading Tracker</h1>
    <h3>Conan The Librarian Approved!</h3>
  <dashboard />
  </div>
</template>

<script>
import Dashboard from '../components/Dashboard.vue';

export default {
  components: { Dashboard },
  name: "home"
};
</script>
