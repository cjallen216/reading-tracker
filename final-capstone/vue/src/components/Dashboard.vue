<template>
  <div>
    <main id="main-grid">
      <div id="current-read">
        <h1>        
        Currently Reading:<br>
        This Book
        </h1>
        <book-card/>
        </div>
      <div id="conan"><iframe width="560" height="315" src="https://www.youtube.com/embed/XHbdoO7uCkk" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
      <div id="timer">
        <timer/>
        </div>
      <div id="my-books">
        <my-books />
        </div>
    </main>
  </div>
</template>

<script>
import MyBooks from '../views/MyBooks.vue';
import BookCard from './BookCard.vue';
import Timer from './Timer.vue';
export default {
  components: { Timer, MyBooks, BookCard },
  name: "dashboard",
};
</script>

<style>
#main-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 15px;
  grid-template-areas:
    "current conan timer"
    "books books books";
  align-items: center;
}

#current-read {
  grid-area: current;
}

#timer {
  grid-area: timer;
  margin-left: 28%;
}

#my-books {
  grid-area: books;
}

#conan {
  grid-area: conan;
}

@media (max-width: 1024px) {
  #main-grid {
    grid-template-columns: 1fr;
    gap: 10px;
    grid-template-areas:
      "current"
      "conan"
      "timer"
      "books"
      ;
  }
}

</style>