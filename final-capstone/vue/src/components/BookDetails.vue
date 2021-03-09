<template>
  <div class="details" v-bind:class="{isbn : book.isbn}">
      <h2 class="book-title">{{book.title}}</h2>
      <h3 class="book-author">{{book.author}}</h3>
      <img v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
      <p></p>
      <router-link v-bind:to="{name: 'my-books'}">Return to Reading List</router-link>
  </div>
</template>

<script>
export default {
    name: 'book-details',
    data() {
        return {
            title: '',
            author: '',
            isbn: ''
        }
    },
    created () {
        const isbn = this.$route.params.isbn;
        this.book = this.$store.state.books.find( book => book.isbn == isbn);
    },

}
</script>

<style>
.details {
    text-align: center;
}

</style>