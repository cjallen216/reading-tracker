import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    create(book) {
        return http.post(`/books/`, book);
    },

    list(user) {
        return http.get('books/myBooks/', user).then(results => {
            this.$store.commit('SET_BOOK_LIST_RESULTS', results);
        });
    },
}