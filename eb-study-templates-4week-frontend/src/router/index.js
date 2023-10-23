import {createRouter, createWebHistory} from "vue-router";
import BoardCreate from "@/router/views/Board/BoardCreate.vue";

const routes = [
    {
        path: "/board/free/write",
        component: BoardCreate,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;