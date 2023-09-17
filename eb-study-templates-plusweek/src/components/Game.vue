<template>
  <div>
    <v-btn @click="gameStart">reStart game</v-btn>
    <router-link to="/"><v-btn style="left: 400px">HOME</v-btn></router-link>
  </div>
  <div>
    <span v-for="(winBall, index) in winBalls" :key="index" style="color: red">
      &nbsp;{{ winBall }}
    </span>
  </div>
  <Player
    v-for="(player, index) in players"
    :key="index"
    :player-index="index"
    :player="player"
    :win-balls="winBalls"
  />
  <WinBall
    :player-cnt="playerCnt"
    :player-each-cnt="playerEachCnt"
    :win-balls="winBalls"
    :players="players"
    :temp-balls="tempBalls"
    :iter="iter"
    :continue="continue"
    :winner-number="winnerNumber"
    @pullOutWinBall="pullOutWinBall"
  />
</template>

<script>
import Player from './Player.vue';
import WinBall from './WinBall.vue';
export default {
  components: {
    Player,
    WinBall,
  },
  data() {
    return {
      // 상수
      //-----------------*
      // 숫자의 범위
      startNumber: 1,
      endNumber: 41,
      // 플레이어 수
      playerCnt: 3,
      // 플레이어 당 갖는 숫자
      playerEachCnt: 5,
      //-----------------*

      // 변수
      // 당첨된 숫자들
      winBalls: [],
      // 플레이어들의 숫자들
      players: [],
      // 임시로 섞인 숫자들
      tempBalls: [],
      // 당첨 공의 순서
      iter: 0,
      // 게임이 끝났는지 확인
      continue: true,
      // 게임 승리자 번호
      winnerNumber: 0,
    };
  },
  methods: {
    /**
     * 게임 시작
     */
    gameStart() {
      this.init();
    },
    /**
     * 게임이 시작된 후, 초기값 설정
     */
    init() {
      this.winBalls = [];
      this.players = [];
      this.tempBalls = [];
      this.iter = 0;
      this.continue = true;
      (this.winnerNumber = 0),
        this.getShuffledNumbers(
          // 처음 플레이어 공을 나누는데 사용
          Array.from(
            { length: this.endNumber - this.startNumber },
            (_, index) => this.startNumber + index
          )
        );
      this.generateBalls();
      this.getShuffledNumbers(
        // 당첨되는 순서의 배열을 갖기 위해 다시 재배열
        Array.from(
          { length: this.endNumber - this.startNumber },
          (_, index) => this.startNumber + index
        )
      );
    },

    /**
     * 배열 섞기
     * @param {범위의 숫자 배열} arr
     */
    getShuffledNumbers(arr) {
      for (let i = arr.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1) + 1);
        [arr[i], arr[j]] = [arr[j], arr[i]];
      }
      this.tempBalls = arr;
    },
    /**
     * 정해진 플레이어 수 만큼 플레이어들에게 정해진 만큼의 숫자 할당
     */
    generateBalls() {
      for (
        let i = 0;
        i < this.playerCnt * this.playerEachCnt;
        i += this.playerEachCnt
      ) {
        this.players.push({
          balls: this.tempBalls
            .slice(i, i + this.playerEachCnt)
            .sort((a, b) => a - b),
          winBallCnt: 0,
        });
      }
    },
    /**
     * 공 뽑기
     */
    pullOutWinBall() {
      this.winBalls.push(this.tempBalls[this.iter]);
      for (let i = 0; i < this.players.length; i++) {
        for (let j = 0; j < this.players[i].balls.length; j++) {
          if (this.players[i].balls[j] === this.tempBalls[this.iter]) {
            this.players[i].winBallCnt += 1;
            if (this.players[i].winBallCnt === this.playerEachCnt) {
              this.continue = false;
              this.winnerNumber = i + 1;
            }
          }
        }
      }
      this.iter = this.iter + 1;
    },
  },
};
</script>
<style>
.v-btn {
  margin: 10px;
}
</style>
