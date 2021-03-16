import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    create(book) {
        return http.post('/addBook', book);
    },

    getMyBooks() {
      return http.get("/myBooks");
    },

    updateBookStatus(book, statusType, value){
        return http.put('/myBooks')
        .then((response) => {
            this.$store.commit('UPDATE_BOOK_STATUS', response.data);
        });
    }
}