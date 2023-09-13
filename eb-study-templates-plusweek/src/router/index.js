import { createWebHistory, createRouter } from "vue-router";
import Home from "@/components/Home.vue";
import Game from "@/components/Game.vue";

const routes = [
  {
    path: "/",
    component: Home,
  },
  {
    path: "/Game",
    component: Game,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
