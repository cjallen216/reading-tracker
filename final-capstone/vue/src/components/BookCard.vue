<template>
  <div class="card center" :class="{ read: readStatus }">
    <h2 class="book-title">
      <router-link
        v-bind:to="{ name: 'book-details', params: { title: book.title } }"
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
    <div class="button-container">
      <button class="button"
        @click="setReadStatus()"
      >
        {{ readButtonText }}
      </button>
    </div>
    <div class="button-container">
      <button class="button" :class="{reading : readingStatus }"
        @click="setReadingStatus()"
      >
        {{ readingButtonText }}
      </button>
    </div>
    <div class="button-container">
      <button class="button"
        @click="removeBook()"
      >
        Remove Book
      </button>
      <modal v-show="isRemovedModalVisible" @close="closeRemovedModal()">
        <h2 slot="header"></h2>
        <h3 slot="body">
          {{ this.book.title }} 
          <br>was removed
          <br>from your book list.
        </h3>
    </modal>
    </div>
  </div>
</template>
<script>
import booksService from '@/services/BooksService.js';
import modal from "@/components/Modal.vue";

export default {
  name: "book-card",
  data(){
    return {
      isRemovedModalVisible: false
    }
  },
  components: {
    modal
  },
  computed: {
    readButtonText(){
      return this.readStatus == true ? "Unmark Read" : "Mark Read";
    }, 

    readStatus(){
      return this.book.read;
    },

    readingStatus(){
      return this.book.reading;
    },

    readingButtonText(){
      return this.readingStatus == true ? "Currently Reading" : "Mark Currently Reading";
    }
  },

  props: {
    book: Object    
  },
  methods: {
    setReadStatus() {
      this.book.read = !this.readStatus;
      booksService.updateBookStatus(this.book).then((response) => {
        if (response.status === 202) {
                this.$store.commit('UPDATE_BOOK_STATUS', response.data);
          } else {
            alert("Conan the Librarian was unable to change the status of\r\n" 
            + this.book.title 
            + ".\r\nPlease try again later.")
          }
      });
    },

    setReadingStatus(){
      this.book.reading = !this.readingStatus;
      booksService.updateBookStatus(this.book).then((response) => {
        if (response.status === 202) {
          this.$store.commit('UPDATE_BOOK_STATUS', response.data);
        } else {
          alert("Conan the Librarian was unable to mark\r\n" 
          + this.book.title 
          + "\r\nas your current book. Please try again later.");
        }
      });
    },

   removeBook() {
      booksService.remove(this.book).then((response) => {
        if (response.status === 200) {
          this.$store.commit('REMOVE_BOOK', this.book.bookId);
        } else {
          alert("Conan the Librarian was unable to remove\r\n" 
          + this.book.title + 
          ".\r\nPlease try again later.")
        }
      });
    },
  }, 

  showRemovedModal(){
    this.isRemovedModalVisible = true;
  },

  closeRemovedModal(){
    this.isRemovedModalVisible = true;
  }
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
.button.reading{
    color: whitesmoke;
    background: #117864;
    box-shadow: 2px 6px #51395c;
}

img {
  height: 200px;
  width: 150px;
}
</style>