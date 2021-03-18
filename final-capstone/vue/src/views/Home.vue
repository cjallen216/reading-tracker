<template>
  <div class="center">
    <h1 class="dashboard-link">Welcome To The Dewey Decimal Reading Tracker</h1>
    <h2 class="dashboard-link">Conan The Librarian Approved!</h2>
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
<style>
h2 {
  margin: top 10px bottom 20px;
}
</style>
