<template>
  <div class="card center" v-bind:class="{ read: book.read }">
    <h2 class="book-title">
      <router-link
        v-bind:to="{ name: 'book-details', params: { isbn: book.isbn } }"
        >{{ book.title }}</router-link
      >
    </h2>
    <h3 class="book-author">By: {{ book.author }}</h3>
    <img
      v-if="book.isbn"
      v-bind:src="
        'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'
      "
    />
    <div class="button-container" v-if="!enableAdd">
      <button
        class="mark-read button"
        v-on:click.prevent="setReadStatus(true)"
        v-if="!book.read"
      >
        Mark Read
      </button>
      <button
        class="mark-unread button"
        v-on:click.prevent="setRead(false)"
        v-if="book.read"
      >
        UnMark Read
      </button>
    </div>
    <button class="button" v-if="enableAdd" v-on:click.prevent="addToReadingList(book)">
      Add to Reading List
    </button>
  </div>
</template>
<script>
import booksService from '@/services/BooksService.js';

export default {
  name: "book-card",
  props: {
    book: Object,
    enableAdd: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    setReadStatus(value) {
      booksService.updateBookStatus(this.book, 'read', value );
    },
    
    addToReadingList(book) {
      let addedBook = Object.assign({ read: false }, book);
      this.$store.commit("SAVE_BOOK", addedBook);
    },
  },
};
</script>
<style>
.card {
  border: 1px solid black;
  width: 250px;
  height: 450px;
  margin: 20px;
  background-color: whitesmoke;
  box-shadow: 8px 10px #51395c;
  border-radius: 5px;
}

.card.read {
  background-color: #6f96b6;
}
</style>