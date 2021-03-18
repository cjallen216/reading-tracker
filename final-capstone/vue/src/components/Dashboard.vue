<template>
  <div>
    <main id="main-grid">
      <div id="current-read">
        <h1 class="dashboard-title">
          Currently Reading:
        <br /> </h1>
          <router-link class="dashboard-link"
            v-show="book.reading"
            v-bind:book="book"
            v-for="book in $store.state.books"
            v-bind:key="book.title"
            v-bind:to="{ name: 'book-details', params: { title: book.title } }"
            >{{ book.title }}
            <br />
          </router-link>

      </div>
      <div id="conan">
        <iframe
          width="460"
          height="315"
          src="https://www.youtube.com/embed/XHbdoO7uCkk"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
      </div>
      <div id="timer">
        <timer />
      </div>
    </main>
  </div>
</template>

<script>
import Timer from './Timer.vue';

export default {
  name: "dashboard",
  components: {
    Timer
  },
  computed: {
    currentlyReading() {
      return this.$store.books.filter((book) => book.reading == true);
    },
  },
};
</script>

<style>
#main-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 15px;
  grid-template-areas:
    "current conan timer";
}

#current-read {
  grid-area: current;
}

#timer {
  grid-area: timer;
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
      "timer";
  }
}
</style>