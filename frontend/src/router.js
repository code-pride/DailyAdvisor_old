import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import RegistrationConfirm from './views/RegistrationConfirm.vue';
import AfterRegistration from './views/AfterRegistration.vue';
import NotFound from './views/NotFound.vue';
import About from './views/About.vue';

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
            path: '/about',
            name: 'about',
            component: About,
        },
        {
            path: '/register',
            name: 'register',
            component: Register,
        },
        {
            path: '/afterRegistration',
            name: 'afterRegistration',
            component: AfterRegistration,
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
