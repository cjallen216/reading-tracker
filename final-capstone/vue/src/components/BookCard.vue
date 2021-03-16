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
    <img
      v-if="!book.isbn"
      v-bind:src="
        'https://mrb.imgix.net/assets/default-book.png?auto=format&ixlib=react-9.0.3&w=150'
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
  data(){
    return {
      readStatus: false
    }
  },
  props: {
    book: Object,
    enableAdd: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    setReadStatus() {
      this.readStatus = !this.book.read;
      booksService.updateBookStatus(this.book, 'read', !this.book.read).then((response) => {
        if (response.status === 200) {
                this.$store.commit('UPDATE_BOOK_STATUS', response.data);
          } else {
            alert("Conan the Librarian was unable to change your read status at this time. Please try again later.")
          }
      });
    },

    setCurrentlyReading(){
      booksService.updateBookStatus(this.book, 'reading', !this.book.reading).then((response) => {
        if (response.status === 200) {
          this.$store.commit('UPDATE_BOOK_STATUS', response.data);
        } else {
          alert("Conan the Librarian was unable to mark " + this.book.title + " as your current book. Please try again later.");
        }
      });
    },
    
    addToReadingList(book) {
      let addedBook = Object.assign({ read: false }, book);
      this.$store.commit("SAVE_BOOK", addedBook);
    }
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

img {
  height: 200px;
  width: 150px;
}
</style>