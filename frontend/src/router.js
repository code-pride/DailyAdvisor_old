import Vue from 'vue';
import Router from 'vue-router';
import store from './store';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import RegistrationConfirm from './views/RegistrationConfirm.vue';
import AfterRegistration from './views/AfterRegistration.vue';
import NotFound from './views/NotFound.vue';
import Home from './views/Home.vue';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/login',
        },
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/restricted',
            name: 'restricted',
            component: Home,
            meta: { requiresAuthentication: true },
        },
        {
            path: '*',
            name: 'Default',
            component: Login,
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

router.beforeEach((to, from, next) => {
    const pageRequiresAuthentication = to.meta.requiresAuthentication === true;
    const userIsAuthenticated = store.getters.isAuthenticated;

    if (pageRequiresAuthentication && !userIsAuthenticated) {
        next('/login');
    } else {
        next();
    }
});

export default router;
