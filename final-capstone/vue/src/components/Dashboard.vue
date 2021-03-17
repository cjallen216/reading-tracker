<template>
  <div>
    <main id="main-grid">
      <div id="current-read">
        <h1>        
          Currently Reading:<br>
          This Book
        </h1>
        </div>
      <div id="conan"><iframe width="560" height="315" src="https://www.youtube.com/embed/XHbdoO7uCkk" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
      <div id="timer">
        <timer/>
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

};
</script>

<style>
#main-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 15px;
  grid-template-areas:
    "current conan timer";
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
      ;
  }
}

</style>