<template>
    <div>
      <v-btn v-if="continue" @click="pullOutWinBall">pull out Ball</v-btn>
      <h3 v-else style="color: green">
        &nbsp;&nbsp;Player{{ winnerNumber }}의 승리!
      </h3>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      playerCnt: Number,
      playerEachCnt: Number,
      winBalls: Array,
      players: Array,
      tempBalls: Array,
      iter: Number,
      continue: Boolean,
      winnerNumber: Number,
    },
    methods: {
      pullOutWinBall() {
        this.$emit('pullOutWinBall');
      },
    },
  };
  </script>
<style>

</style>  