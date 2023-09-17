<template>
    <div class="player">
      <span style="font-size: 24px">
        &nbsp;&nbsp;player{{ playerIndex + 1 }} =
        <span v-for="ball in player.balls" :key="ball">
          <span :style="{ color: isWinningBall(ball) ? 'red' : 'black' }">
            &nbsp;&nbsp;{{ ball }}
          </span>
        </span>
      </span>
      <span v-if="hasWon" style="color: chartreuse">
        &nbsp;&nbsp;WIN
      </span>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      playerIndex: Number,
      player: Object,
      winBalls: Array,
    },
    computed: {
      hasWon() {
        return this.player.winBallCnt === this.playerEachCnt;
      },
    },
    methods: {
      isWinningBall(ball) {
        return this.winBalls.includes(ball);
      },
    },
  };
  </script>
  
  <style scoped>
  .player {
    margin-bottom: 10px;
    border: 1px solid #ccc;
    text-align: left;
  }
  </style>
  