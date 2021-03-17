<template>
  <div id="timer">
    <div>{{ formattedElapsedTime }}</div>
    <button class="button" @click="start" v-if="!this.isRunning">
      Start Timer
    </button>
    <button class="button" @click="stop" v-if="this.isRunning">
      Stop Timer
    </button>
    <button class="button" @click="reset">Reset</button>
    <h4>Total Time Spent Reading: {{ $store.state.total }}</h4>
  </div>
</template>

<script>
export default {
  name: "timer",
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
      isRunning: false,
      total: 0,
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
    formattedTotal() {
       


      const date = new Date(null);
      date.setSeconds(this.total / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
  },
  methods: {
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
      this.isRunning = true;
    },
    stop() {
      clearInterval(this.timer);
      this.isRunning = false;
      this.total = this.total + this.timer;
      
    },
    reset() {
      this.total = this.total + this.elapsedTime;
      this.$store.commit("SAVE_TIME", this.formattedTotal);
      this.elapsedTime = 0;
    },
  },
};
</script>

<style>
#timer {
  display: flex;
  flex-direction: column;
  align-content: center;
  width: 250px;
  gap: 10px;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
  font-size: 4rem;
}

h4 {
  font-size: 2rem;
}
</style>