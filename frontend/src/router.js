import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import RegistrationConfirm from './views/RegistrationConfirm.vue';
import NotFound from './views/NotFound.vue';

Vue.use(Router);

export default new Router({
    mode: 'history',
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
            path: '/registrationConfirm/:token',
            name: 'registrationConfirm',
            component: RegistrationConfirm,
        },
        {
            path: '*',
            name: 'NotFound',
            component: NotFound,
        },
    ],
});
