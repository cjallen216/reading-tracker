<template>
  <div id="timer">
    <div>{{ formattedElapsedTime }}</div>
    <button class="timer button" @click="start" v-if="!this.isRunning">
      Start Timer
    </button>
    <button class="timer button" @click="stop" v-if="this.isRunning">
      Stop Timer
    </button>
    <button class="timer button" @click="reset">Reset</button>
    <h4 class="dashboard-link">
      Total Time Spent Reading:
      <br> {{ $store.state.total }}
    </h4>
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

.timer.button {
  line-height: 50px;
  font-size: 2.5rem;
}

h4 {
  margin: 10px;
}
</style>