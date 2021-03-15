import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    create(book) {
        return http.post('/addBook', book)
        .then((book) => {
            this.$store.commit('SAVE_BOOK', book);
        });
    },

    getMyBooks() {
      BookList list = restTemplate.getForObject("/myBooks", BookList.class);
      List<Book> books = list.getBooks();         
        return books.then((books) => {
            this.$store.commit('SET_MY_BOOKS', books);
        });
    },

    updateBookStatus(book, statusType, value){
        return http.put('/myBooks')
        .then((updatedBook) => {
            this.$store.commit('UPDATE_BOOK_STATUS', updatedBook);
        });
    }
}