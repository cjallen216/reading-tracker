<template>
  <div class="reading-list">
      <div class="bookshelf">
          <reading-list />
      </div>
      <p></p>
      <router-link v-bind:to="{name: 'new-book-form'}">Add Book to Reading List</router-link>
  </div>
</template>

<script>
import ReadingList from '../components/ReadingList.vue'

export default {
    components: {
        ReadingList
    },

}
</script>

<style>
.reading-list {
    text-align: center;
}
.bookshelf {
    background: linear-gradient(#e5e6e3 90%, #cabaa1 10%);
    background-size: 500px 500px;
    background-repeat: repeat;
    /* border-style: none;
    border-bottom-style: solid;
    border-color: rgb(0, 0, 0); */
}

</style>