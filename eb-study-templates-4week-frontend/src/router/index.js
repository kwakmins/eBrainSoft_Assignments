import {createRouter, createWebHistory} from "vue-router";
import Board from "@/router/views/Board.vue";

const routes = [
    {
        path: "/board/free/",
        component: Board,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;