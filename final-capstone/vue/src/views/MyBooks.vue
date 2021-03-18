<template>
  <div class="center">
      <div class="bookshelf">
          <reading-list />
      </div>
      <p></p>
      <router-link class="finished-link" v-bind:to="{name: 'new-book-form'}">Add another book to your reading list</router-link>
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
        booksService.getMyBooks().then((response) => {
            if (response.status === 200) {
                this.$store.commit('SET_MY_BOOKS', response.data.books);
          } else {
            alert("Conan the Librarian was unable to pull your books at this time. Please try again later.")
          }
        });        
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