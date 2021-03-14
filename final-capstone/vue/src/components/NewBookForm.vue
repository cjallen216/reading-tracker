<template>
  <div id="new-book">
    <form class="new-book-form">
      <div>
        <h1>Add A Book</h1>
      </div>

      <div class="form-body">
        <div class="input-line">
          <input
            class="title-input"
            type="text"
            placeholder="Title"
            v-model="book.title"
          />
        </div>
        <div class="input-line">
          <input
            class="author-input"
            type="text"
            placeholder="Author"
            v-model="book.author"
          />
        </div>
        <div class="input-line">
          <input
            class="isbn-input"
            type="text"
            placeholder="ISBN"
            v-model="book.isbn"
          />
        </div>
        <div class="input-line">
          <input
            class="imgLink-input"
            type="text"
            placeholder="Cover Image Link"
            v-model="book.imgLink"
          />
        </div>

        <div class="input-center">
          <button 
            v-on:click.prevent="
            saveBook();
            "
          >
            Add Book
          </button>
          <modal v-show="isCreateBookModalVisible" @close="closeCreateBookModal">
            <h3 slot="body">
              Title: {{ this.book.title }}<br />
              Author: {{ this.book.author }}<br />
              ISBN #{{ this.book.isbn }}<br />
            </h3>
          </modal>
          <modal v-show="isDuplicateBookModalVisible" @close="closeDuplicateBookModal">
            <h3 slot="body">
              You already have {{ this.book.title }} on your list!<br />
            </h3>
          </modal>
        </div>
      </div>
    </form>
    <p></p>
    <router-link v-bind:to="{ name: 'my-books' }"
      >I'm done adding books</router-link
    >
  </div>
</template>
<script>
import booksService from "../services/BooksService.js"
import modal from "@/components/Modal.vue";

export default {
  name: "new-book-form",
  components: {
    modal
  },
  data() {
    return {
      book: {
        title: "",
        author: "",
        read: false,
        isbn: "",
        imgLink: "",
      },
      isCreateBookModalVisible: false,
      isDuplicateBookModalVisible: false
    };
  },
  computed: {
    bookURL() {
      return {
        name: "my-books",
        params: {
          isbn: this.$route.params.isbn,
        },
      };
    },
  },
  methods: {
    saveBook() {
      booksService.create(this.book).then((response) => {
        if (response.status === 200) {
          this.showCreateBookModal();
          this.$store.commit("SAVE_BOOK", this.book);
        } else {
          this.showDuplicateBookModal();
        }
      });
    },
    showCreateBookModal() {
      this.isCreateBookModalVisible = true;
    },
    closeCreateBookModal(){
      this.isCreateBookModalVisible = false;
      this.clearFormFields();
    },
    showDuplicateBookModal() {
      this.isDuplicateBookModalVisible = true;
    },
    closeDuplicateBookModal(){
      this.isDuplicateBookModalVisible = false;
      this.clearFormFields();
    },
    clearFormFields(){
        this.book.title = "";
        this.book.author = "";
        this.book.isbn = "";
        this.book.imgLink = "";
    }
  },
};
</script>
<style>
#new-book {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.new-book-form {
  margin: 20px;
}

.new-book-form input,
.new-book-form button {
  margin: 10px;
  font-size: 1rem;
}

.new-book-form {
  background-color: rgb(247, 244, 231);
  border-radius: 10px;
  width: 25%;
  box-shadow: 10px 10px #888888;
  }

.input-center {
  text-align: center;
  }

.input-line {
  border-style: none;
  border-bottom-style: solid;
  border-color: rgb(194, 194, 194);
}

.form-body input {
  border-style: none;
  border-color: gray;
  background-color: rgb(247, 244, 231);
}
</style>