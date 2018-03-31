import Vue from 'vue';
import Router from 'vue-router';
import SignUp from './views/SignUp.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import NotFound from './views/NotFound.vue';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/register',
            name: 'register',
            component: Register,
        },
        {
            path: '*',
            name: 'NotFound',
            component: NotFound,
        },
        {
            path: '/signup',
            name: 'signup',
            component: SignUp,
        },
    ],
});
