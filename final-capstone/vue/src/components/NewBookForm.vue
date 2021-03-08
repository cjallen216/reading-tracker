<template>
  <div id="new-book">
    <form class="new-book-form" v-on:submit.prevent="saveBook">
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
          <button>Add Book</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
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
      this.$store.commit("SAVE_BOOK", this.book);
      this.book = {
        title: "",
        author: "",
        read: false,
        isbn: "",
      };
      this.$router.push(this.bookURL);
    },
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