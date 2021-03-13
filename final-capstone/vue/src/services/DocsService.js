import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    create(book) {
        return http.post(`/books/`, book);
    },

    list() {
        return http.get(`/books/`);
    },
}