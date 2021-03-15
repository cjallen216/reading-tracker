<template>
  <div class="center">
      <div class="bookshelf">
          <reading-list />
      </div>
      <p></p>
      <router-link v-bind:to="{name: 'new-book-form'}">Add Book to Reading List</router-link>
  </div>
</template>

<script>
import ReadingList from '../components/ReadingList.vue';
import booksService from '@/services/BooksService.js';

export default {
    components: {
        ReadingList
    },
    mounted() {
        booksService.getMyBooks();
    }
}
</script>

<style>
.bookshelf {
    background: linear-gradient(#e5e6e3 90%, #cabaa1 10%);
    background-size: 500px 500px;
    background-repeat: repeat;
}

</style>