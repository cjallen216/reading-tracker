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
        class="mark-read"
        v-on:click.prevent="setRead(true)"
        v-if="!book.read"
      >
        Mark Read
      </button>
      <button
        class="mark-unread"
        v-on:click.prevent="setRead(false)"
        v-if="book.read"
      >
        UnMark Read
      </button>
    </div>
    <button v-if="enableAdd" v-on:click.prevent="addToReadingList(book)">
      Add to Reading List
    </button>
  </div>
</template>
<script>

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
    setRead(value) {
      this.$store.commit("SET_READ_STATUS", { book: this.book, value: value });
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
  box-shadow: 5px 5px #505050;
}

.card.read {
  background-color: #6f96b6;
}

.card .book-title {
  font-size: 1rem;
}

.card .book-author {
  font-size: 0.75rem;
}
</style>