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
        <div class="input-center">
          <button v-on:click.prevent="saveBook()">Add Book</button>
        </div>
      </div>
    </form>
    <p></p>
      <router-link v-bind:to="{name: 'my-books'}">I'm done adding books</router-link>
  </div>
</template>
<script>
import docsService from '../services/DocsService'

export default {
  name: "new-book-form",
  data() {
    return {
      book: {
        title: "",
        author: "",
        read: false,
        isbn: "",
      },
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
      docsService
        .create(this.book)
        .then((response) => {
          if (response.status === 200) {
            alert(
            `Book Added Successfully!
            
            Title: ${this.book.title}
            Author: ${this.book.author}
            ISBN #${this.book.isbn}`
            );
            this.book.title = '';
            this.book.author = '';
            this.book.isbn = '';
            // this.$router.push('/myBooks');
          }
        })
    }
  }
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